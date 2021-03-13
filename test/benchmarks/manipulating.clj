(ns benchmarks.manipulating
  (:require [clojure.test :refer :all]
            [nemesis.util.generators :refer :all]
            [nemesis.util.conversion :refer :all]
            [clojure.test.check.generators :as gen]
            [benchmarks.util :as u])
  (:import (json Converters)))

(def ^:const SAMPLE-SIZE 5)

(defn assoc-in-non-empty [m keys val]
  (if (empty? m) m (assoc-in m keys val)))

(defn convertor [scalar]
  (cond
    (int? scalar)     {:to   Converters/INT_TO_JSON
                       :from Converters/JSON_TO_INT}
    (double? scalar)  {:to   Converters/DOUBLE_TO_JSON
                       :from Converters/JSON_TO_DOUBLE}
    (string? scalar)  {:to   Converters/STRING_TO_JSON
                      :from Converters/JSON_TO_STRING}
    (boolean? scalar) {:to   Converters/BOOLEAN_TO_JSON
                       :from Converters/JSON_TO_BOOLEAN}
    (nil? scalar)     {:to   Converters/NULL_TO_JSON
                       :from Converters/JSON_TO_NULL}))

(defn update-fn [scalar]
  (cond
    (number? scalar)  (comp str inc)
    (string? scalar)  count
    (boolean? scalar) (comp str not)
    (nil? scalar)     str))

(defn insert-json [{:keys [json value in]}]
  (-> json (.transform) (.insertJson value in) (.affix)))

(defn insert-any-val [{:keys [json value in]}]
  (-> json (.transform) (.insertValue value in) (.affix)))

(defn insert-scalar-val [{:keys [json value to in]}]
  (-> json (.transform) (.insertValue value to in) (.affix)))

(defn lookup-json [{:keys [json in]}]
  (-> json (.transform) (.getJson in) (.affix)))

(defn lookup-scalar [{:keys [json from in]}]
  (-> json (.transform) (.getValue from in)))

(defn merge-json [{:keys [json value]}]
  (-> json (.transform) (.mergeJson value) (.affix)))

(defn update-json-partial [{:keys [json to in fn]}]
  (-> json (.transform) (.updateValue to fn in) (.affix)))

(defn update-json-total [{:keys [json from to in fn]}]
  (-> json (.transform) (.updateValue to fn from in) (.affix)))

(defn gen-insertion-with [f]
  (do-gen [json  (gen-map {:max-depth    7
                           :max-elements 3})
           value (gen-json {})
           path  (gen-path {:max-depth 7})]
    {:in    (in path)
     :json  (clj->nem json)
     :value (f value)}))

(def gen-scalar-insertion
  (do-gen [json   (gen-map {:max-depth    7
                            :max-elements 3})
           scalar (gen/one-of default-scalars)
           path   (gen-path {:max-depth 7})]
    {:in    (in path)
     :json  (clj->nem json)
     :value scalar
     :to    (-> scalar (convertor) (:to))}))

(def gen-lookup
  (do-gen [json (gen-map {:max-depth    7
                          :max-elements 3})
           path (gen-path-from json)]
    {:in   (in path)
     :json (clj->nem json)}))

(def gen-scalar-lookup
  (do-gen [json   (gen-map {:max-depth    7
                            :max-elements 3})
           path   (gen-path-from json)
           scalar (gen/one-of default-scalars)]
    {:in   (in path)
     :from (-> scalar (convertor) (:from))
     :json (clj->nem (assoc-in-non-empty json path scalar))}))

(def gen-obj-merge
  (do-gen [map1 (gen-map {:max-depth    7
                          :max-elements 3})
           map2 (gen-map {:max-depth    7
                          :max-elements 3})]
    {:json  (clj->nem map1)
     :value (clj->nem map2)}))

(def gen-arr-merge
  (do-gen [arr1 (gen-arr {:max-depth    7
                          :max-elements 3})
           arr2 (gen-arr {:max-depth    7
                          :max-elements 3})]
    {:json  (clj->nem arr1)
     :value (clj->nem arr2)}))

(defn gen-merge-for [type]
  (case type
    :array gen-arr-merge
    :object gen-obj-merge))

(def gen-scalar-update
  (do-gen [json   (gen-map {:max-depth    7
                            :max-elements 3})
           path   (gen-path-from json)
           scalar (gen/one-of default-scalars)]
    (let [f       (update-fn scalar)
          scalar' (f scalar)
          json'   (assoc-in-non-empty json path scalar)]
      {:json (clj->nem json')
       :to   (-> scalar (convertor) (:to))
       :from (-> scalar' (convertor) (:from))
       :in   (in path)
       :fn   (function f)})))

;; FIXME: These should have progressive sizes
;; E.G: inserting small, medium, large json.
;; where small depth = [0, 2], elements = [0, 10]
;;       medium depth = [3, 5], elements = [10, 20]
;;       large  depth = [5, 10], elements = [20, 25]

(def default-tasks
  [{:name      "Insert json"
    :operation insert-json
    :samples   (gen/sample (gen-insertion-with clj->nem) SAMPLE-SIZE)}
   {:name      "Insert raw value with default convert"
    :operation insert-any-val
    :samples   (gen/sample (gen-insertion-with clj->java) SAMPLE-SIZE)}
   {:name      "Insert raw value with given convert"
    :operation insert-scalar-val
    :samples   (gen/sample gen-scalar-insertion SAMPLE-SIZE)}
   {:name      "Lookup json"
    :operation lookup-json
    :samples   (gen/sample gen-lookup SAMPLE-SIZE)}
   {:name      "Lookup raw value with given convert"
    :operation lookup-scalar
    :samples   (gen/sample gen-scalar-lookup SAMPLE-SIZE)}
   {:name      "Merge json objects"
    :operation merge-json
    :samples (gen/sample (gen-merge-for :object) SAMPLE-SIZE)}
   {:name      "Merge json arrays"
    :operation merge-json
    :samples (gen/sample (gen-merge-for :array) SAMPLE-SIZE)}
   {:name      "Update with partial given convert"
    :operation update-json-partial
    :samples (gen/sample gen-scalar-update SAMPLE-SIZE)}
   {:name      "Update with total given convert"
    :operation update-json-total
    :samples (gen/sample gen-scalar-update SAMPLE-SIZE)}])

(defn benchmark! [& tasks]
  (doseq [task tasks]
    (-> task (u/benchmark-compound) (u/show-result) (println))))

(deftest run-benchmark
  (apply benchmark! default-tasks))