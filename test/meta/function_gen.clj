(ns meta.function-gen
  (:require [meta.gen-util :refer :all]
            [clojure.string :refer [upper-case lower-case join]]))

(def function-def
  "@FunctionalInterface\n public interface Function%s<%s> {\n  %s apply (%s); }")

(def consumer-def
  "@FunctionalInterface\n public interface Consumer%s<%s> {\n  void apply(%s); }")

(def class-def
  "package util.function;\n public class %s {\n  %s }")

(defn param-def [letter]
  (format "final %s %s" (upper-case letter) (lower-case letter)))

(defn param-list [arity]
  (->> type-labels (take arity) (map param-def) (join ",\n")))

(defn fun-interface [arity]
  (format function-def
          arity
          (type-list (inc arity))
          (return-type (inc arity))
          (param-list arity)))

(defn con-interface [arity]
  (format consumer-def
          arity
          (type-list arity)
          (param-list arity)))

(defn clazz [class-name interface-def interface#]
  (->> (inc interface#)
       (range 1)
       (map interface-def)
       (join "\n\n")
       (format class-def class-name)))

(defn create-file [interface#]
  (spit "./src/java/util/function/Consumers.java" (clazz "Consumers" con-interface interface#))
  (spit "./src/java/util/function/Functions.java" (clazz "Functions" fun-interface interface#)))