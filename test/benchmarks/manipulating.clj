(ns benchmarks.manipulating
  (:require [clojure.test :refer :all]
            [nemesis.util.generators :refer :all]
            [nemesis.util.conversion :refer :all]
            [clojure.test.check.generators :as gen]
            [criterium.core :as c])
  (:import (json JsonOps)
           (json.model JsonT)))

(defn insert-json [jsons]
  (let [result ^JsonT (reduce
                        (fn [nem {:keys [in json]}]
                          (.insert nem json in)) (.transform JsonOps/empty) jsons)]
    (.affix result)))

(defn gen-json-with [f]
  (do-gen [json (gen-json {:scalars [gen-int
                                     gen-double
                                     gen-bool
                                     gen-string]})
           path (gen-path {:max-depth 7})]
    {:in   (in path)
     :json (f json)}))

(def default-tasks
  [{:name      "Inserting nemesis json"
    :operation insert-json
    :samples   (gen/sample (gen-json-with clj->nem))}
   {:name      "Inserting raw values"
    :operation insert-json
    :samples   (gen/sample (gen-json-with clj->java))}])

(defn benchmark! [tasks]
  (doseq [{name   :name
           op     :operation
           sample :samples} tasks]
    (println "Benchmarking: " name)
    (c/bench (op sample))))