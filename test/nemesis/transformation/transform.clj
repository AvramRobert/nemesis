(ns nemesis.transformation.transform
  (:require [clojure.test :refer :all]
            [nemesis.util :refer :all]
            [clojure.test.check :refer [quick-check]]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]]))

(defspec isomorphic-conversion 100
  (for-all [json-clj (gen-clj-json {:max-depth    4
                                    :max-elements 7})]
    (-> json-clj (clj->nem) (nem->clj) (= json-clj) (is))))