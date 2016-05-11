(ns orbit.intersections-test
  (:require [clojure.test :refer :all]
            [orbit.intersections :refer :all]
            [orbit.common :refer :all]))

(deftest intersections-test
  (testing "should find intersection points with the line and the earth"
    (is (= [{:x 0 :y earth-radius :z 0} {:x 0 :y (- earth-radius) :z 0}]
           (intersections {:x 0 :y 9000 :z 0} {:x 0 :y -9000 :z 0})))))

(deftest line-intersects-earth?-test
  (testing "intersections should be found"
    (is (true? (intersection-between-points?
                 {:x 0 :y 9000 :z 0}
                 {:x 0 :y (- 9000) :z 0}))))
  (testing "intersections should be also found"
        (is (true? (intersection-between-points?
                 {:x 0 :y 9000 :z 0}
                 {:x 0 :y 8000 :z 0}))))
  (testing "intersections should not be found"
        (is (true? (intersection-between-points?
                 {:x 0 :y 9000 :z 9000}
                 {:x 0 :y (- 9000) :z 9000})))))
