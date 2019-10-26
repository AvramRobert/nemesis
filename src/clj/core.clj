(ns core
  (:require [criterium.core :as c]
            [cheshire.core :as j])
  (:import json.parser.Parser))


(defn -main [& args]
  (let [json (slurp "/home/robert/Downloads/generated.json")]
    (c/with-progress-reporting (c/bench (j/parse-string-strict json)))))