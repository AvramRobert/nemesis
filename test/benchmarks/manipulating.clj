(ns benchmarks.manipulating
  (:require [clojure.test :refer :all]
            [nemesis.util.generators :refer :all]
            [nemesis.util.conversion :refer :all]
            [clojure.test.check.generators :as gen]
            [criterium.core :as c])
  (:import (json JsonOps)
           (json.model JsonT In Json)))

(def ^:const SAMPLE-SIZE 1000)

(def empty-json-t (.transform JsonOps/empty))

(defn insert-json [jsons]
  (let [result ^JsonT (reduce
                        (fn [nem {:keys [in json]}]
                          (.insert nem json in)) empty-json-t jsons)]
    (.affix result)))

(defn lookup-json [jsons]
  (doseq [{:keys [^In in ^Json json]} jsons]
    (-> json (.transform) (.get in) (.affix))))

(defn merge-json [jsons]
  (let [result JsonT (reduce
                       (fn [json-a json-b]
                         (.merge json-a json-b)) empty-json-t jsons)]
    (.affix result)))

(defn gen-insertion-with [f]
  (do-gen [json (gen-json {:scalars [gen-int
                                     gen-double
                                     gen-bool
                                     gen-string]})
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
  (let [array (gen-arr {:max-depth 3
                        :max-elements 10})
        map   (gen-arr {:max-depth 3
                        :max-elements 10})]
    (get {:array  array
          :object map} type)))

;; FIXME: These should have progressive sizes
;; E.G: inserting small, medium, large json.
;; where small depth = [0, 2], elements = [0, 10]
;;       medium depth = [3, 5], elements = [10, 20]
;;       large  depth = [5, 10], elements = [20, 25]
(def default-tasks
  [{:name      "Inserting json"
    :operation insert-json
    :samples   (gen/sample (gen-insertion-with clj->nem) SAMPLE-SIZE)}
   {:name      "Inserting raw values"
    :operation insert-json
    :samples   (gen/sample (gen-insertion-with clj->java) SAMPLE-SIZE)}
   {:name      "Lookups"
    :operation lookup-json
    :samples   (gen/sample gen-lookup SAMPLE-SIZE)}
   {:name      "Merging objects"
    :operation merge-json
    :samples (gen/sample (gen-merge-with :object) SAMPLE-SIZE)}
   {:name      "Merging arrays"
    :operation merge-json
    :samples (gen/sample (gen-merge-with :array) SAMPLE-SIZE)}])

(defn benchmark! [tasks]
  (doseq [{name   :name
           op     :operation
           sample :samples} tasks]
    (println "Benchmarking: " name)
    (c/bench (op sample))))