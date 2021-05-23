(ns nemesis.transform_test
  (:require [clojure.test :refer :all]
            [nemesis.util.generators :refer :all]
            [nemesis.util.conversion :refer :all]
            [clojure.test.check.properties :refer [for-all]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen])
  (:import (com.ravram.nemesis Json Read Write)))

(defspec isomorphism 100
  (for-all [json-clj (gen-json {:max-depth    2
                                :max-elements 3})]
    (let [computed (-> json-clj (clj->nem) (nem->clj))
          expected json-clj]
      (is (= expected computed)))))

(defspec empty-association 100
  (for-all [keys  (gen/vector gen-string)
            value  gen-int]
    (let [json-clj {}
          computed (insert-jval json-clj value keys)
          expected (if (empty? keys)
                     json-clj
                     (assoc-in json-clj keys value))]
      (is (= expected (result-value computed))))))

(defspec association 100
  (for-all [json-clj      (gen-map {:max-depth    2
                                    :max-elements 3})
            clj-to-insert (gen-json {:max-depth    2
                                     :max-elements 3})
            keys          (gen-path {:max-depth 5})]
    (let [nem      (clj->nem clj-to-insert)
          computed (insert-j json-clj nem keys)
          expected (assoc-in json-clj keys clj-to-insert)]
      (is (= expected (result-value computed))))))

(defspec replacement 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3})
            new-json (gen-json {:max-depth    2
                                :max-elements 3})]
    (let [keys     (rand-keyseq json-clj)
          nem      (clj->nem new-json)
          computed (insert-j json-clj nem keys)
          expected (assoc-in json-clj keys new-json)]
      (is (= expected (result-value computed))))))

(defspec raw-association 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3})
            value    (gen-json {})
            key      gen-string]
           (let [computed (insert-jval json-clj (clj->java value) [key])
                 expected (assoc json-clj key value)]
             (is (= expected (result-value computed))))))

(defspec dissociation 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3})]
    (let [all-keys (keys json-clj)
          amount   (rand-int (count all-keys))
          keys     (->> all-keys (shuffle) (take amount))
          computed (remove-j json-clj keys)
          expected (apply dissoc json-clj keys)]
      (is (= expected (result-value computed))))))

(defspec retrieval 100
  (for-all [obj-clj (gen-map {:max-depth    2
                              :min-elements 1
                              :max-elements 3})
            arr-clj (gen-arr {:max-depth    2
                              :min-elements 1
                              :max-elements 3})]
    (let [key        (-> obj-clj (keys) (rand-nth))
          index      (rand-int (count arr-clj))
          computed-o (get-j obj-clj [key])
          computed-a (get-j arr-clj [index])
          expected-o (get obj-clj key)
          expected-a (get arr-clj index)]
      (is (= expected-o (result-value computed-o)))
      (is (= expected-a (result-value computed-a))))))

(defspec deep-retrieval 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3})]
    (let [keys     (rand-keyseq json-clj)
          computed (get-j json-clj keys)
          expected (get-in json-clj keys)]
      (is (= expected (result-value computed))))))

(defspec updating 100
  (for-all [json-clj (gen-map {:max-depth    2
                               :min-elements 1
                               :max-elements 3
                               :scalars      [gen-int]})
            new-key   gen-string
            new-value gen/nat]
    (let [keys     (rand-keyseq json-clj)
          elem     (get-in json-clj keys)
          updates  {:map    {:clj (fn [m] (update-in m keys #(assoc % new-key new-value)))
                             :nem (fn [m] (update-j m #(insert-jval % new-value [new-key]) keys))}
                    :vec    {:clj (fn [m] (update-in m keys #(assoc % 0 new-value)))
                             :nem (fn [m] (update-j m #(insert-jval % new-value [0]) keys))}
                    :scalar {:clj (fn [m] (update-in m keys inc))
                             :nem (fn [m] (update-jval m
                                                       Read/LONG
                                                       inc
                                                       Write/LONG
                                                       keys))}}
          fn-for   (fn [api]
                     (cond
                       (map? elem)    (get-in updates [:map api])
                       (vector? elem) (if (empty? elem)
                                        identity
                                        (get-in updates [:vec api]))
                       :else          (get-in updates [:scalar api])))
          computed ((fn-for :nem) json-clj)
          expected ((fn-for :clj) json-clj)]
      (is (= expected (result-value computed))))))

(defspec merging-objects 100
  (for-all [json-clj-1 (gen-map {:max-depth    2
                                 :min-elements 1
                                 :max-elements 3})
            json-clj-2 (gen-map {:max-depth    2
                                 :min-elements 1
                                 :max-elements 3})]
    (let [computed (merge-j json-clj-1 json-clj-2)
          expected (merge json-clj-1 json-clj-2)]
      (is (= expected (result-value computed))))))

(defspec merging-arrays 100
  (for-all [json-clj-1 (gen-arr {:max-depth    2
                                 :min-elements 1
                                 :max-elements 3})
            json-clj-2 (gen-arr {:max-depth    2
                                 :min-elements 1
                                 :max-elements 3})]
           (let [computed (merge-j json-clj-1 json-clj-2)
                 expected (concat json-clj-1 json-clj-2)]
             (is (= expected (result-value computed))))))

(defspec reducing 100
  (let [opts {:min-elements 1
              :max-elements 10
              :scalars      [gen-int]}
        add  (fn [i _ v]
               (-> v (.as Read/LONG) (.map (function-1 #(+ i %)))))]
    (for-all [json-obj (gen-shallow-map opts)
              json-arr (gen-shallow-arr opts)]
       (let [computed-obj (reduce-obj-j json-obj 0 add)
             expected-obj (reduce (fn [i [_ v]] (+ i v)) 0 json-obj)
             computed-arr (reduce-arr-j json-arr 0 add)
             expected-arr (reduce + 0 json-arr)]
         (is (= expected-obj (.value computed-obj)))
         (is (= expected-arr (.value computed-arr)))))))

(defspec short-circuiting-object-reduction 100
  (let [opts {:min-elements 1
              :max-elements 10
              :scalars      [gen-int]}
        fail (fn [_ _ v]
               (.as v Read/BOOLEAN))]
    (for-all [json-obj (gen-shallow-map opts)
              json-arr (gen-shallow-arr opts)]
      (let [computed-obj (reduce-obj-j json-obj 0 fail)
            computed-arr (reduce-arr-j json-arr 0 fail)]
        (is (.isFailure computed-obj))
        (is (.isFailure computed-arr))))))

;; these operations are arbitrary.
;; None of them should actually work irrespective of their sanity
(defspec failure-propagation 100
  (for-all [faulty gen-faulty-json-string
            path     (gen-path {:max-depth 2})]
    (let [json           (Json/parse faulty)
          mapped-json    (.as json Read/INT)
          inserted-json  (.insertJson json Json/empty (in path))
          inserted-value (.insertValue json 1 (in path))
          retrieved-json (.getJson json (in path))
          merged-json    (.mergeJson json json)
          updated-json   (.updateJson json
                                      (function-1 #(.insertJson % Json/empty (in path)))
                                      (in path))
          updated-value  (.updateValue json
                                       Read/INT
                                       (function-1 inc)
                                       Write/INT
                                       (in path))
          reduced-obj    (.reduceObj json 0 (function-3 (fn [b _ _] (inc b))))
          reduced-arr    (.reduceArr json 0 (function-3 (fn [b _ _] (inc b))))]
      (is (= json inserted-json))
      (is (= json inserted-value))
      (is (= json retrieved-json))
      (is (= json merged-json))
      (is (= json updated-json))
      (is (= json updated-value))
      (is (= (.affix json) mapped-json))
      (is (= (.affix json) reduced-obj))
      (is (= (.affix json) reduced-arr)))))