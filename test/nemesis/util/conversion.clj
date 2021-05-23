(ns nemesis.util.conversion
  (:require [clojure.test :refer :all]
            [cheshire.core :as json]
            [clojure.pprint :refer [pprint]])
  (:import (com.ravram.nemesis.parser Parser)
           (com.ravram.nemesis Json JsonT)
           (com.ravram.nemesis.util.function Functions$Function1 Functions$Function3)
           (java.util ArrayList HashMap HashSet)))

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

(defn json->clj [json-string]
  (json/parse-string json-string))

(defn nem->clj [^Json json]
  (json->clj (.encode json)))

(defn clj->json [clj]
  (json/generate-string clj))

(defn json->nem [json]
  (-> json (Parser/parse) (.value)))

(defn clj->nem [clj]
  (-> clj (clj->json) (json->nem)))

(defn rand-keyseq [form]
  (vec (keyseq form)))

(defn function-1 [fn]
  (reify Functions$Function1
    (apply [_ a] (fn a))))

(defn function-3 [fn]
  (reify Functions$Function3
    (apply [_ a b c] (fn a b c))))

(defn in [args]
  (into-array Object args))

(defn converted [f & cljs]
  (->> cljs
       (mapv clj->nem)
       (mapv #(.transform %))
       (apply f)))

(defn result [^JsonT json-t]
  (-> json-t (.affix) (.map (function-1 nem->clj))))

(defn result-value [^JsonT json-t]
  (-> json-t (result) (.value)))

(defn insert-j [clj json keys]
  (converted #(.insertJson % json (in keys)) clj))

(defn insert-jval [clj value keys]
  (converted #(.insertValue % value (in keys)) clj))

(defn remove-j [clj keys]
  (converted #(.remove % (into-array String keys)) clj))

(defn get-j [clj keys]
  (converted #(.getJson % (in keys)) clj))

(defn update-j [clj fn keys]
  (converted #(.updateJson % (function-1 (comp fn result-value)) (in keys)) clj))

(defn update-jval [clj to fn from keys]
  (converted #(.updateValue % to (function-1 fn) from (in keys)) clj))

(defn merge-j [clj-1 clj-2]
  (converted #(.mergeJson %1 %2) clj-1 clj-2))

(defn reduce-obj-j [clj init f]
  (.reduceObj (.transform (clj->nem clj)) init (function-3 f)))

(defn reduce-arr-j [clj init f]
  (.reduceArr (.transform (clj->nem clj)) init (function-3 f)))