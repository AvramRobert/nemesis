(ns meta.gen-util
  (:require [clojure.string :refer [join lower-case]]))

(def type-labels
  (->> 0 (iterate inc) (map (partial str "T"))))

(def type-vars
  (map lower-case type-labels))

(defn var-list [arity]
  (->> type-vars (take arity) (join ",")))

(defn type-list [arity]
  (->> type-labels (take arity) (join ",")))

(defn return-type [arity]
  (->> type-labels (take arity) (last)))