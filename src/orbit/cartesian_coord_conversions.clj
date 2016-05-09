(ns orbit.cartesian-coord-conversions
  (:require [orbit.constants :refer :all]))


(defn radians [degrees]
  (Math/toRadians degrees))

(defn radius [altitude]
  (+ earth-radius altitude))

(defn cartesian-x [alpha beta r]
  (* r (Math/cos alpha) (Math/cos beta)))

(defn cartesian-y [alpha beta r]
  (* r (Math/cos alpha) (Math/sin beta)))

(defn cartesian-z [alpha beta r]
  (* r (Math/sin alpha)))

(defn cartesian [lat lon alt]
  {:x (cartesian-x (radians lat) (radians lon) (radius alt))
   :y (cartesian-y (radians lat) (radians lon) (radius alt))
   :z (cartesian-z (radians lat) (radians lon) (radius alt))})
