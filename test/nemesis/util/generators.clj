(ns nemesis.util.generators
  (:require [clojure.test :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.string :refer [join]]))

(declare gen-arr gen-map)

(defn- deeper [{:keys [depth]
                :or {depth 0}
                :as opts}]
  (assoc opts :depth (inc depth)))

(defmacro do-gen [binding & body]
  (let [[[n# b#] & bound] (->> binding (destructure) (partition 2) (reverse))]
    (reduce
      (fn [expr [bn# bb#]]
        `(gen/bind ~bb# (fn [~bn#] ~expr))) `(gen/fmap (fn [~n#] ~@body) ~b#) bound)))

(def gen-bool
  gen/boolean)

(def gen-int
  gen/small-integer)

(def gen-double
  (gen/double* {:infinite? false
                :NaN?      false}))

(def gen-nil
  (gen/return nil))

(def gen-string-alpha
  (->> gen/char-alpha (gen/vector) (gen/fmap #(apply str %)) (gen/not-empty)))

(def ^:private default-scalars
  [gen/small-integer
   gen-nil
   gen-double
   gen/boolean
   gen-string-alpha
   gen/string-alphanumeric])

(defn gen-path [{:keys [min-depth max-depth]
                 :or   {min-depth 1
                        max-depth 4}}]
  (gen/vector
    (gen/resize 10 (gen/not-empty gen/string-alphanumeric))
    min-depth max-depth))

(defn gen-arr [{:keys [depth max-depth max-elements scalars]
                :or {depth   0
                     max-depth 1
                     max-elements 5
                     scalars default-scalars}
                :as opts}]
  (letfn [(rec-arr [scalars]
            (gen/vector (gen/one-of [scalars
                                     (gen-arr (deeper opts))
                                     (gen-map (deeper opts))]) 0 max-elements))]
    (if (> depth max-depth)
      (gen/vector (gen/one-of scalars))
      (rec-arr (gen/recursive-gen rec-arr (gen/one-of scalars))))))

(defn gen-map [{:keys [depth max-depth max-elements scalars]
                :or {depth   0
                     max-depth 1
                     max-elements 5
                     scalars default-scalars}
                :as opts}]
  (letfn [(rec-map [scalars]
            (gen/map gen-string-alpha
                     (gen/one-of [scalars
                                  (gen-arr (deeper opts))
                                  (gen-map (deeper opts))])
                     {:max-elements max-elements}))]
    (if (> depth max-depth)
      (gen/map gen-string-alpha (gen/one-of scalars))
      (rec-map (gen/recursive-gen rec-map (gen/one-of scalars))))))

(defn gen-json [{:keys [scalars]
                 :or   {scalars default-scalars}
                 :as   opts}]
  (gen/one-of (conj scalars (gen-arr opts) (gen-map opts))))

(def gen-faulty-json-string
  (->> [(gen/return " ")
        (gen/return "\n")
        (gen/return "[{}")
        (gen/return "{[]")
        (gen/return "{\"A:")]
       (gen/one-of)
       (gen/vector)
       (gen/not-empty)
       (gen/fmap join)))