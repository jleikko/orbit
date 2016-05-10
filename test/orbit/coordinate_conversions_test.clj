(ns orbit.coordinate-conversions-test
  (:require [clojure.test :refer :all]
            [orbit.coordinate-conversions :refer :all]
            [orbit.environment :refer :all]))

(deftest degree-to-radians-test
  (testing "Should convert zero degrees to zero radians"
    (is (approx= 0 (radians 0))))
  (testing "Should convert 180 degrees to pi radians"
    (is (approx= Math/PI (radians 180))))
  (testing "Should convert -90 degrees to -pi/2 radians"
    (is (approx= (- (/ Math/PI 2)) (radians -90)))))

(deftest altitude-to-radius-test
  (testing "with zero altitude should return the earth radius"
    (is (= earth-radius (radius 0))))
  (testing "with altitude 500 should return the earth radius + 500"
    (is (= (+ earth-radius 500) (radius 500)))))

(deftest spherical-to-cartesian-origo-test
  (testing "Should find x from the center"
    (is (approx= 0 (cartesian-x 0 0 0))))
  (testing "Should find y from the center"
    (is (approx= 0 (cartesian-y 0 0 0))))
  (testing "Should find z from the center"
    (is (approx= 0 (cartesian-z 0 0 0)))))

(deftest spherical-to-cartesian-north-pole-test
  (testing "Should find x from the north pole of a unit circle properly"
    (is (approx= 0 (cartesian-x (/ Math/PI 2) 0 1))))
  (testing "Should find y from the north pole of a unit circle properly"
    (is (approx= 0 (cartesian-y (/ Math/PI 2) 0 1))))
  (testing "Should find z from the north pole of a unit circle properly"
    (is (approx= 1 (cartesian-z (/ Math/PI 2) 0 1)))))

(deftest spherical-to-cartesian-south-pole-test
  (testing "Should find x from the south pole of a unit circle properly"
    (is (approx= 0 (cartesian-x (- (/ Math/PI 2)) 0 1))))
  (testing "Should find y from the south pole of a unit circle properly"
    (is (approx= 0 (cartesian-y (- (/ Math/PI 2)) 0 1))))
  (testing "Should find z from the south pole of a unit circle properly"
    (is (approx= -1 (cartesian-z (- (/ Math/PI 2)) 0 1)))))


(deftest full-cartesian-conversion-test
  (testing "a point 1 meter above the north pole should be converted properly"
    (let [coords (cartesian 90 0 1)]
      (is (approx= 0 (:x coords)))
      (is (approx= 0 (:y coords)))
      (is (approx= (+ earth-radius 1) (:z coords))))))
