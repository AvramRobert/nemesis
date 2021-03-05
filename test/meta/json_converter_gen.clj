(ns meta.json-converter-gen
  (:require [clojure.string :refer [join]]
            [meta.gen-util :refer :all]))

(def class-def
  "package json.coerce;
  import static util.Functions.*;
  import util.Either;
  import json.data.Json;
  import json.data.JsonT;

  public class JsonConverter {
    private final Json json;

      public JsonConverter(final Json json) {
        this.json = json;
      }

    %s
  }")

(def map-def "f%d.convert(blob)\n.map(%s -> comb.apply(%s))")

(def flat-map-def "f%d.convert(blob)\n.flatMap(%s -> %s)")

(def function-def
  "public <%s> Either<String, %s> with(\n%s,\n%s) {
    JsonT blob = json.transform();
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
       (join ",\n")))

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

(defn clazz [method#]
  (->> (+ method# 2)
       (range 2)
       (map method)
       (join "\n\n")
       (format class-def)))

(defn create-file [fn#]
  (spit "./src/java/json/coerce/JsonConverter.java" (clazz fn#)))