(ns benchmarks.parsing
  (:require [cheshire.core :as j]
            [clojure.test :refer [deftest]]
            [clojure.java.io :as jio]
            [benchmarks.util :as u])
  (:import com.fasterxml.jackson.databind.ObjectMapper)
  (:import com.google.gson.JsonParser
           (java.nio.file Files Paths)))

(defn default-tasks [json]
  (let [default-sampler (constantly [json])]
    [{:name      "Nemesis Parser"
      :operation #(json.parser.Parser/parse %)
      :sampler   default-sampler}
     {:name      "Cheshire Parser"
      :operation #(j/parse-string-strict %)
      :sampler   default-sampler}
     {:name      "GSON Parser"
      :operation #(JsonParser/parseString %)
      :sampler   default-sampler}
     {:name      "Jackson Parser"
      :operation (let [mapper ^ObjectMapper (ObjectMapper.)]
                   #(.readTree mapper ^String %))
      :sampler   default-sampler}
     {:name      "Play Parser"
      :operation #(play.api.libs.json.Json/parse ^String %)
      :sampler   default-sampler}]))

(defn- read-json! [path]
  (-> (jio/resource path)
      (.toURI)
      (Paths/get)
      (Files/readAllBytes)
      (String.)))

#_(deftest run-benchmark
  (let [tasks (-> "resources/sample.json" (read-json!) (default-tasks))]
    (apply u/benchmark-out! tasks)))