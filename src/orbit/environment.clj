(ns orbit.environment
  (:require [clojure.math.numeric-tower :as math]))

;; CONSTANTS

(def earth-radius 6371)

(def machine-epsilon 1e-15)

;; HELPERS

(defn approx=
  "Return true if the absolute value of the difference between x and y
   is less than the machine epsilon. Otherwise returns false."
  [x y]
  (< (math/abs (- x y)) machine-epsilon))
