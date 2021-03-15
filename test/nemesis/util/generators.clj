(ns nemesis.util.generators
  (:require [clojure.test :refer :all]
            [clojure.test.check.generators :as gen]
            [clojure.string :refer [join]]))

(declare gen-arr gen-map)

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

(def gen-string
  (->> gen/char-alpha (gen/vector) (gen/fmap #(apply str %)) (gen/not-empty)))

(def default-scalars
  [gen-int
   gen-double
   gen-bool
   gen-nil
   gen-string])

(defn gen-path [{:keys [min-depth max-depth]
                 :or   {min-depth 1
                        max-depth 4}}]
  (gen/vector
    (gen/resize 10 (gen/not-empty gen/string-alphanumeric))
    min-depth max-depth))

(defn- make-path-gen [path json]
  (letfn [(empty-coll? [v] (and (coll? v) (empty? v)))
          (bind-with [gen-pick]
            (gen/bind gen-pick #(make-path-gen (conj path %) (get json %))))]
    (cond
      (empty-coll? json)  (gen/return path)
      (vector? json)      (bind-with (gen/choose 0 (dec (count json))))
      (map? json)         (bind-with (gen/elements (keys json)))
      :else               (gen/return path))))

(defn gen-path-from [json]
  (make-path-gen [] json))

(defn- inc-depth [opts]
  (update opts :current-depth #(-> % (or 0) (inc))))

(defn gen-arr [{:keys [min-depth
                       max-depth
                       min-elements
                       max-elements
                       scalars]
                :or   {min-depth    0
                       max-depth    1
                       min-elements 0
                       max-elements 5
                       scalars      default-scalars}
                :as   opts}]
  (let [current-depth (:current-depth opts 0)               ; track the current-depth to avoid generating very deep json
        rec-arr       (fn [scalars]
                        (if (<= current-depth min-depth)
                          (gen/vector (gen-map (inc-depth opts)) min-elements max-elements)
                          (gen/vector (gen/one-of [scalars
                                                   (gen-arr (inc-depth opts))
                                                   (gen-map (inc-depth opts))]) min-elements max-elements)))]
    (cond
      (> min-depth max-depth)      (gen-arr (assoc opts :max-depth min-depth))
      (>= current-depth max-depth) (gen/vector (gen/one-of scalars) min-elements max-elements)
      :else                        (rec-arr (gen/recursive-gen rec-arr (gen/one-of scalars))))))

;; Even the array generator could actually pick between a map or an array generator in its 'min-depth' case.
;; Making it however default to a map generator guarantees its termination.
;; Otherwise, if it gets both, then assuming `one-of`s probability distribution is equal, it would have a 0.5 chance of terminating
;; The other 0.5 being it will continuously create arrays, never going 1 level deeper.
(defn gen-map [{:keys [min-depth
                       max-depth
                       min-elements
                       max-elements
                       scalars]
                :or   {min-depth    0
                       max-depth    1
                       min-elements 0
                       max-elements 5
                       scalars      default-scalars}
                :as   opts}]
  (let [current-depth (:current-depth opts 0)               ; track the current-depth to avoid generating very deep json
        map-opts      {:min-elements min-elements
                       :max-elements max-elements}
        rec-map       (fn [scalars]
                        (if (<= current-depth min-depth)
                          (gen/map gen-string (gen/one-of [(gen-arr opts)
                                                           (gen-map (inc-depth opts))]) map-opts)
                          (gen/map gen-string (gen/one-of [scalars
                                                           (gen-arr (inc-depth opts))
                                                           (gen-map (inc-depth opts))]) map-opts)))]
    (cond
      (> min-depth max-depth)      (gen-map (assoc opts :max-depth min-depth))
      (>= current-depth max-depth) (gen/map gen-string (gen/one-of scalars) map-opts)
      :else                        (rec-map (gen/recursive-gen rec-map (gen/one-of scalars))))))

(defn gen-json [{:keys [scalars
                        max-depth
                        max-elements]
                 :or   {scalars default-scalars
                        max-depth 1
                        max-elements 5}
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