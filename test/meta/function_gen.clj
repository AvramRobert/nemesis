(ns meta.function-gen
  (:require [meta.gen-util :refer :all]
            [clojure.string :refer [upper-case lower-case join]]))


(def fun-interface
  "@FunctionalInterface\n public interface Function%s<%s> {\n  %s apply (%s); }")

(def con-interface
  "@FunctionalInterface\n public interface Consumer%s<%s> {\n  void apply(%s); }")

(def class-def
  "package util;\n public class Functions {\n  %s }")

(defn param-def [letter]
  (format "final %s %s" (upper-case letter) (lower-case letter)))

(defn param-list [arity]
  (->> type-labels (take arity) (map param-def) (join ",\n")))

(defn fun-interfaces [arity]
  (format fun-interface
          arity
          (type-list (inc arity))
          (return-type (inc arity))
          (param-list arity)))

(defn con-interfaces [arity]
  (format con-interface
          arity
          (type-list arity)
          (param-list arity)))

(defn interfaces [arity]
  (str (fun-interfaces arity) "\n\n" (con-interfaces arity)))

(defn clazz [interface#]
  (->> (inc interface#)
       (range 1)
       (map interfaces)
       (join "\n\n")
       (format class-def)))

(defn create-file [interface#]
  (spit "./src/java/util/Functions.java" (clazz interface#)))