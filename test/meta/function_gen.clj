(ns meta.function-gen
  (:require [meta.gen-util :refer :all]
            [clojure.string :as s]))

(def function-def
  "@FunctionalInterface\n public interface Function%s<%s> {\n  %s apply (%s); }")

(def consumer-def
  "@FunctionalInterface\n public interface Consumer%s<%s> {\n  void apply(%s); }")

(def class-def
  "package %s;\n public final class %s {\n  %s }")

(defn param-def [letter]
  (format "final %s %s" (s/upper-case letter) (s/lower-case letter)))

(defn param-list [arity]
  (->> type-labels (take arity) (map param-def) (s/join ",\n")))

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

(defn clazz [package class-name interface-def interface#]
  (->> (inc interface#)
       (range 1)
       (map interface-def)
       (s/join "\n\n")
       (format class-def package class-name)))

(defn create-file [interface#]
  (let [domain    "com.ravram.nemesis"
        package   (str domain ".util.function")
        path      (s/replace package "." "/")
        consumers "Consumers"
        functions "Functions"]
    (spit (format "./src/%s/%s.java" path consumers)
          (clazz package consumers con-interface interface#))
    (spit (format "./src/%s/%s.java" path functions)
          (clazz package functions fun-interface interface#))))