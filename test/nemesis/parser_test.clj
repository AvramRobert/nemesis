(ns nemesis.parser_test
  (:require [clojure.test :refer :all]
            [nemesis.util :refer :all]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]])
  (:import json.parser.Parser))


(defspec isomorphic-conversion 100
  (for-all [json-clj (gen-clj-json {:max-depth    4
                                    :max-elements 7})]
    (let [json   (clj->json json-clj)
          result (-> json (Parser/parse) (.json))]
      (is (= (nem->clj result) json-clj)))))

(defspec invalidity-detection 100
  (for-all [json-string gen-faulty-json-string]
    (->> json-string (Parser/parse) (.succeeded) (= false) (is))))