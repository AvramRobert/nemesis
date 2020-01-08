(ns meta.converter-gen
  (:require [clojure.string :refer [join]]
            [meta.gen-util :refer :all]))

(def map-blob "f%d.apply(blob)\n.map(%s -> comb.apply(%s))")

(def flatmap-blob "f%d.apply(blob)\n.flatMap(%s -> %s)")

(def combine-fn "public static <%s> Convert<Json, %s> combine(\n%s,\n%s) {
    return json -> {
    JsonTransform blob = json.transform();
    return %s;
    };}")

(def converter-class
  "package json.coerce;
  import static util.Functions.*;
  import json.data.Json;
  import json.data.JsonTransform;
  import util.Either;

  public class Converter {\n  %s }")

(defn combiner [arity]
  (format "Function%d<%s> comb" (dec arity) (type-list arity)))

(defn mapper [index letter]
  (format "Function1<JsonTransform, Either<String, %s>> f%d" letter index))

(defn transformers [params]
  (->> type-labels
       (map-indexed mapper)
       (take params)
       (join ",\n")))

(defn binds [param arity]
  (if (>= param (dec arity))
    (format map-blob
            param
            (nth type-vars param)
            (var-list arity))
    (format flatmap-blob
            param
            (nth type-vars param)
            (binds (inc param) arity))))

(defn flat-maps [arity]
  (binds 0 arity))

(defn method [arity]
  (format combine-fn
          (type-list arity)
          (return-type arity)
          (transformers (dec arity))
          (combiner arity)
          (flat-maps (dec arity))))

;; It only makes sense to start with an arity of 2
(defn class-def [method#]
  (->> (+ method# 2)
       (range 2)
       (map method)
       (join "\n\n")
       (format converter-class)))

(defn create-file [fn#]
  (spit "./src/java/json/coerce/Converter.java" (class-def fn#)))