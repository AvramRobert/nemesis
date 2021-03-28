(ns benchmarks.parsing
  (:require [cheshire.core :as cheshire]
            [clojure.test :refer [deftest]]
            [clojure.java.io :as jio]
            [benchmarks.util :as u])
  (:import (java.nio.file Files Paths)
           (com.google.gson JsonParser)
           (play.api.libs.json Json)
           (com.fasterxml.jackson.databind ObjectMapper)
           (com.ravram.nemesis.parser Parser)))

(defn default-tasks [json]
  (let [default-sampler (constantly [json])]
    [{:name      "Nemesis Parser"
      :operation #(Parser/parse %)
      :sampler   default-sampler}
     {:name      "Cheshire Parser"
      :operation #(cheshire/parse-string-strict %)
      :sampler   default-sampler}
     {:name      "GSON Parser"
      :operation #(JsonParser/parseString %)
      :sampler   default-sampler}
     {:name      "Jackson Parser"
      :operation (let [mapper ^ObjectMapper (ObjectMapper.)]
                   #(.readTree mapper ^String %))
      :sampler   default-sampler}
     {:name      "Play Parser"
      :operation #(Json/parse ^String %)
      :sampler   default-sampler}]))

(defn- read-json! [path]
  (-> (jio/resource path)
      (.toURI)
      (Paths/get)
      (Files/readAllBytes)
      (String.)))

(defn -main [& args]
  (let [tasks (-> "resources/sample.json" (read-json!) (default-tasks))]
    (apply u/benchmark-out! tasks)))