(ns nemesis.parser_test
  (:require [clojure.test :refer :all]
            [nemesis.util.conversion :refer :all]
            [nemesis.util.generators :refer :all]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [cheshire.core :as c])
  (:import (com.ravram.nemesis.parser Parser)))

(defspec isomorphic-conversion 100
  (for-all [json-clj (gen-json {:max-depth    3
                                :max-elements 4})]
    (let [json   (clj->json json-clj)
          result (-> json (Parser/parse) (.value))]
      (is (= (nem->clj result) json-clj)))))

(defspec fault-detection 100
  (for-all [json-string gen-faulty-json-string]
    (is (not (-> json-string (Parser/parse) (.isSuccess))))))

(deftest parses-escpaed-characters
  (let [parse #(-> % (Parser/parse) (.value))
        cases {:quote     "\"\\\"\""
               :backslash "\"\\\\\""
               :newline   "\"\\n\""
               :backspace "\"\\b\""
               :tab       "\"\\t\""
               :form      "\"\\f\""
               :return    "\"\\r\""}]
    (doseq [[case example] cases
            :let [actual-parsed   (parse example)
                  expected-parsed (c/parse-string example)]]
      (is (= expected-parsed (.value actual-parsed)) (str "Check parsing: " case))
      (is (= (c/generate-string expected-parsed) (.encode actual-parsed)) (str "Check encoding: " case)))))