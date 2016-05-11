(ns orbit.route
  (:require [clojure.string :as str]
            [orbit.intersections :refer :all]))

(defn sorted [points]
  (sort-by :id points))

(def hop?
  (memoize
    (fn [points]
      (if (and (line-intersects-earth? points)
               (intersection-between-points? points))
        false
        true))))

;;TODO refactor more readable
(defn routes [data tail]
  (if (hop? (sorted [(:called (:phones data)) (last tail)]))
    [tail]
    (let [possible-hops (filter #(not (contains? tail %)) (:sats data))]
      (concat
        (map
          #(if
             (hop? (sorted [% last tail]))
             (routes data (conj tail %))
             [])
          possible-hops)))))

(defn pick-optimal [routes]
  (first (sort-by count routes)))

(defn find-route [data]
  (let [all-routes (routes data (:calling (:phones data)))]
    (pick-optimal all-routes)))
