(ns orbit.route-test
  (:require [clojure.test :refer :all]
            [orbit.common :refer :all]
            [orbit.route :refer :all]
            [orbit.route-mock :refer :all]
            [orbit.parsed-data-mock :refer :all]))

(deftest routes-test
  (testing "with mock data should find routes"
    (is (= mock-routes (routes mock-parsed-data)))))

(deftest route-to-str-test
  (testing "with mock data sould make valid string"
    (is (= "SAT1,SAT5" (route-to-str (first mock-routes))))))
