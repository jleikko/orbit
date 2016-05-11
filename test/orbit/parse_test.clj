(ns orbit.parse-test
  (:require [clojure.test :refer :all]
            [orbit.parse :refer :all]
            [orbit.common :refer :all]
            [orbit.parsed-data-mock :refer :all]
            [orbit.route-mock :refer :all]))

(deftest parse-input-test
  (testing "Tests that input-parse splits rows and columns properly"
    (is (= mock-parsed-data (parse-input (slurp "test/orbit/raw_data_mock.txt"))))))

(deftest parse-output-test
  (testing "with mock data sould make valid string"
    (is (= "SAT1,SAT5" (parse-output (first mock-routes))))))

(deftest parse-seed-test
  (testing "parses the seed line properly"
    (is (= {:seed 0.0533969490788877}
           (parse-seed "#SEED: 0.0533969490788877")))))

(deftest parse-sats-test
  (testing "parses the sat lines properly"
    (is (= { :sats [{:id "SAT0"
                     :lat 67.84534277348689
                     :lon 38.45724797780886
                     :alt 574.8784371035017
                     :x 2051.137651245084
                     :y 1629.0511901423185
                     :z 6433.059444662398}
                    {:id "SAT1"
                     :lat -10.191654781073794
                     :lon -18.69844683442267
                     :alt 541.7659825279877
                     :x 6444.58625616902
                     :y -2181.177387671989
                     :z -1223.154413597034}
                    {:id "SAT2"
                     :lat 39.0209876418501
                     :lon -112.2489059864599
                     :alt 601.167191221042
                     :x -2050.962033646513
                     :y -5013.492130086571
                     :z 4389.711464372082}]}
           (parse-sats ["SAT0,67.84534277348689,38.45724797780886,574.8784371035017"
                        "SAT1,-10.191654781073794,-18.69844683442267,541.7659825279877"
                        "SAT2,39.0209876418501,-112.2489059864599,601.167191221042"])))))

(deftest parse-phones-test
  (testing "parses the phones line properly"
    (is (= {:phones {:calling {:id "CALLING"
                               :lat -46.68418774557557
                               :lon 52.702632313282066
                               :alt phone-altitude
                               :x 2648.3906511089735
                               :y 3476.841019960912
                               :z -4635.434955107962}
                     :called  {:id "CALLED"
                               :lat 48.30677954454114
                               :lon -84.68958586546985
                               :alt phone-altitude
                               :x 392.19841744514207
                               :y -4219.432048350242
                               :z 4757.334057296103}}}
           (parse-phones "ROUTE,-46.68418774557557,52.702632313282066,48.30677954454114,-84.68958586546985")))))
