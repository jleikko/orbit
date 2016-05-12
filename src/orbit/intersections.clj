(ns orbit.intersections
  (:require [clojure.math.numeric-tower :as math]
            [orbit.common :refer :all]))

(defn get-a [x1 x2 y1 y2 z1 z2]
  (+ (math/expt (- x2 x1) 2)
     (math/expt (- y2 y1) 2)
     (math/expt (- z2 z1) 2)))

(defn get-b [x1 x2 y1 y2 z1 z2]
  (+ (* 2 x1 (- x2 x1))
     (* 2 y1 (- y2 y1))
     (* 2 z1 (- z2 z1))))

(defn get-c [x1 y1 z1 r]
  (-
    (+ (math/expt x1 2)
       (math/expt y1 2)
       (math/expt z1 2))
    (math/expt r 2)))

(defn get-x [x1 x2 t]
  (+ (* (- x2 x1) t) x1))

(defn get-y [y1 y2 t]
  (+ (* (- y2 y1) t) y1))

(defn get-z [z1 z2 t]
  (+ (* (- z2 z1) t) z1))

(defn quatratic-equation-has-roots? [a b c]
  (> (- (math/expt b 2) (* 4 a c) 0)))

(defn get-one-quatratic-equation-root [a b c]
  (/ (+ (- b) (math/sqrt (- (math/expt b 2) (* 4 a c)))) (* 2 a)))

(defn is-between? [target value1 value2]
  (let [correct-order (< value1 value2)
        boundary1 (if (correct-order) value1 value2)
        boundary2 (if (correct-order) value2 value1)]
    (and (> target boundary1) (< target boundary2)))

(defn is-intersection-between-points? [points a b c]
  (let [t (get-one-quatratic-equation-root a b c)
        x-earth (get-x x1 x2 t)
        y-earth (get-y y1 y2 t)
        z-earth (get-z z1 z2 t)
        between-x (is-between? x-earth x1 x2)
        between-y (is-between? y-earth y1 y2)
        between-z (is-between? z-earth z1 z2)]
    (and between-x between-y between-z)))

(defn is-earth-between? [points]
  (let [x1 (:x (nth points 0))
        x2 (:x (nth points 1))
        y1 (:y (nth points 0))
        y2 (:y (nth points 1))
        z1 (:z (nth points 0))
        z2 (:z (nth points 1))
        a (get-a x1 x2 y1 y2 z1 z2)
        b (get-b x1 x2 y1 y2 z1 z2)
        c (get-c x1 y1 z1 earth-radius)
        line-intersects-earth (quatratic-equation-has-roots? a b c)])
  (and line-intersects-earth
       (is-intersection-between-points? x1 x2 y1 y2 z1 z2 a b c)))
