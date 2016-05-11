(ns orbit.net-test
  (:require [clojure.test :refer :all]
            [orbit.net :refer :all]))

(def mock-data-url
  "https://raw.githubusercontent.com/jleikko/orbit/master/test/orbit/net_mock.txt")

(deftest http-get-body-test!
  (testing "Tests that the http client retrieves the mock file from the GitHub."
    (is (= "foo\n" (http-get-body! mock-data-url)))))
