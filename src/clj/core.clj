(ns core
  (:require [criterium.core :as c]
            [cheshire.core :as j]))


(defn parser-typed [json]
  "5.48 ms"
  (c/with-progress-reporting (c/bench (json.parser.typed.Parser/parse json))))

(defn parser-dynamic [json]
  "6.76 ms"
  (c/with-progress-reporting (c/bench (json.parser.dynamic.Parser/parse json))))

(defn parser-cheshire [json]
  "7.01 ms"
  (c/with-progress-reporting (c/bench (j/parse-string-strict json))))

(defn -main [& args]
  (let [json (slurp "/home/robert/Downloads/generated.json")]
    (parser-dynamic json)))