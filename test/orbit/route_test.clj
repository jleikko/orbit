(ns orbit.route-test
  (:require [clojure.test :refer :all]
            [orbit.common :refer :all]
            [orbit.route :refer :all]
            [orbit.route-mock :refer :all]
            [orbit.parsed-data-mock :refer :all]))

(deftest sorted-test
  (testing "sorts points to a valid order to support memoize better"
    (is (= [{:id "aaa"} {:id "bbb"}] (sorted [{:id "bbb"} {:id "aaa"}])))))

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
