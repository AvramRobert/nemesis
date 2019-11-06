(ns nemesis.transform_test
  (:require [clojure.test :refer :all]
            [nemesis.util :refer :all]
            [clojure.test.check :refer [quick-check]]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]))

(defspec isomorphic-conversion 100
  (for-all [json-clj (gen-clj-json {:max-depth    4
                                    :max-elements 7})]
    (-> json-clj (clj->nem) (nem->clj) (= json-clj) (is))))

(defspec association 1
  (for-all [json-clj   (gen-map {:max-depth    4
                                 :max-elements 7})
            associatee (gen-clj-json {:max-depth    2
                                      :max-elements 3})
            key        gen/nat]
    (-> (clj->json json-clj)
        (json->nem)
        (.json)
        (.transform)
        (.assoc (str key) (clj->nem associatee))
        (.affix))))