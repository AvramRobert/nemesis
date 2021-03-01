(ns nemesis.transform_test
  (:require [clojure.test :refer :all]
            [nemesis.util :refer :all]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen])
  (:import (json.coerce DefaultConverters)))

(defspec isomorphism 100
  (for-all [json-clj (gen-clj-json {:max-depth    2
                                    :max-elements 3})]
    (let [computed (-> json-clj (clj->nem) (nem->clj))
          expected json-clj]
      (is (= expected computed)))))

(defspec empty-association 100
  (for-all [key   (gen/not-empty gen/string-alphanumeric)
            value  gen/small-integer]
    (let [json-clj {}
          computed (transform (insert-j value [key]) json-clj)
          expected (assoc json-clj key value)]
      (is (= expected computed)))))

(defspec empty-deep-association 100
  (for-all [keys   (gen/not-empty (gen/list (gen/not-empty gen/string-alphanumeric)))
            value  gen/small-integer]
    (let [json-clj {}
          computed (transform (insert-j value keys) json-clj)
          expected (assoc-in json-clj keys value)]
      (is (= expected computed)))))

(defspec association 100
  (for-all [json-clj      (gen-map {:max-depth    2
                                    :max-elements 3})
            clj-to-insert (gen-clj-json {:max-depth    2
                                         :max-elements 3})
            key           (gen/not-empty gen/string-alphanumeric)]
    (let [nem-to-insert (clj->nem clj-to-insert)
          computed      (transform (insert-j nem-to-insert [key]) json-clj)
          expected      (assoc json-clj key clj-to-insert)]
      (is (= expected computed)))))

(defspec deep-association-create 100
  (for-all [json-clj      (gen-map {:max-depth    2
                                    :max-elements 3})
            clj-to-insert (gen-clj-json {:max-depth    2
                                         :max-elements 3})
            keys          (-> gen/string-alphanumeric
                              (gen/not-empty)
                              (gen/vector)
                              (gen/not-empty))]
    (let [nem-to-insert (clj->nem clj-to-insert)
          computed      (transform (insert-j nem-to-insert keys) json-clj)
          expected      (assoc-in json-clj keys clj-to-insert)]
      (is (= expected computed)))))

(defspec deep-association-replace 100
  (for-all [json-clj   (gen/not-empty (gen-map {:max-depth    2
                                                :max-elements 3}))
            associatee (gen-clj-json {:max-depth    2
                                      :max-elements 3})]
    (let [keys         (rand-keyseq json-clj)
          n-associatee (clj->nem associatee)
          computed     (transform (insert-j n-associatee keys) json-clj)
          expected     (assoc-in json-clj keys associatee)]
      (is (= expected computed)))))

(defspec dissociation 100
  (for-all [json-clj (gen/not-empty (gen-map {:max-depth    2
                                              :max-elements 3}))]
    (let [all-keys (keys json-clj)
          amount   (rand-int (count all-keys))
          keys     (->> all-keys (shuffle) (take amount))
          computed (transform (remove-j keys) json-clj)
          expected (apply dissoc json-clj keys)]
      (is (= expected computed)))))

(defspec retrieval 100
  (for-all [json-clj (gen/not-empty (gen-map {:max-depth    2
                                              :max-elements 3}))
            arr-clj  (gen/not-empty (gen-arr {:max-depth 2
                                              :max-elements 3}))]
    (let [key        (-> json-clj (keys) (rand-nth))
          index      (rand-int (count arr-clj))
          computed-m (transform (get-j [key]) json-clj)
          computed-a (transform (get-j [index]) arr-clj)
          expected-m (get json-clj key)
          expected-a (get arr-clj index)]
      (is (= expected-m computed-m))
      (is (= expected-a computed-a)))))

(defspec deep-retrieval 100
  (for-all [json-clj (gen/not-empty (gen-map {:max-depth    2
                                              :max-elements 3}))]
    (let [keys     (rand-keyseq json-clj)
          computed (transform (get-j keys) json-clj)
          expected (get-in json-clj keys)]
      (is (= expected computed)))))

(defspec updating 100
  (for-all [json-clj (gen/not-empty (gen-map {:max-depth    2
                                              :max-elements 3
                                              :scalars      [gen/small-integer]}))
            new-key   gen-string-alpha
            new-value gen/nat]
    (let [key      (-> json-clj (keys) (rand-nth))
          elem     (json-clj key)
          updates {:map {:clj #(assoc % new-key new-value)
                         :nem  (update-j (insert-j new-value [new-key]) [key]) }
                   :vec {:clj #(assoc % 0 new-value)
                         :nem  (update-j (insert-j new-value [0]) [key])}
                   :scalar {:clj #(inc %)
                            :nem (update-j DefaultConverters/JSON_TO_LONG
                                           inc
                                           DefaultConverters/LONG_TO_JSON
                                           [key])}}
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
          elem     (get-in json-clj keys)
          updates {:map {:clj #(assoc % new-key new-value)
                         :nem (update-j (insert-j new-value [new-key]) keys)}
                   :vec {:clj #(assoc % 0 new-value)
                         :nem (update-j (insert-j new-value [0]) keys)}
                   :scalar {:clj inc
                            :nem (update-j DefaultConverters/JSON_TO_LONG
                                           inc
                                           DefaultConverters/LONG_TO_JSON
                                           keys)}}
          f        (fn [api]
                     (cond
                       (map? elem)     (get-in updates [:map api])
                       (vector? elem)  (if (empty? elem)
                                         identity
                                         (get-in updates [:vec api]))
                       :else           (get-in updates [:scalar api])))
          computed (transform (f :nem) json-clj)
          expected (update-in json-clj keys (f :clj))]
      (is (= expected computed)))))

(defspec merging 100
  (for-all [json-clj-1 (gen/not-empty (gen-map {:max-depth    2
                                                :max-elements 3}))
            json-clj-2 (gen/not-empty (gen-map {:max-depth    2
                                                :max-elements 3}))]
    (let [computed (transform merge-j json-clj-1 json-clj-2)
          expected (merge json-clj-1 json-clj-2)]
      (is (= expected computed)))))