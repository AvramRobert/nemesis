(ns meta.object-converter-gen
  (:require [clojure.string :as s]
            [meta.gen-util :refer :all]))

(def class-def
  "package %s;

  import %s.json.model.JObj;
  import %2$s.json.model.Json;
  import %2$s.util.error.Either;
  import io.lacuna.bifurcan.Map;
  import io.lacuna.bifurcan.Maps.Entry;
  import java.util.Arrays;

  public class %s<A> {
    private final A obj;

    private Entry<String, Json> entry(final String key, final Json value) {
      return new Entry<>(\"\\\"\" + key + \"\\\"\", value);
    }

    public %3$s(final A obj) {
        this.obj = obj;
    }

    @SafeVarargs
    private JObj mapFrom(final Entry<String, Json>... entries) {
      return new JObj(Map.from(Arrays.asList(entries)));
    }

    %s
  }")

(def function-def "public final Either<String, Json> with(%s) {\n return %s; }")

(def param-def "final String key%d, final Convert<A, Json> f%d")

(def map-def "f%d.convert(obj).map(%s -> mapFrom(%s))")

(def flat-map-def "f%d.convert(obj).flatMap(%s -> %s)")

(def entry-def "entry(key%d, %s)")

(defn entry-list [arity]
  (->> (range 0 arity)
       (map #(format entry-def % (nth type-vars %)))
       (s/join ",")))

(defn param-pairs [arity]
  (->> (range 0 arity)
       (map #(format param-def % %))
       (s/join ",\n")))

(defn binds [param arity]
  (if (= param (dec arity))
    (format map-def
            param
            (nth type-vars param)
            (entry-list arity))
    (format flat-map-def
            param
            (nth type-vars param)
            (binds (inc param) arity))))

(defn flat-maps [arity]
  (binds 0 arity))

(defn method [arity]
  (format function-def
          (param-pairs arity)
          (flat-maps arity)))

(defn clazz [domain package class-name fn#]
  (->> (inc fn#)
       (range 1)
       (map method)
       (s/join "\n\n")
       (format class-def package domain class-name)))

(defn create-file [fn#]
  (let [domain "com.ravram.nemesis"
        package (str domain ".json.coerce")
        path    (s/replace package "." "/")
        class   "ObjectConverter"]
    (spit (format "./src/%s/%s.java" path class)
          (clazz domain package class fn#))))