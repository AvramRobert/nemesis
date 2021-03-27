(ns benchmarks.util
  (:require [criterium.core :as c]))

(defn- mean [values]
  (/ (apply + values) (count values)))

(defn- derive-unit [mean]
  (let [nano  (* mean 1000000000)
        micro (* mean 1000000)
        milli (* mean 1000)]
    (cond
      (< nano 1000)  (str nano " ns")
      (< micro 1000) (str micro " µs")
      (< milli 1000) (str milli " ms")
      :else          (str mean " s"))))

(defn- stats [results]
  (let [average        (comp first :mean)
        lower-quantile (comp first second :mean)
        upper-quantile (comp second second :mean)]
    {:mean           (->> results (mapv average) (mean))
     :lower-quantile (->> results (mapv lower-quantile) (mean))
     :upper-quantile (->> results (mapv upper-quantile) (mean))}))

(defn label-with [name stats]
  (assoc stats :name name))

(defn- bench-task! [operation sample]
  (c/quick-benchmark* #(operation sample) {}))

(defn show-result [stats]
  (let [name  (:name stats "Operation")
        mean  (-> stats (:mean) (derive-unit))
        lower (-> stats (:lower-quantile) (derive-unit))
        upper (-> stats (:upper-quantile) (derive-unit))]
    (str (format "==== %s stats ====\n" name)
         (format "Average time: %s\n" mean)
         (format "Lower quantile: %s\n" lower)
         (format "Upper quantile: %s\n" upper))))

(defn benchmark-task [task]
  "Benchmarks an operation against a number samples and returns their mean."
  (let [name      (:name task)
        operation (:operation task)
        sampler   (:sampler task)
        _         (println "Benchmarking: " name)]
    (->> (sampler)
         (mapv #(bench-task! operation %))
         (stats)
         (label-with name))))

(defn benchmark-out! [& tasks]
  (doseq [task tasks]
    (-> task (benchmark-task) (show-result) (println))))