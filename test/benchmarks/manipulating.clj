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

(def gen-nem
  (do-gen [map  (gen-map {:max-depth    2
                          :max-elements 3})
           path (gen-path {:max-depth 7})]
    {:in   (in path)
     :json (clj->nem map)}))

(def gen-raw-vals
  (do-gen [scalar (gen/one-of [gen-double
                               gen-int
                               gen-string-alpha
                               gen-bool])
           path   (gen-path {:max-depth 7})]
    {:in   (in path)
     :json scalar}))

(def default-tasks
  [{:name      "Inserting nemesis json"
    :operation insert-json
    :samples   (gen/sample gen-nem)}
   {:name      "Inserting raw values"
    :operation insert-json
    :samples   (gen/sample gen-raw-vals)}])

(defn benchmark! [tasks]
  (doseq [{name   :name
           op     :operation
           sample :samples} tasks]
    (println "Benchmarking: " name)
    (c/bench (op sample))))