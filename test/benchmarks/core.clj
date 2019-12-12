(ns benchmarks.core
  (:require [criterium.core :as c]
            [cheshire.core :as j]
            [clojure.test :refer [deftest]]
            [clojure.java.io :as jio])
  (:import com.fasterxml.jackson.databind.ObjectMapper)
  (:import com.google.gson.JsonParser
           (java.nio.file Files Paths)))

(defn parser-cheshire [json]
  "~8 ms"
  (c/with-progress-reporting (c/bench (j/parse-string-strict json))))

(defn parser-play [json]
  "~7 ms"
  (c/with-progress-reporting (c/bench (play.api.libs.json.Json/parse ^String json))))

(defn parser-gson [json]
  "~7 ms"
  (c/with-progress-reporting (c/bench (JsonParser/parseString json))))

(defn parser-typed [json]
  "~6 ms"
  (c/with-progress-reporting (c/bench (json.parser.Parser/parse json))))

(defn parser-jackson [json]
  "~4 ms"
  (let [mapper ^ObjectMapper (ObjectMapper.)]
    (c/with-progress-reporting (c/bench (.readTree mapper ^String json)))))

(defn benchmark [f]
  (-> (jio/resource "resources/sample.json")
      (.toURI)
      (Paths/get)
      (Files/readAllBytes)
      (String.)
      (f)))

#_(deftest run-benchmark
  (benchmark parser-play))