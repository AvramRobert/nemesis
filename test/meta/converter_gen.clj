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

(defn function [params]
  (format "Function%d<%s> comb" (dec params) (types params)))

(defn mapper [index letter]
  (format "Function1<JsonTransform, Either<String, %s>> f%d" letter index))

(defn return [params]
  (return-type \A params))

(defn mappers [params]
  (->> (letters \A)
       (map-indexed mapper)
       (take params)
       (join ",\n")))

(defn binds [letters param total]
  (if (= param (dec total))
    (format map-blob,
            param
            (nth letters param)
            (types \a total))
    (format flatmap-blob
            param
            (nth letters param)
            (binds letters (inc param) total))))

(defn flatmaps [total]
  (binds (->> \a (letters) (take total) (vec)) 0 total))

(defn method [arity]
  (let [param# (inc arity)]
    (format combine-fn
            (types param#)
            (return param#)
            (mappers arity)
            (function param#)
            (flatmaps arity))))

(defn class-def [method#]
  (format converter-class (->> (range 1 method#) (map method) (join "\n\n"))))

(defn create-file [nr-fns]
  (spit "./src/java/json/coerce/Converter.java" (class-def nr-fns)))