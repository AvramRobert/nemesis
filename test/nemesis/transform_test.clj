(ns nemesis.transform_test
  (:require [clojure.test :refer :all]
            [nemesis.util :refer :all]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen])
  (:import (json.data JsonTree JNum)
           (json.coerce Default)))

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
          computed     (transform #(.assoc % key n-associatee) json-clj)
          expected     (assoc json-clj key associatee)]
      (is (= expected computed)))))

(defspec deep-association-create 100
  (for-all [json-clj   (gen-map {:max-depth    2
                                 :max-elements 3})
            associatee (gen-clj-json {:max-depth    2
                                      :max-elements 3})
            keys       (-> gen/string-alphanumeric
                           (gen/not-empty)
                           (gen/vector)
                           (gen/not-empty))]
    (let [n-keys       (into-array Object keys)
          n-associatee (clj->nem associatee)
          computed     (transform #(.assocIn % n-associatee n-keys) json-clj)
          expected     (assoc-in json-clj keys associatee)]
      (is (= expected computed)))))

(defspec deep-association-replace 100
  (for-all [json-clj   (gen/not-empty (gen-map {:max-depth    2
                                                :max-elements 3}))
            associatee (gen-clj-json {:max-depth    2
                                      :max-elements 3})]
    (let [keys         (rand-keyseq json-clj)
          n-associatee (clj->nem associatee)
          n-keys       (into-array Object keys)
          computed     (transform #(.assocIn % n-associatee n-keys) json-clj)
          expected     (assoc-in json-clj keys associatee)]
      (is (= expected computed)))))

(defspec dissociation 100
  (for-all [json-clj (gen/not-empty (gen-map {:max-depth    2
                                              :max-elements 3}))]
    (let [all-keys (keys json-clj)
          amount   (rand-int (count all-keys))
          keys     (->> all-keys (shuffle) (take amount))
          n-keys   (into-array String keys)
          computed (transform #(.dissoc % n-keys) json-clj)
          expected (apply dissoc json-clj keys)]
      (is (= expected computed)))))

(defspec retrieval 100
  (for-all [json-clj (gen/not-empty (gen-map {:max-depth    2
                                              :max-elements 3}))
            arr-clj  (gen/not-empty (gen-arr {:max-depth 2
                                              :max-elements 3}))]
    (let [key        (-> json-clj (keys) (rand-nth))
          index      (rand-int (count arr-clj))
          computed-m (transform #(.get ^JsonTree % ^String key) json-clj)
          computed-a (transform #(.get ^JsonTree % ^Long index) arr-clj)
          expected-m (get json-clj key)
          expected-a (get arr-clj index)]
      (is (= expected-m computed-m))
      (is (= expected-a computed-a)))))

(defspec deep-retrieval 100
  (for-all [json-clj (gen/not-empty (gen-map {:max-depth    2
                                              :max-elements 3}))]
    (let [keys     (rand-keyseq json-clj)
          n-keys   (into-array Object keys)
          computed (transform #(.getIn % n-keys) json-clj)
          expected (get-in json-clj keys)]
      (is (= expected computed)))))

(defspec updating 100
  (for-all [json-clj (gen/not-empty (gen-map {:max-depth    2
                                              :max-elements 3
                                              :scalars      [gen/int]}))
            new-key   gen-string-alpha
            new-value gen/nat]
    (let [key      (-> json-clj (keys) (rand-nth))
          elem     (json-clj key)
          updates {:map {:clj #(assoc % new-key new-value)
                         :nem (fn [tree]
                                (.update tree key (function #(.assoc % new-key new-value))))}
                   :vec {:clj #(assoc % 0 new-value)
                         :nem (fn [tree]
                                (.update tree key (function #(.assoc % 0 new-value))))}
                   :scalar {:clj #(inc %)
                            :nem (fn [tree]
                                   (.update tree key (function #(inc %)) Default/jsonToLong Default/longToJson))}}
          f        (fn [api]
                     (cond
                       (map? elem)     (get-in updates [:map api])
                       (vector? elem)  (if (empty? elem)
                                         identity
                                         (get-in updates [:vec api]))
                       :else           (get-in updates [:scalar api])))
          computed (transform (f :nem) json-clj)
          expected (update json-clj key (f :clj))]
      (is (= expected computed)))))

(defspec deep-updating 100
  (for-all [json-clj (gen/not-empty (gen-map {:max-depth    2
                                              :max-elements 3
                                              :scalars      [gen/int]}))
            new-key   gen-string-alpha
            new-value gen/nat]
    (let [keys     (rand-keyseq json-clj)
          n-keys   (into-array Object keys)
          elem     (get-in json-clj keys)
          updates {:map {:clj #(assoc % new-key new-value)
                         :nem (fn [tree]
                                (.updateIn tree n-keys (function #(.assoc % new-key new-value))))}
                   :vec {:clj #(assoc % 0 new-value)
                         :nem (fn [tree]
                                (.updateIn tree n-keys (function #(.assoc % 0 new-value))))}
                   :scalar {:clj #(inc %)
                            :nem (fn [tree]
                                   (.updateIn tree n-keys (function #(inc %)) Default/jsonToLong Default/longToJson))}}
          f        (fn [api]
                     (cond
                       (map? elem)     (get-in updates [:map api])
                       (vector? elem)  (if (empty? elem)
                                         identity
                                         (get-in updates [:vec api]))
                       :else           (get-in updates [:scalar api])))
          computed (transform (f :nem) json-clj)
          expected (update json-clj key (f :clj))]
      (is (= expected computed)))))