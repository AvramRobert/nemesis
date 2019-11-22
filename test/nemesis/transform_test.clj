(ns nemesis.transform_test
  (:require [clojure.test :refer :all]
            [nemesis.util :refer :all]
            [clojure.test.check :refer [quick-check]]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]))

(defn nem-map [f json-clj]
  (let [res (->> (clj->nem json-clj)
                 (.transform)
                 (f)
                 (.affix))]
    (if (.isRight res)
      (nem->clj (.value res))
      (clojure.pprint/pprint res))))

(defn- keyseq [form]
  (cond
    (and (map? form)
         (not-empty form)) (let [k (->> form (keys) (rand-nth))]
                                 (cons k (lazy-seq (keyseq (get form k)))))
    (and (vector? form)
         (not-empty form)) (let [i (rand-int (count form))]
                                 (cons i (lazy-seq (keyseq (nth form i)))))
    :else '()))

(defn rand-keyseq [form]
  (vec (keyseq form)))

(defspec isomorphism 100
  (for-all [json-clj (gen-clj-json {:max-depth    2
                                    :max-elements 3})]
    (let [computed (-> json-clj (clj->nem) (nem->clj))
          expected json-clj]
      (is (= expected computed)))))

(defspec association 100
  (for-all [json-clj   (gen-map {:max-depth    2
                                 :max-elements 3})
            associatee (gen-clj-json {:max-depth    2
                                      :max-elements 3})
            key        (gen/not-empty gen/string-alphanumeric)]
    (let [n-associatee (clj->nem associatee)
          computed     (nem-map #(.assoc % key n-associatee) json-clj)
          expected     (assoc json-clj key associatee)]
      (is (= expected computed)))))

(defspec deep-association-create 100
  (for-all [json-clj   (gen-map {:max-depth    2
                                 :max-elements 3})
            associatee (gen-clj-json {:max-depth    2
                                      :max-elements 3})
            keys       (-> gen/string-alphanumeric (gen/not-empty) (gen/vector) (gen/not-empty))]
    (let [n-keys       (into-array java.lang.Object keys)
          n-associatee (clj->nem associatee)
          computed     (nem-map #(.assocIn % n-associatee n-keys) json-clj)
          expected     (assoc-in json-clj keys associatee)]
      (is (= expected computed)))))

(defspec deep-association-replace 100
  (for-all [json-clj   (gen/not-empty (gen-map {:max-depth    2
                                                :max-elements 3}))
            associatee (gen-clj-json {:max-depth    2
                                      :max-elements 3})]
    (let [keys         (rand-keyseq json-clj)
          n-associatee (clj->nem associatee)
          n-keys       (into-array java.lang.Object keys)
          computed     (nem-map #(.assocIn % n-associatee n-keys) json-clj)
          expected     (assoc-in json-clj keys associatee)]
      (is (= expected computed)))))
