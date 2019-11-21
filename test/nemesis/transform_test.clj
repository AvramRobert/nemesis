(ns nemesis.transform_test
  (:require [clojure.test :refer :all]
            [nemesis.util :refer :all]
            [clojure.test.check :refer [quick-check]]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]))

(defn nem-map [f json-clj]
  (->> (clj->nem json-clj)
       (.transform)
       (f)
       (.affix)
       (.value)
       (nem->clj)))

(defspec isomorphic-conversion 100
  (for-all [json-clj (gen-clj-json {:max-depth    2
                                    :max-elements 3})]
    (-> json-clj (clj->nem) (nem->clj) (= json-clj) (is))))

(defspec association 100
  (for-all [json-clj   (gen-map {:max-depth    2
                                 :max-elements 3})
            associatee (gen-clj-json {:max-depth    2
                                      :max-elements 3})
            key        gen/nat]
    (is (= (nem-map #(.assoc % (str key) (clj->nem associatee)) json-clj)
           (assoc json-clj (str key) associatee)))))