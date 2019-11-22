(ns nemesis.transform_test
  (:require [clojure.test :refer :all]
            [nemesis.util :refer :all]
            [clojure.test.check :refer [quick-check]]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]))

(defn nem-map [f json-clj]
  (let [res (->> (clj->nem json-clj)
                 (.transform)
                 (f)
                 (.affix))]
    (if (.isRight res)
      (nem->clj (.value res))
      (report {:type :fail
               :error (.error res)}))))

(defspec isomorphic-conversion 100
  (for-all [json-clj (gen-clj-json {:max-depth    2
                                    :max-elements 3})]
    (-> json-clj (clj->nem) (nem->clj) (= json-clj) (is))))

(defspec association 100
  (for-all [json-clj   (gen-map {:max-depth    2
                                 :max-elements 3})
            associatee (gen-clj-json {:max-depth    2
                                      :max-elements 3})
            key        (gen/not-empty gen/string-alphanumeric)]
    (is (= (nem-map #(.assoc % (str key) (clj->nem associatee)) json-clj)
           (assoc json-clj (str key) associatee)))))

(defspec deep-association 1
  (for-all [json-clj   (gen-map {:max-depth    2
                                 :max-elements 3})
            associatee (gen-clj-json {:max-depth    2
                                      :max-elements 3})
            keys       (gen/vector (gen/not-empty gen/string-alphanumeric))]
    (is (= (nem-map #(.assocIn % (clj->nem associatee) (into-array keys)) json-clj)
           (assoc-in json-clj keys associatee)))))