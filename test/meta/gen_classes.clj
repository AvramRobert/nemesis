(ns meta.gen-classes
  (:require [meta.gen-function :as f-gen]
            [meta.gen-json-reader :as g-gen]
            [meta.gen-json-writer :as o-gen]))

(defn generate-classes [max-arity]
  (f-gen/create-file max-arity)
  (g-gen/create-file max-arity)
  (o-gen/create-file max-arity))
