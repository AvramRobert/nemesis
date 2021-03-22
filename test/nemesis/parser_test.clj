(ns nemesis.parser_test
  (:require [clojure.test :refer :all]
            [nemesis.util.conversion :refer :all]
            [nemesis.util.generators :refer :all]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]])
  (:import com.ravram.nemesis.parser.Parser))

(defspec isomorphic-conversion 100
  (for-all [json-clj (gen-json {:max-depth    3
                                :max-elements 4})]
    (let [json   (clj->json json-clj)
          result (-> json (Parser/parse) (.value))]
      (is (= (nem->clj result) json-clj)))))

(defspec fault-detection 100
  (for-all [json-string gen-faulty-json-string]
    (->> json-string (Parser/parse) (.isRight) (= false) (is))))