(defproject nemesis "0.1.0-SNAPSHOT"
  :description "A library for working with JSON through direct manipulation."
  :url "https://github.com/AvramRobert/nemesis"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :java-source-paths ["src"]
  :dependencies [[io.lacuna/bifurcan "0.1.0"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.9.0"]
                                  [org.clojure/test.check "1.1.0"]
                                  [com.fasterxml.jackson.core/jackson-databind "2.9.8"]
                                  [com.typesafe.play/play-json_2.13 "2.7.4"]
                                  [com.google.code.gson/gson "2.8.6"]
                                  [criterium "0.4.6"]
                                  [cheshire "5.9.0"]]
                   :resource-paths ["test/resources"]}})
