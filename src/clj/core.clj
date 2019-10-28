(ns core
  (:require [criterium.core :as c]
            [cheshire.core :as j])
  (:import com.fasterxml.jackson.databind.ObjectMapper)
  (:import play.api.libs.json.Json)
  (:import com.google.gson.JsonParser))

(defn parser-cheshire [json]
  "~8 ms"
  (c/with-progress-reporting (c/bench (j/parse-string-strict json))))

(defn parser-play [json]
  "~7 ms"
  (c/with-progress-reporting (c/bench (Json/parse ^String json))))

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

(defmacro try-out [f]
  `(let [json# (slurp "/home/robert/Downloads/generated.json")]
     (Thread/sleep 10000)
     (dorun (repeatedly 10000 #(~f json#)))))

(defn benchmark [f]
  (let [json (slurp "/home/robert/Downloads/generated.json")]
    (f json)))

(defn -main [& args]
  (benchmark parser-dynamic)
  #_(let [json# (slurp "/home/robert/Downloads/generated.json")]
      (Thread/sleep 10000)
      (dorun (repeatedly 10000 #(json.parser.Parser/parse json#)))))