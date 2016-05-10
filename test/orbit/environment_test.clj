(ns orbit.environment-test
  (:require [clojure.test :refer :all]
            [orbit.environment :refer :all]))


(deftest approx-equals-false-test
  (testing "Two numbers that are not close enough are not approximately equal."
    (is (false? (approx= 0.004 0.005)))))

(deftest approx-equals-true-test
  (testing "Two numbers that are close enough are approximately equal."
    (is (true? (approx= 1e-16 2e-16)))))
