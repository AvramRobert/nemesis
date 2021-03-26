(ns nemesis.transform_test
  (:require [clojure.test :refer :all]
            [nemesis.util.generators :refer :all]
            [nemesis.util.conversion :refer :all]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen])
  (:import (com.ravram.nemesis Converters JsonOps)))

(defspec isomorphism 100
  (for-all [json-clj (gen-json {:max-depth    2
                                :max-elements 3})]
    (let [computed (-> json-clj (clj->nem) (nem->clj))
          expected json-clj]
      (is (= expected computed)))))

(defspec empty-association 100
  (for-all [key   gen-string
            value gen-int]
    (let [json-clj {}
          computed (transform (insert-jval value [key]) json-clj)
          expected (assoc json-clj key value)]
      (is (= expected computed)))))

(defspec empty-deep-association 100
  (for-all [keys   (gen/not-empty (gen/list gen-string))
            value  gen-int]
    (let [json-clj {}
          computed (transform (insert-jval value keys) json-clj)
          expected (assoc-in json-clj keys value)]
      (is (= expected computed)))))

(defspec association 100
  (for-all [json-clj      (gen-map {:max-depth    2
                                    :max-elements 3})
            clj-to-insert (gen-json {:max-depth        2
                                     :max-elements 3})
            key           gen-string]
    (let [nem-to-insert (clj->nem clj-to-insert)
          computed      (transform (insert-j nem-to-insert [key]) json-clj)
          expected      (assoc json-clj key clj-to-insert)]
      (is (= expected computed)))))

(defspec deep-association-create 100
  (for-all [json-clj      (gen-map {:max-depth    2
                                    :max-elements 3})
            clj-to-insert (gen-json {:max-depth    2
                                     :max-elements 3})
            keys          (gen-path {:min-depth 1})]
    (let [nem-to-insert (clj->nem clj-to-insert)
          computed      (transform (insert-j nem-to-insert keys) json-clj)
          expected      (assoc-in json-clj keys clj-to-insert)]
      (is (= expected computed)))))

(defspec raw-association 100
  (for-all [json  (gen-map {:max-depth    2
                            :min-elements 1
                            :max-elements 3})
            value (gen-json {})
            key   gen-string]
           (let [computed (transform (insert-jval (clj->java value) [key]) json)
                 expected (assoc json key value)]
             (is (= expected computed)))))

(defspec deep-association-replace 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3})
            new-json (gen-json {:max-depth    2
                                :max-elements 3})]
    (let [keys          (rand-keyseq json-clj)
          nem-to-insert (clj->nem new-json)
          computed      (transform (insert-j nem-to-insert keys) json-clj)
          expected      (assoc-in json-clj keys new-json)]
      (is (= expected computed)))))

(defspec dissociation 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3})]
    (let [all-keys (keys json-clj)
          amount   (rand-int (count all-keys))
          keys     (->> all-keys (shuffle) (take amount))
          computed (transform (remove-j keys) json-clj)
          expected (apply dissoc json-clj keys)]
      (is (= expected computed)))))

(defspec retrieval 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3})
            arr-clj  (gen-arr {:max-depth 2
                               :min-elements 1
                               :max-elements 3})]
    (let [key        (-> json-clj (keys) (rand-nth))
          index      (rand-int (count arr-clj))
          computed-m (transform (get-j [key]) json-clj)
          computed-a (transform (get-j [index]) arr-clj)
          expected-m (get json-clj key)
          expected-a (get arr-clj index)]
      (is (= expected-m computed-m))
      (is (= expected-a computed-a)))))

(defspec deep-retrieval 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3})]
    (let [keys     (rand-keyseq json-clj)
          computed (transform (get-j keys) json-clj)
          expected (get-in json-clj keys)]
      (is (= expected computed)))))

(defspec updating 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3
                               :scalars      [gen-int]})
            new-key   gen-string
            new-value gen/nat]
    (let [key      (-> json-clj (keys) (rand-nth))
          elem     (json-clj key)
          updates  {:map    {:clj #(assoc % new-key new-value)
                             :nem (update-j (insert-jval new-value [new-key]) [key])}
                    :vec    {:clj #(assoc % 0 new-value)
                             :nem (update-j (insert-jval new-value [0]) [key])}
                    :scalar {:clj #(inc %)
                             :nem (update-jval Converters/JSON_TO_LONG
                                               inc
                                               Converters/LONG_TO_JSON
                                               [key])}}
          fn-for   (fn [api]
                     (cond
                       (map? elem) (get-in updates [:map api])
                       (vector? elem) (if (empty? elem)
                                        identity
                                        (get-in updates [:vec api]))
                       :else (get-in updates [:scalar api])))
          computed (transform (fn-for :nem) json-clj)
          expected (update json-clj key (fn-for :clj))]
      (is (= expected computed)))))

(defspec deep-updating 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3
                               :scalars      [gen/small-integer]})
            new-key   gen-string
            new-value gen/nat]
    (let [keys     (rand-keyseq json-clj)
          elem     (get-in json-clj keys)
          updates  {:map    {:clj #(assoc % new-key new-value)
                             :nem (update-j (insert-jval new-value [new-key]) keys)}
                    :vec    {:clj #(assoc % 0 new-value)
                             :nem (update-j (insert-jval new-value [0]) keys)}
                    :scalar {:clj inc
                             :nem (update-jval Converters/JSON_TO_LONG
                                               inc
                                               Converters/LONG_TO_JSON
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

(defspec merging-objects 100
  (for-all [json-clj-1 (gen-map {:max-depth    2
                                 :min-elements 1
                                 :max-elements 3})
            json-clj-2 (gen-map {:max-depth    2
                                 :min-elements 1
                                 :max-elements 3})]
    (let [computed (transform merge-j json-clj-1 json-clj-2)
          expected (merge json-clj-1 json-clj-2)]
      (is (= expected computed)))))

(defspec merging-arrays 100
  (for-all [json-clj-1 (gen-arr {:max-depth    2
                                 :min-elements 1
                                 :max-elements 3})
            json-clj-2 (gen-arr {:max-depth    2
                                 :min-elements 1
                                 :max-elements 3})]
           (let [computed (transform merge-j json-clj-1 json-clj-2)
                 expected (concat json-clj-1 json-clj-2)]
             (is (= expected computed)))))

(defspec reducing 100
  (let [opts          {:min-elements 1
                       :max-elements 10
                       :scalars      [gen-int]}
        add           (fn [i _ v]
                        (-> v (.as Converters/JSON_TO_LONG) (.map (function-1 #(+ i %)))))
        obj-reduction (reduce-obj-j 0 add)
        arr-reduction (reduce-arr-j 0 add)]
    (for-all [json-obj (gen-shallow-map opts)
              json-arr (gen-shallow-arr opts)]
       (let [expected-obj (reduce (fn [i [_ v]] (+ i v)) 0 json-obj)
             expected-arr (reduce + 0 json-arr)]
         (is (= expected-obj (-> json-obj (clj->nem) (.transform) (obj-reduction) (.value))))
         (is (= expected-arr (-> json-arr (clj->nem) (.transform) (arr-reduction) (.value))))))))

(defspec short-circuiting-object-reduction 100
  (let [opts          {:min-elements 1
                       :max-elements 10
                       :scalars      [gen-int]}
        fail          (fn [_ _ v]
                        (.as v Converters/JSON_TO_BOOLEAN))
        obj-reduction (reduce-obj-j 0 fail)
        arr-reduction (reduce-arr-j 0 fail)]
    (for-all [json-obj (gen-shallow-map opts)
              json-arr (gen-shallow-arr opts)]
       (is (.isLeft (-> json-obj (clj->nem) (.transform) (obj-reduction))))
       (is (.isLeft (-> json-arr (clj->nem) (.transform) (arr-reduction)))))))

;; these operations are arbitrary.
;; None of them should actually work irrespective of their sanity
(defspec failure-propagation 100
  (for-all [faulty gen-faulty-json-string
            in     (gen-path {:max-depth 2})]
    (let [json         (JsonOps/parse faulty)
          insert-json  (insert-j JsonOps/empty in)
          insert-value (insert-jval 1 in)
          update-json  (update-j insert-json in)
          update-value (update-jval Converters/JSON_TO_INT inc Converters/INT_TO_JSON in)
          merge-json  #(merge-j % %)
          get-json     (get-j in)
          reduce-json  (reduce-obj-j 0 (fn [b _ _] (inc b)))
          reduce-arr   (reduce-arr-j 0 (fn [b _ _] (inc b)))]
      (is (= json (insert-json json)))
      (is (= json (insert-value json)))
      (is (= json (update-json json)))
      (is (= json (update-value json)))
      (is (= json (merge-json json)))
      (is (= json (get-json json)))
      (is (= (.affix json) (reduce-json json)))
      (is (= (.affix json) (reduce-arr json))))))