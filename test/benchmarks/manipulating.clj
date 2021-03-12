(ns benchmarks.manipulating
  (:require [clojure.test :refer :all]
            [nemesis.util.generators :refer :all]
            [nemesis.util.conversion :refer :all]
            [clojure.test.check.generators :as gen]
            [criterium.core :as c])
  (:import (json JsonOps Converters)
           (json.model JsonT In Json)
           (util.function Functions$Function1)
           (json.coerce Convert)))

(def ^:const SAMPLE-SIZE 5)

(def empty-json-t (.transform JsonOps/empty))

(defn convert-from [scalar]
  (cond
    (int? scalar)     Converters/INT_TO_JSON
    (double? scalar)  Converters/DOUBLE_TO_JSON
    (string? scalar)  Converters/STRING_TO_JSON
    (boolean? scalar) Converters/BOOLEAN_TO_JSON
    (nil? scalar)     Converters/NULL_TO_JSON))

(defn convert-to [scalar]
  (cond
    (int? scalar)     Converters/JSON_TO_INT
    (double? scalar)  Converters/JSON_TO_DOUBLE
    (string? scalar)  Converters/JSON_TO_STRING
    (boolean? scalar) Converters/JSON_TO_BOOLEAN
    (nil? scalar)     Converters/JSON_TO_NULL))

(defn update-fn [scalar]
  (cond
    (number? scalar)  (comp str inc)
    (string? scalar)  count
    (boolean? scalar) (comp str not)
    (nil? scalar)     str))

(defn insert-json [jsons]
  (let [result ^JsonT (reduce
                        (fn [nem {:keys [in json]}]
                          (.insertJson nem json in)) empty-json-t jsons)]
    (.affix result)))

(defn insert-val [jsons]
  (let [result ^JsonT (reduce
                        (fn [nem {:keys [in json]}]
                          (.insertValue nem json in)) empty-json-t jsons)]
    (.affix result)))

(defn lookup-json [jsons]
  (doseq [{:keys [^In in ^Json json]} jsons]
    (-> json (.transform) (.getJson in) (.affix))))

(defn merge-json [jsons]
  (let [result ^JsonT (reduce
                       (fn [json-a json-b]
                         (.mergeJson json-a json-b)) empty-json-t jsons)]
    (.affix result)))

(defn update-json-partial [jsons]
  (doseq [{:keys [^Json json
                  ^Convert to
                  ^In in
                  ^Functions$Function1 fn]} jsons]
    (-> json (.transform) (.updateValue to fn in) (.affix))))

(defn update-json-total [jsons]
  (doseq [{:keys [^Json json
                  ^Convert from
                  ^Convert to
                  ^In in
                  ^Functions$Function1 fn]} jsons]
    (-> json (.transform) (.updateValue to fn from in) (.affix))))

(defn gen-insertion-with [f]
  (do-gen [json (gen-json {})
           path (gen-path {:max-depth 7})]
    {:in   (in path)
     :json (f json)}))

(def gen-lookup
  (do-gen [json (gen-map {:max-depth    7
                          :max-elements 3})
           path (gen-path-from json)]
    {:in   (in path)
     :json (clj->nem json)}))

(defn gen-merge-with [type]
  (let [array (gen-arr {:max-depth 7
                        :max-elements 3})
        map   (gen-map {:max-depth 7
                        :max-elements 3})]
    (get {:array  (gen/fmap clj->nem array)
          :object (gen/fmap clj->nem map)} type)))

(def gen-scalar-update
  (do-gen [json  (gen-map {:max-depth    7
                           :max-elements 3})
           path  (gen-path-from json)
           value (gen/one-of default-scalars)]
    (let [f      (update-fn value)
          value' (f value)
          json'  (if (empty? path)
                   json
                   (assoc-in json path value))]
      {:json (clj->nem json')
       :to   (convert-to value)
       :from (convert-from value')
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
    :operation insert-val
    :samples   (gen/sample (gen-insertion-with clj->java) SAMPLE-SIZE)}
   {:name      "Lookup json"
    :operation lookup-json
    :samples   (gen/sample gen-lookup SAMPLE-SIZE)}
   {:name      "Lookup raw value with given convert"
    :operation lookup-json
    :samples   (gen/sample gen-lookup SAMPLE-SIZE)}
   {:name      "Merge json objects"
    :operation merge-json
    :samples (gen/sample (gen-merge-with :object) SAMPLE-SIZE)}
   {:name      "Merge json arrays"
    :operation merge-json
    :samples (gen/sample (gen-merge-with :array) SAMPLE-SIZE)}
   {:name      "Update with partial given convert"
    :operation update-json-partial
    :samples (gen/sample gen-scalar-update SAMPLE-SIZE)}
   {:name      "Update with total given convert"
    :operation update-json-total
    :samples (gen/sample gen-scalar-update SAMPLE-SIZE)}])

(defn benchmark! [tasks]
  (doseq [{name   :name
           op     :operation
           sample :samples} tasks]
    (println "Benchmarking: " name)
    (c/bench (op sample))))