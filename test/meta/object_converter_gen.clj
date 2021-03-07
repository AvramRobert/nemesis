(ns meta.object-converter-gen
  (:require [meta.gen-util :refer :all]
            [clojure.string :refer [join]]))

(def class-def
  "package json.coerce;
  import io.lacuna.bifurcan.Map;
  import io.lacuna.bifurcan.Maps.Entry;
  import json.model.JObj;
  import json.model.Json;
  import util.error.Either;
  import java.util.Arrays;

  public class ObjectConverter<A> {
    private final A obj;

    private Entry<String, Json> entry(final String key, final Json value) {
      return new Entry<>(\"\\\"\" + key + \"\\\"\", value);
    }

    public ObjectConverter(final A obj) {
        this.obj = obj;
    }

    @SafeVarargs
    private JObj mapFrom(final Entry<String, Json>... entries) {
      return new JObj(Map.from(Arrays.asList(entries)));
    }

    %s'
  }")

(def function-def "public final Either<String, Json> with(%s) {\n return %s; }")

(def param-def "final String key%d, final Convert<A, Json> f%d")

(def map-def "f%d.convert(obj).map(%s -> mapFrom(%s))")

(def flat-map-def "f%d.convert(obj).flatMap(%s -> %s)")

(def entry-def "entry(key%d, %s)")

(defn entry-list [arity]
  (->> (range 0 arity)
       (map #(format entry-def % (nth type-vars %)))
       (join ",")))

(defn param-pairs [arity]
  (->> (range 0 arity)
       (map #(format param-def % %))
       (join ",\n")))

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

(defn clazz [fn#]
  (->> (inc fn#)
       (range 1)
       (map method)
       (join "\n\n")
       (format class-def)))

(defn create-file [fn#]
  (spit "./src/java/json/coerce/ObjectConverter.java" (clazz fn#)))