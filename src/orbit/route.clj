(ns orbit.route
  (:require [clojure.string :as str]))

(def intersections
  (memoize
    (fn [point-a point-b]
      [{:x 1 :y 2 :z 3} {:x 4 :y 5 :z 6}])))

(def intersection-between-points?
  (memoize
    (fn [point-a point-b intersections]
      false)))

(def line-intersects-earth?
  (memoize
    (fn [point-a point-b]
  false)))

(def hop?
  (memoize
    (fn [point-a point-b]
      (if (and (line-intersects-earth? point-a point-b)
               (intersection-between-points? point-a point-b (intersections point-a point-b)))
        false
        true))))

(defn routes [data tail]
  nil)

(defn separate [route-tree]
  nil)

(defn pick-optimal [routes]
  (first (sort-by count routes)))

(defn find-route [data]
  (let [route-tree (routes data)
        separate-routes (separate route-tree)]
    (pick-optimal separate-routes)))
