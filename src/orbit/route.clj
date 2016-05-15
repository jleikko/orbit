(ns orbit.route
  (:require [clojure.string :as str]
            [orbit.common :refer :all]
            [orbit.intersections :refer :all]))

(def hop?
  (memoize
    (fn [points]
      (not (is-earth-between? points)))))

(defn find-routes
  ;;first call with one parameter
  ([data]
   (find-routes data [(:calling (:phones data))]))

  ;;used recursively with tho parameters
  ([data tail]
   (let [try-branches (fn []
                        (apply concat (map
                                        #(if (hop? (sort-by :id [% (last tail)]))
                                           (find-routes data (conj tail %))
                                           [])
                                        (list-complement (:sats data) tail))))

         try-directly (fn []
                        (if (hop? [(last tail) (:called (:phones data))])
                          [(conj tail (:called (:phones data)))]
                          []))
         direct-route (try-directly)]
   (if
     (> (count direct-route) 0)
     direct-route
     (try-branches)))))

(defn pick-optimal [routes]
  (first (sort-by count routes)))
