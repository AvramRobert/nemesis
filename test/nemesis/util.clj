(ns nemesis.util
  (:require [cheshire.core :as json]
            [clojure.test.check.generators :as gen])
  (:import json.data.Json
           (json.data JNum JString JObj JArr JEmpty JBool JNull)
           (io.lacuna.bifurcan List Map)))

(deftype WEntry [key value])
(deftype WMap [entries])

(defn wentry? [e]
  (instance? WEntry e))

(defn wmap? [m]
  (instance? WMap m))

(defn- walker [f clj]
  (letfn [(recurse [[k v]]
            (walker f (WEntry. (walker f k) (walker f v))))]
    (cond
      (map? clj)   (->> clj (mapv recurse) (WMap.) (f))
      (coll? clj)  (->> clj (mapv (partial walker f)) (f))
      :else (f clj))))

(defn- coerce [form]
  (letfn [(empty-map? [form]
            (and (map? form) (empty? form)))

          (bifurcan-map [form]
            (reduce (fn [m e]
                      (.put m (str (.key e)) (.value e))) (Map.) form))]
    (cond
      (nil? form)       JNull/instance
      (empty-map? form) JEmpty/instance
      (number? form)    (JNum. form)
      (string? form)    (JString. form)
      (keyword? form)   (JString. (name form))
      (boolean? form)   (if form JBool/jtrue JBool/jfalse)
      (wentry? form)    form
      (wmap? form)      (JObj. (bifurcan-map (.entries form)))
      (vector? form)    (JArr. (List/from form))
      :else (throw (Exception. (format "Illegal JSON type of `%s`" form))))))

(declare gen-arr gen-map)

(def gen-double
  (gen/double* {:infinite? false
                :NaN?      false}))

(def gen-nil
  (gen/return nil))

(def ^:private scalars
  [gen/int
   gen-nil
   gen-double
   gen/boolean
   gen/string-alphanumeric])

(defn deeper [{:keys [depth]
               :or {depth 0}
               :as opts}]
  (assoc opts :depth (inc depth)))

(defn gen-arr [{:keys [depth max-depth max-elements]
                :or {depth 0}
                :as opts}]
  (if (> depth max-depth)
    (gen/vector (gen/one-of scalars))
    (gen/recursive-gen
      (fn [scalars]
        (gen/vector (gen/one-of [scalars
                                 (gen-arr (deeper opts))
                                 (gen-map (deeper opts))]) 0 max-elements))
      (gen/one-of scalars))))

(defn gen-map [{:keys [depth max-depth max-elements]
                :or {depth 0}
                :as opts}]
  (if (> depth max-depth)
    (gen/map gen/string-alphanumeric (gen/one-of scalars))
    (gen/recursive-gen
      (fn [scalars]
        (gen/map gen/string-alphanumeric
                 (gen/one-of [scalars
                              (gen-arr (deeper opts))
                              (gen-map (deeper opts))])
                 {:max-elements max-elements}))
      (gen/one-of scalars))))

(defn gen-clj-json [opts]
  (gen/one-of (conj scalars (gen-arr opts) (gen-map opts))))

(def gen-faulty-json-string
  (gen/fmap clojure.string/join
            (-> [(gen/return " ") (gen/return "\n")]
                (gen/one-of)
                (gen/vector))))

(defn clj->nem [clj]
  (walker coerce clj))

(defn json->clj [json-string]
  (json/parse-string-strict json-string))

(defn nem->clj [^Json json]
  (json->clj (str json)))

(defn clj->json [clj]
  (json/generate-string clj))

(defn json->nem [json]
  (json.parser.Parser/parse json))