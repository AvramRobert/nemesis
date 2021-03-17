(ns meta.class-generator
  (:require [meta.function-gen :as f-gen]
            [meta.json-converter-gen :as g-gen]
            [meta.object-converter-gen :as o-gen]))

(defn generate-classes [max-arity]
  (f-gen/create-file max-arity)
  (g-gen/create-file max-arity)
  (o-gen/create-file max-arity))
