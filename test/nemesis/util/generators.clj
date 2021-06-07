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

(def gen-exp-num
  (do-gen [num-sign (gen/elements ["-" "+"])
           before   gen/nat
           dot      (gen/elements ["." ""])
           middle   gen/nat
           exp      (gen/elements ["E" "e"])
           exp-sign (gen/elements ["-" "+" ""])
           after    gen/nat]
    (Double/parseDouble (str num-sign before dot middle exp exp-sign after))))

(def gen-double
  (gen/double* {:infinite? false
                :NaN?      false}))

(def gen-num
  (gen/one-of [gen-int gen-exp-num gen-double]))

(def gen-nil
  (gen/return nil))

;; think about using string-ascii here
(def gen-string
  (gen/not-empty gen/string-alphanumeric))

(def default-scalars
  [gen-num
   gen-bool
   gen-nil
   gen-string])

(defn gen-path [{:keys [min-depth max-depth]
                 :or   {min-depth 1
                        max-depth 4}}]
  (gen/vector
    (gen/resize 10 (gen/not-empty gen-string))
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

(defn- extract-densities [opts]
  (let [current-depth        (:current-depth opts 0)
        default-min-elements (:min-elements opts 0)
        default-max-elements (:max-elements opts 5)
        min-elements         (get-in opts [:densities current-depth :min-elements] default-min-elements)
        max-elements         (get-in opts [:densities current-depth :max-elements] default-max-elements)
        [min max] (if (> min-elements max-elements)
                    [min-elements min-elements]
                    [min-elements max-elements])]
    {:min-elements min
     :max-elements max}))

(defn- extract-depth [{:keys [densities
                              min-depth
                              max-depth]}]
  (let [min-depth (or min-depth 0)
        max-depth (or max-depth (some->> densities (keys) (apply max)) min-depth)
        [min max] (if (> min-depth max-depth)
                    [min-depth min-depth]
                    [min-depth max-depth])]
    {:min-depth min
     :max-depth max}))

(defn gen-arr [{:keys [scalars]
                :or   {scalars default-scalars}
                :as   opts}]
  "Generates vectors akin to json arrays.
   Supports the following options:
   - :densities = {0 {:min-elements 2, :max-elements 5},
                   1 {:min-elements 5}}
     -> determines how many elements should be generated at a particular depth in the json. The depth is indicated by the key.
     -> in the above example, it is stated that the root level of the json should have between 2 and 5 elements
   - :min-elements = positive number
     -> determines the default number of minimal elements a json should have, shouldn't there be any specified in a density at any depth
     -> only used when there's no density defined for a particular depth
   - :max-elements = positive number
     -> determines the default number of maximal elements a json should have, shouldn't there be any specified in a density at any depth
     -> only used when there's no density defined for a particular depth
   - :min-depth = positive number
     -> determines the minimal depth a json should have
     -> if this is not specified, the largest depth for which a density is specified is taken, otherwise it's 0
   - :max-depth = positive number
     -> determines the maximal depth a json should have
     -> if this is not specified, the largest depth for which a density is specified is taken, otherwise it's 0
   - :scalars = list of generators
     -> determines which generators to use for creating scalar values"
  (let [current-depth (:current-depth opts 0)               ; track the current-depth to avoid generating very deep json
        {:keys [min-depth
                max-depth]} (extract-depth opts)
        {:keys [min-elements
                max-elements]} (extract-densities opts)
        rec-arr       (fn [scalars]
                        (if (<= current-depth min-depth)
                          (gen/vector (gen-map (inc-depth opts)) min-elements max-elements)
                          (gen/vector (gen/one-of [scalars
                                                   (gen-arr (inc-depth opts))
                                                   (gen-map (inc-depth opts))]) min-elements max-elements)))]
    (if (>= current-depth max-depth)
      (gen/vector (gen/one-of scalars) min-elements max-elements)
      (rec-arr (gen/recursive-gen rec-arr (gen/one-of scalars))))))

;; Even the array generator could actually pick between a map or an array generator in its 'min-depth' case.
;; Making it however default to a map generator guarantees its termination.
;; Otherwise, if it gets both, then assuming `one-of`s probability distribution is equal, it would have a 0.5 chance of terminating
;; The other 0.5 being it will continuously create arrays, never going 1 level deeper.
(defn gen-map [{:keys [scalars]
                :or   {scalars default-scalars}
                :as   opts}]
  "Generates maps akin to json objects.
   Supports the following options:
   - :densities = {0 {:min-elements 2, :max-elements 5},
                   1 {:min-elements 5}}
     -> determines how many elements should be generated at a particular depth in the json. The depth is indicated by the key.
     -> in the above example, it is stated that the first level of the json should have between 2 and 5 elements
   - :min-elements = positive number
     -> determines the default number of minimal elements a json should have, shouldn't there be any specified in a density at any depth
     -> only used when there's no density defined for a particular depth
   - :max-elements = positive number
     -> determines the default number of maximal elements a json should have, shouldn't there be any specified in a density at any depth
     -> only used when there's no density defined for a particular depth
   - :min-depth = positive number
     -> determines the minimal depth a json should have
     -> if this is not specified, the largest depth for which a density is specified is taken, otherwise it's 0
   - :max-depth = positive number
     -> determines the maximal depth a json should have
     -> if this is not specified, the largest depth for which a density is specified is taken, otherwise it's 0
   - :scalars = list of generators
     -> determines which generators to use for creating scalar values"
  (let [current-depth (:current-depth opts 0)               ; track the current-depth to avoid generating very deep json
        {:keys [min-depth
                max-depth]} (extract-depth opts)
        map-opts      (extract-densities opts)
        rec-map       (fn [scalars]
                        (if (<= current-depth min-depth)
                          (gen/map gen-string (gen/one-of [(gen-arr opts)
                                                           (gen-map (inc-depth opts))]) map-opts)
                          (gen/map gen-string (gen/one-of [scalars
                                                           (gen-arr (inc-depth opts))
                                                           (gen-map (inc-depth opts))]) map-opts)))]
    (if (>= current-depth max-depth)
      (gen/map gen-string (gen/one-of scalars) map-opts)
      (rec-map (gen/recursive-gen rec-map (gen/one-of scalars))))))

(defn gen-shallow-map [{:keys [scalars]
                        :or   {scalars default-scalars}
                        :as   opts}]
  (->> opts (extract-densities) (gen/map gen-string (gen/one-of scalars))))

(defn gen-shallow-arr [{:keys [scalars]
                        :or   {scalars default-scalars}
                        :as   opts}]
  (let [{:keys [min-elements max-elements]} (extract-densities opts)]
    (gen/vector (gen/one-of scalars) min-elements max-elements)))

(defn gen-json [{:keys [scalars]
                 :or   {scalars default-scalars}
                 :as   opts}]
  (gen/one-of (conj scalars (gen-arr opts) (gen-map opts))))

(def gen-faulty-json-string
  (->> [(gen/return " ")
        #_(gen/return "\"ab")
        (gen/return "\n")
        (gen/return "{")
        (gen/return "[")
        (gen/return "[{}")
        (gen/return "{[]")
        (gen/elements (reductions str "" "tru"))
        (gen/elements (reductions str "" "fals"))
        (gen/elements (reductions str "" "nul"))
        (gen/return "{\"A:")
        (gen/return "{\"A:1,}")
        (gen/return "[1,]")]
       (gen/one-of)
       (gen/vector)
       (gen/not-empty)
       (gen/fmap join)))