(defproject com.ravram/nemesis "0.1.1-SNAPSHOT"
  :description "A library for working with JSON as one would with a normal data-structure"
  :url "https://github.com/AvramRobert/nemesis"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :java-source-paths ["src"]
  :dependencies [[io.lacuna/bifurcan "0.1.0"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.3"]
                                  [org.clojure/test.check "1.1.0"]
                                  [com.fasterxml.jackson.core/jackson-databind "2.10.2"]
                                  [com.typesafe.play/play-json_2.13 "2.9.2"]
                                  [com.google.code.gson/gson "2.8.6"]
                                  [criterium "0.4.6"]
                                  [cheshire "5.10.0"]]
                   :resource-paths ["test/resources"]}}
  :javac-options ["-target" "1.8" "-source" "1.8"]
  :scm {:url "git@github.com:AvramRobert/nemesis.git"}
  ;; use the token from Nexus UI, not your jira credentials directly
  :deploy-repositories {"releases"  {:url   "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                                     :creds :gpg}
                        "snapshots" {:url   "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                                     :creds :gpg}}
  :pom-addition [:developers [:developer
                              [:name "Robert M. Avram"]
                              [:url "https://github.com/AvramRobert"]
                              [:email "am11.robert@yahoo.com"]]]
  :classifiers {:javadoc {:java-source-paths ^:replace []
                          :source-paths      ^:replace []
                          :resource-paths    ^:replace ["javadoc"]}
                :sources {:source-paths   ^:replace ["src"]
                          :resource-paths ^:replace []}})
