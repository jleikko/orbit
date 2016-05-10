(ns orbit.coordinate-conversions-test
  (:require [clojure.test :refer :all]
            [orbit.coordinate-conversions :refer :all]
            [orbit.environment :refer :all]))

;; DEGREE->RADIAN

(deftest radians-zero-test
  (testing "Should convert zero degrees to zero radians"
    (is (= 0.0 (radians 0)))))

(deftest radians-pi-test
  (testing "Should convert 180 degrees to pi radians"
    (is (= Math/PI (radians 180)))))

(deftest radians-minus-half-pi-test
  (testing "Should convert -90 degrees to -pi/2 radians"
    (is (= (- (/ Math/PI 2)) (radians -90)))))

;; ALTITUDE->RADIUS

(deftest radius-zero-test
  (testing "with zero altitude should return the earth radius"
    (is (= earth-radius (radius 0)))))

(deftest radius-500-test
  (testing "with altitude 500 should return the earth radius + 500"
    (is (= (+ earth-radius 500) (radius 500)))))

;; SPHERICAL->CARTESIAN (ORIGO)

(deftest origo-x-test
  (testing "Should find x from the center"
    (is (= 0.0 (cartesian-x 0 0 0)))))

(deftest origo-y-test
  (testing "Should find y from the center"
    (is (= 0.0 (cartesian-y 0 0 0)))))

(deftest origo-z-test
  (testing "Should find z from the center"
    (is (= 0.0 (cartesian-z 0 0 0)))))

;; SPHERICAL->CARTESIAN (NORTH POLE)

(deftest north-pole-x-test
  (testing "Should find x from the north pole of a unit circle properly"
    (is (> 0.00001 (cartesian-x (/ Math/PI 2) 0 1)))))

(deftest north-pole-y-test
  (testing "Should find y from the north pole of a unit circle properly"
    (is (> 0.00001 (cartesian-y (/ Math/PI 2) 0 1)))))

(deftest north-pole-z-test
  (testing "Should find z from the north pole of a unit circle properly"
    (is (= 1.0 (cartesian-z (/ Math/PI 2) 0 1)))))

;; SPHERICAL->CARTESIAN (SOUTH POLE)

(deftest south-pole-x-test
  (testing "Should find x from the south pole of a unit circle properly"
    (is (> 0.00001 (cartesian-x (- (/ Math/PI 2)) 0 1)))))

(deftest south-pole-y-test
  (testing "Should find y from the south pole of a unit circle properly"
    (is (> 0.00001 (cartesian-y (- (/ Math/PI 2)) 0 1)))))

(deftest south-pole-z-test
  (testing "Should find z from the south pole of a unit circle properly"
    (is (= -1.0 (cartesian-z (- (/ Math/PI 2)) 0 1)))))

;; FULL CONVERSION TEST (1 METER ABOVE THE NORTH POLE)

(deftest cartesian-test
  (testing "a point 1 meter above the north pole should be converted properly"
    (let [coords (cartesian 90 0 1)]
      (is (> 0.00001 (:x coords)))
      (is (> 0.00001 (:y coords)))
      (is (= (+ earth-radius 1.0) (:z coords))))))
