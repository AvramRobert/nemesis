(ns meta.function-gen
  (:require [meta.gen-util :refer :all]
            [clojure.string :refer [upper-case lower-case join]]))

(def functional-interface
  "@FunctionalInterface\n public interface Function%s<%s> {\n  %s apply (%s); }\n")

(def functions-class
  "package util;\n public class Functions {\n  %s }")

(defn param [letter]
  (format "final %s %s" (upper-case letter) (lower-case letter)))

(defn params [arity]
  (->> type-labels (take arity) (map param) (join ",\n")))

(defn interface [arity]
  (format functional-interface
          arity
          (type-list (inc arity))
          (return-type (inc arity))
          (params arity)))

(defn class-def [interface#]
  (->> (+ interface# 1)
       (range 1)
       (map interface)
       (join "\n\n")
       (format functions-class)))

(defn create-file [interface#]
  (spit "./src/java/util/Functions.java" (class-def interface#)))