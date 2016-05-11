(ns orbit.route
  (:require [clojure.string :as str]))

(defn intersections [point-a point-b]
  [{:x 1 :y 2 :z 3}])

(defn intersection-between-points? [point-a point-b intersection]
  false)

(defn intersection-with-globe? [point-a point-b]
  false)

(defn hop? [point-a point-b]
  false)

(defn hop-length [point-a point-b]
  1200.123)

(def routes (memoize (fn ([data]
                             (routes data [(:calling (:phones data))]))
                            ([data tail]
                             nil))))


(defn pick-optimal [routes]
  "SAT7,SAT20")

(defn route-to-str [route]
  (str/join "," (rest (butlast (map #(:id %) route)))))

