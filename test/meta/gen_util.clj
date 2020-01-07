(ns meta.gen-util
  (:require [clojure.string :refer [join]]))

(defn letters [start]
  (->> (int start) (iterate inc) (map char)))

(defn types
  ([param#]
   (types \A param#))
  ([letter param#]
   (->> letter (letters) (take param#) (join ","))))

(defn return-type [letter param#]
  (->> letter (letters) (take param#) (last)))