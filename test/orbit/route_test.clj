(ns orbit.route-test
  (:require [clojure.test :refer :all]
            [orbit.common :refer :all]
            [orbit.route :refer :all]
            [orbit.route-mock :refer :all]
            [orbit.parsed-data-mock :refer :all]))

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

(deftest hop?-test
  (testing "hop is not possible through the earth"
    (is (false? (hop? {:x 0 :y 9000 :z 0}
                      {:x 0 :y (- 9000) :z 0}))))
  (testing "hop is possible above the earth"
    (is (true? (hop? {:x 0 :y 9000 :z 9000}
                     {:x 0 :y (- 9000) :z 9000})))))
  (testing "hop is possible also vertically above the earth"
    (is (true? (hop? {:x 0 :y 9000 :z 9000}
                     {:x 0 :y (- 800) :z 8000}))))

(deftest routes-test
  (testing "with mock data should find routes"
    (is (= mock-routes (routes mock-parsed-data)))))

(deftest pick-optimal-test
  (testing "picks the shortest route from available routes"
    (is (= [{:id "CALLING"
             :lat 1
             :lon 2
             :alt 3
             :x 1
             :y 2
             :z 3}
            {:id "SAT1"
             :lat 4
             :lon 5
             :alt 6
             :x 4
             :y 5
             :z 6
             :dist 1200.123}
            {:id "CALLED"
             :lat 7
             :lon 8
             :alt 9
             :x 7
             :y 8
             :z 9}]
           (pick-optimal mock-routes)))))