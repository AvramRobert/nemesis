(defproject nemesis "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/clj"]
  :java-source-paths ["src/java"]
  :main core
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [io.lacuna/bifurcan "0.1.0"]
                 [criterium "0.4.5"]
                 [cheshire "5.9.0"]
                 [com.fasterxml.jackson.core/jackson-databind "2.9.8"]
                 [com.typesafe.play/play-json_2.13 "2.7.4"]
                 [com.google.code.gson/gson "2.8.6"]]
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.10.0"]]}})
