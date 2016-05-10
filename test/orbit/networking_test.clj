(ns orbit.networking-test
  (:require [clojure.test :refer :all]
            [orbit.networking :refer :all]))

(def mock-data-url
  "https://raw.githubusercontent.com/jleikko/orbit/master/test/orbit/networking_mock.txt")

(deftest http-get-body-test
  (testing "Tests that the http client retrieves the mock file from the GitHub."
    (is (.startsWith
          (http-get-body mock-data-url)
          "foo"))))
