(ns meta.json-converter-gen
  (:require [clojure.string :as s]
            [meta.gen-util :refer :all]))

(def class-def
  "package %s;
  import static %s.util.function.Functions.*;
  import %2$s.util.error.Either;
  import %2$s.model.JsonT;

  public final class %s {
    private final JsonT json;

      public %3$s(final JsonT json) {
        this.json = json;
      }

    %s
  }")

(def map-def "f%d.convert(json)\n.map(%s -> comb.apply(%s))")

(def flat-map-def "f%d.convert(json)\n.flatMap(%s -> %s)")

(def function-def
  "public final <%s> Either<String, %s> with(\n%s,\n%s) {
    return %s;
  }")

(defn combiner-def [arity]
  (format "Function%d<%s> comb" (dec arity) (type-list arity)))

(defn converter [index letter]
  (format "Convert<JsonT, %s> f%d" letter index))

(defn converters [params]
  (->> type-labels
       (map-indexed converter)
       (take params)
       (s/join ",\n")))

(defn binds [param arity]
  (if (>= param (dec arity))
    (format map-def
            param
            (nth type-vars param)
            (var-list arity))
    (format flat-map-def
            param
            (nth type-vars param)
            (binds (inc param) arity))))

(defn flat-maps [arity]
  (binds 0 arity))

(defn method [arity]
  (format function-def
          (type-list arity)
          (return-type arity)
          (converters (dec arity))
          (combiner-def arity)
          (flat-maps (dec arity))))

(defn clazz [domain
             package
             class-name
             method#]
  (->> (+ method# 2)
       (range 2)
       (map method)
       (s/join "\n\n")
       (format class-def package domain class-name)))

(defn create-file [fn#]
  (let [domain  "com.ravram.nemesis"
        package (str domain ".coerce")
        path    (s/replace package "." "/")
        class   "JsonConverter"]
    (spit (format "./src/%s/%s.java" path class)
          (clazz domain package class fn#))))