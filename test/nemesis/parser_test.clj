(ns nemesis.parser_test
  (:require [clojure.test :refer :all]
            [nemesis.util :refer :all]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]])
  (:import json.parser.Parser))


(defspec isomorphic-conversion 100
  (for-all [json-clj (gen-clj-json {:max-depth    4
                                    :max-elements 7})]
    (let [_      (println json-clj)
          json   (clj->json json-clj)
          _      (println json)
          result (Parser/parse json)
          _      (println (str "Parsed: " (.json result)))
          _      (println "=============================")]
      (is (= (nem->clj (.json result)) json-clj)))))
