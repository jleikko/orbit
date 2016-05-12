(ns orbit.route
  (:require [clojure.string :as str]
            [orbit.intersections :refer :all]))

(defn try-branches [data tail]
  (apply concat (map
                  #(if (hop? (sort-by :id [% (last tail)]))
                     (find-routes data (conj tail %))
                     [])
                  (difference data tail))))

(defn try-directly [data tail]
  (if (hop? (sort-by :id [(:called (:phones data))
                          (last tail)]))
    [(conj tail (:called (:phones data)))]
    []))

(defn find-routes
  ;;first call with one parameter
  ([data]
   (find-route data (:calling (:phones data))))
  ;;used recursively with tho parameters
  ([data tail]
   (concat
     (try-directly data tail)
     (try-branches data tail))))

(defn pick-optimal [routes]
  (first (sort-by count routes)))
