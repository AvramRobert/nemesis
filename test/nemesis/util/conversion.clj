(ns nemesis.util.conversion
  (:require [clojure.test :refer :all]
            [cheshire.core :as json]
            [clojure.pprint :refer [pprint]])
  (:import (json.model Json JNum JString JObj JArr JBool JNull)
           (io.lacuna.bifurcan List Map)
           (json.parser Parser)
           (json.model JsonT)
           (json JsonOps)
           (util.function Functions$Function1)
           (java.util ArrayList HashMap HashSet)))

(deftype WEntry [key value])
(deftype WMap [entries])

(defn- wentry? [e]
  (instance? WEntry e))

(defn- wmap? [m]
  (instance? WMap m))

(defn- walker [f clj]
  (letfn [(recurse [[k v]]
            (walker f (WEntry. (walker f k) (walker f v))))]
    (cond
      (map? clj)   (->> clj (mapv recurse) (WMap.) (f))
      (coll? clj)  (->> clj (mapv (partial walker f)) (f))
      :else (f clj))))

(defn- coerce [form]
  (letfn [(empty-map? [form]
            (and (map? form) (empty? form)))

          (bifurcan-map [form]
            (reduce (fn [m e]
                      (.put m (str (.key e)) (.value e))) (Map.) form))]
    (cond
      (nil? form)       JNull/instance
      (empty-map? form) JObj/empty
      (number? form)    (JNum. form)
      (string? form)    (JString. (pr-str form))
      (keyword? form)   (JString. (name form))
      (boolean? form)   (if form JBool/jtrue JBool/jfalse)
      (wentry? form)    form
      (wmap? form)      (JObj. (bifurcan-map (.entries form)))
      (vector? form)    (JArr. (List/from form))
      :else (throw (Exception. (format "Illegal JSON type of `%s`" form))))))

(defn- keyseq [form]
  (cond
    (and (map? form)
         (not-empty form)) (let [k (->> form (keys) (rand-nth))]
                             (cons k (lazy-seq (keyseq (get form k)))))
    (and (vector? form)
         (not-empty form)) (let [i (rand-int (count form))]
                             (cons i (lazy-seq (keyseq (nth form i)))))
    :else '()))

(defn clj->java [clj]
  (cond
    (vector? clj) (->> clj (mapv clj->java) (ArrayList.))
    (map? clj)    (->> clj (mapv (juxt key (comp clj->java val))) (into {}) (HashMap.))
    (set? clj)    (->> clj (mapv clj->java) (HashSet.))
    :else         clj))

(defn clj->nem [clj]
  (walker coerce clj))

(defn json->clj [json-string]
  (json/parse-string json-string))

(defn nem->clj [^Json json]
  (json->clj (str json)))

(defn clj->json [clj]
  (json/generate-string clj))

(defn json->nem [json]
  (Parser/parse json))

(defn transform [f & cljs]
  (let [res (->> cljs (map clj->nem) (map #(.transform %)) (apply f) (.affix))]
    (if (.isRight res)
      (nem->clj (.value res))
      (pprint res))))

(defn rand-keyseq [form]
  (vec (keyseq form)))

(defn function [fn]
  (reify Functions$Function1
    (apply [_ a] (fn a))))

(defn in [args]
  (JsonOps/in (into-array Object args)))

(defn insert-j [json keys]
  (fn [^JsonT json-t]
    (.insertJson json-t json (in keys))))

(defn insert-jval [value keys]
  (fn [^JsonT json-t]
    (.insertValue json-t value (in keys))))

(defn remove-j [keys]
  (fn [^JsonT json-t]
    (.remove json-t (into-array String keys))))

(defn get-j [keys]
  (fn [^JsonT json-t]
    (.getJson json-t (in keys))))

(defn update-j [fn-j keys]
  (fn [^JsonT json-t]
    (.updateJson json-t (function fn-j) (in keys))))

(defn update-jval [to fn-val from keys]
  (fn [^JsonT json-t]
    (.updateValue json-t to (function fn-val) from (in keys))))

(defn merge-j [^JsonT a ^JsonT b]
  (.mergeJson a b))