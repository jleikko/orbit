(ns orbit.core-test
  (:require [clojure.test :refer :all]
            [orbit.core :refer :all]
            [clojure.string :as str]))

(deftest main-test
  (testing "Returns a list of satellites in a proper format."
    (let [satellites (-main)]
      (is (re-matches #"(SAT\d\d?)?(,SAT\d\d?)*" satellites))
      (is (false? (str/starts-with? satellites ","))))))
