(ns nemesis.transformation.transform
  (:require [clojure.test :refer :all]
            [cheshire.core :as json])
  (:import json.data.Json
           (json.data JNum JString JObj JArr JEmpty JBool JNull)
           (io.lacuna.bifurcan List Map Maps$Entry)))

(defn bifurcan-map [map]
  (reduce (fn [m [k v]] (.put m k v)) (Map.) map))

(defn bifurcan-list [list]
  (reduce (fn [l a] (.addLast l a)) (List.) list))

(defn bifurcan-entry [[js v]] [(str js) v])

(defn entry? [v]
  (and (vector? v) (->> v (count) (= 2))))

(defn empty-map? [form]
  (and (map? form) (empty? form)))

(defn coerce [form]
  (cond
    (nil? form)       JNull/instance
    (empty-map? form) JEmpty/instance
    (number? form)    (JNum. form)
    (string? form)    (JString. form)
    (keyword? form)   (JString. (name form))
    (boolean? form)   (if form JBool/jtrue JBool/jfalse)
    (entry? form)     (bifurcan-entry form)
    (map? form)       (JObj. (bifurcan-map form))
    (vector? form)    (JArr. (bifurcan-list form))
    :else (throw (Exception. (format "Illegal JSON type of `%s`" form)))))

(defn write-json [clj]
  (clojure.walk/postwalk coerce clj))

(defn read-json [^Json json]
  (json/parse-string-strict (str json)))