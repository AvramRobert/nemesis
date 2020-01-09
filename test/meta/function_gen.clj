(ns meta.function-gen
  (:require [meta.gen-util :refer :all]
            [clojure.string :refer [upper-case lower-case join]]))

(def interface-def
  "@FunctionalInterface\n public interface Function%s<%s> {\n  %s apply (%s); }\n")

(def class-def
  "package util;\n public class Functions {\n  %s }")

(defn param-def [letter]
  (format "final %s %s" (upper-case letter) (lower-case letter)))

(defn params [arity]
  (->> type-labels (take arity) (map param-def) (join ",\n")))

(defn interface [arity]
  (format interface-def
          arity
          (type-list (inc arity))
          (return-type (inc arity))
          (params arity)))

(defn clazz [interface#]
  (->> (inc interface#)
       (range 1)
       (map interface)
       (join "\n\n")
       (format class-def)))

(defn create-file [interface#]
  (spit "./src/java/util/Functions.java" (clazz interface#)))