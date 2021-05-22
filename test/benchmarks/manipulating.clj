(ns benchmarks.manipulating
  (:require [clojure.test :refer :all]
            [nemesis.util.generators :refer :all]
            [nemesis.util.conversion :refer [clj->nem clj->java in function-1]]
            [clojure.test.check.generators :as gen]
            [benchmarks.util :as u])
  (:import (com.ravram.nemesis Writers Readers)))

(def ^:const SAMPLE-SIZE 5)

(defn assoc-in-non-empty [m keys val]
  (if (empty? m) m (assoc-in m keys val)))

(defn convertor [scalar]
  (cond
    (int? scalar) {:to   Writers/WRITE_LONG
                   :from Readers/READ_LONG}
    (double? scalar) {:to   Writers/WRITE_DOUBLE
                      :from Readers/READ_DOUBLE}
    (string? scalar) {:to   Writers/WRITE_STRING
                      :from Readers/READ_STRING}
    (boolean? scalar) {:to   Writers/WRITE_BOOLEAN
                       :from Readers/READ_BOOLEAN}
    (nil? scalar) {:to   Writers/WRITE_NULL
                   :from Readers/READ_NULL}))

(defn update-fn [scalar]
  (cond
    (number? scalar)  (comp str inc)
    (string? scalar)  (comp long count)
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

(defn update-json-partial [{:keys [json from fn in]}]
  (-> json (.transform) (.updateValue from fn in) (.affix)))

(defn update-json-total [{:keys [json from fn to in]}]
  (-> json (.transform) (.updateValue from fn to in) (.affix)))

(defn gen-insertion-as [f opts]
  (do-gen [json  (gen-map opts)
           value (gen-json {})
           path  (gen-path {:max-depth 3})]
    {:in    (in path)
     :json  (clj->nem json)
     :value (f value)}))

(defn gen-scalar-insertion [opts]
  (do-gen [json   (gen-map opts)
           scalar (gen/one-of default-scalars)
           path   (gen-path {:max-depth 3})]
    {:in    (in path)
     :json  (clj->nem json)
     :value scalar
     :to    (-> scalar (convertor) (:to))}))

(defn gen-lookup [opts]
  (do-gen [json (gen-map opts)
           path (gen-path-from json)]
    {:in   (in path)
     :json (clj->nem json)}))

(defn gen-scalar-lookup [opts]
  (do-gen [json   (gen-map opts)
           path   (gen-path-from json)
           scalar (gen/one-of default-scalars)]
    {:in   (in path)
     :from (-> scalar (convertor) (:from))
     :json (clj->nem (assoc-in-non-empty json path scalar))}))

(defn gen-obj-merge [opts]
  (do-gen [map1 (gen-map opts)
           map2 (gen-map opts)]
    {:json  (clj->nem map1)
     :value (clj->nem map2)}))

(defn gen-arr-merge [opts]
  (do-gen [arr1 (gen-arr opts)
           arr2 (gen-arr opts)]
    {:json  (clj->nem arr1)
     :value (clj->nem arr2)}))

(defn gen-merge-for [type opts]
  (case type
    :array (gen-arr-merge opts)
    :object (gen-obj-merge opts)))

(defn gen-scalar-update [opts]
  (do-gen [json   (gen-map opts)
           path   (gen-path-from json)
           scalar (gen/one-of [gen-string])]
    (let [f       (update-fn scalar)
          json'   (assoc-in-non-empty json path scalar)]
      {:json (clj->nem json')
       :from (-> scalar (convertor) (:from))
       :to   (-> scalar (f) (convertor) (:to))
       :in   (in path)
       :fn   (function-1 f)})))

(def insertion-tasks
  (let [small {:densities {0 {:min-elements 2
                              :max-elements 20}
                           1 {:min-elements 5
                              :max-elements 10}}}
        large {:densities {0 {:min-elements 30
                              :max-elements 40}
                           1 {:min-elements 5
                              :max-elements 10}
                           2 {:min-elements 3
                              :max-elements 5}}}]
    [{:name      "Insert Json (small)"
      :operation insert-json
      :sampler   #(gen/sample (gen-insertion-as clj->nem small) SAMPLE-SIZE)}
     {:name      "Insert Json (large)"
      :operation insert-json
      :sampler   #(gen/sample (gen-insertion-as clj->nem large) SAMPLE-SIZE)}

     {:name      "Insert raw value with default conversion (small)"
      :operation insert-any-val
      :sampler   #(gen/sample (gen-insertion-as clj->java small) SAMPLE-SIZE)}
     {:name      "Insert raw value with default conversion (large)"
      :operation insert-any-val
      :sampler   #(gen/sample (gen-insertion-as clj->java large) SAMPLE-SIZE)}

     {:name      "Insert raw value with provided conversion (small)"
      :operation insert-scalar-val
      :sampler   #(gen/sample (gen-scalar-insertion small) SAMPLE-SIZE)}
     {:name      "Insert raw value with provided conversion (large)"
      :operation insert-scalar-val
      :sampler   #(gen/sample (gen-scalar-insertion large) SAMPLE-SIZE)}]))

(def lookup-tasks
  (let [shallow  {:min-elements 1
                  :max-elements 3
                  :min-depth 0
                  :max-depth 3}
        deep     {:min-elements 1
                  :max-elements 3
                  :min-depth 5
                  :max-depth 7}]
    [{:name      "Lookup json (shallow)"
      :operation lookup-json
      :sampler   #(gen/sample (gen-lookup shallow) SAMPLE-SIZE)}
     {:name      "Lookup Json (deep)"
      :operation lookup-json
      :sampler   #(gen/sample (gen-lookup deep) SAMPLE-SIZE)}

     {:name      "Lookup raw value with given conversion (shallow)"
      :operation lookup-scalar
      :sampler   #(gen/sample (gen-scalar-lookup shallow) SAMPLE-SIZE)}
     {:name      "Lookup raw value with given conversion (deep)"
      :operation lookup-scalar
      :sampler   #(gen/sample (gen-scalar-lookup deep) SAMPLE-SIZE)}]))

(def merging-tasks
  (let [small {:densities {0 {:min-elements 5
                              :max-elements 20}
                           1 {:min-elements 5
                              :max-elements 10}}}
        large {:densities {0 {:min-elements 30
                              :max-elements 40}
                           1 {:min-elements 5
                              :max-elements 10}
                           2 {:min-elements 3
                              :max-elements 5}}}]
    [{:name      "Merge json objects (small)"
      :operation merge-json
      :sampler   #(gen/sample (gen-merge-for :object small) SAMPLE-SIZE)}
     {:name      "Merge json objects (large)"
      :operation merge-json
      :sampler   #(gen/sample (gen-merge-for :object large) SAMPLE-SIZE)}

     {:name      "Merge json arrays (small)"
      :operation merge-json
      :sampler   #(gen/sample (gen-merge-for :array small) SAMPLE-SIZE)}
     {:name      "Merge json arrays (large)"
      :operation merge-json
      :sampler   #(gen/sample (gen-merge-for :array large) SAMPLE-SIZE)}]))

(def updating-tasks
  (let [small {:densities {0 {:min-elements 5
                              :max-elements 20}
                           1 {:min-elements 5
                              :max-elements 10}}}
        large {:densities {0 {:min-elements 30
                              :max-elements 40}
                           1 {:min-elements 5
                              :max-elements 10}
                           2 {:min-elements 3
                              :max-elements 5}}}]
    [{:name      "Update with partial given conversion (small)"
      :operation update-json-partial
      :sampler   #(gen/sample (gen-scalar-update small) SAMPLE-SIZE)}
     {:name      "Update with partial given conversion (large)"
      :operation update-json-partial
      :sampler   #(gen/sample (gen-scalar-update large) SAMPLE-SIZE)}

     {:name      "Update with total given conversion (small)"
      :operation update-json-total
      :sampler   #(gen/sample (gen-scalar-update small) SAMPLE-SIZE)}
     {:name      "Update with total given conversion (large)"
      :operation update-json-total
      :sampler   #(gen/sample (gen-scalar-update large) SAMPLE-SIZE)}]))

(def default-tasks
  (concat insertion-tasks
          lookup-tasks
          merging-tasks
          updating-tasks))

(defn -main [& args]
  (apply u/benchmark-out! default-tasks))