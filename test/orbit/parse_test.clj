(ns orbit.parse-test
  (:require [clojure.test :refer :all]
            [orbit.parse :refer :all]
            [orbit.environment :refer :all]))

(def expected-parsed-data
  {:seed 0.0533969490788877
   :phones {:calling {:lat -46.68418774557557
                    :lon 52.702632313282066
                    :alt phone-altitude}
            :called {:lat 48.30677954454114
                  :lon -84.68958586546985
                  :alt phone-altitude}}
   :sats {"SAT0" {:lat 67.84534277348689
                  :lon 38.45724797780886
                  :alt 574.8784371035017}
          "SAT1" {:lat -10.191654781073794
                  :lon -18.69844683442267
                  :alt 541.7659825279877}
          "SAT2" {:lat 39.0209876418501
                  :lon -112.2489059864599
                  :alt 601.167191221042}
          "SAT3" {:lat 2.507808606672313
                  :lon -71.54737191836693
                  :alt 501.3581479548847}
          "SAT4" {:lat 15.388749519126435
                  :lon -90.00493420165043
                  :alt 678.5936110018993}
          "SAT5" {:lat -54.66226217618411
                  :lon 38.54380122194456
                  :alt 508.4777415171144}
          "SAT6" {:lat -73.93475638302925
                  :lon -149.42372880866807
                  :alt 682.5969993365551}
          "SAT7" {:lat -20.535960519803865
                  :lon 143.46388866150988
                  :alt 669.7171783217132}
          "SAT8" {:lat 27.281483176627958
                  :lon 52.15107976767666
                  :alt 468.072579772786}
          "SAT9" {:lat -87.68844428780996
                  :lon 167.92338028461603
                  :alt 613.6325600845254}
          "SAT10" {:lat 80.02999874817769
                  :lon -19.326006636262207
                  :alt 422.3624893141424}
          "SAT11" {:lat 88.94845119420535
                  :lon -44.0255966540596
                  :alt 358.53302175212195}
          "SAT12" {:lat 25.47970280551533
                  :lon -16.315998874603423
                  :alt 303.4305232610965}
          "SAT13" {:lat -42.4039713793233
                  :lon 136.1134519204312
                  :alt 611.3729260451272}
          "SAT14" {:lat -9.686197295055848
                  :lon -168.2395222540369
                  :alt 568.750612404486}
          "SAT15" {:lat 54.18597191694286
                  :lon -43.030630880812765
                  :alt 316.35524999034396}
          "SAT16" {:lat -88.76683808288861
                  :lon -120.40994038386523
                  :alt 430.25365882622043}
          "SAT17" {:lat 81.92546112364323
                  :lon 131.30335416764444
                  :alt 595.6372425158074}
          "SAT18" {:lat 59.98765553806544
                  :lon -35.667808269121394
                  :alt 462.13392518638}
          "SAT19" {:lat 12.584508066684734
                  :lon -142.40044515858048
                  :alt 397.2955133567856}}})

(deftest parse-input-test
  (testing "Tests that input-parse splits rows and columns properly"
    (is (= expected-parsed-data (parse-input (slurp "test/orbit/raw_data_mock.txt"))))))

(deftest parse-seed-test
  (testing "parses the seed line properly"
    (is (= {:seed 0.0533969490788877}
           (parse-seed "#SEED: 0.0533969490788877")))))

(deftest parse-sats-test
  (testing "parses the sat lines properly"
    (is (= { :sats {"SAT0" {:lat 67.84534277348689
                            :lon 38.45724797780886
                            :alt 574.8784371035017}
                    "SAT1" {:lat -10.191654781073794
                            :lon -18.69844683442267
                            :alt 541.7659825279877}
                    "SAT2" {:lat 39.0209876418501
                            :lon -112.2489059864599
                            :alt 601.167191221042}}}
           (parse-sats ["SAT0,67.84534277348689,38.45724797780886,574.8784371035017"
                        "SAT1,-10.191654781073794,-18.69844683442267,541.7659825279877"
                        "SAT2,39.0209876418501,-112.2489059864599,601.167191221042"])))))

(deftest parse-phones-test
  (testing "parses the phones line properly"
    (is (= {:phones {:calling {:lat -46.68418774557557
                               :lon 52.702632313282066
                               :alt phone-altitude}
                     :called  {:lat 48.30677954454114
                               :lon -84.68958586546985
                               :alt phone-altitude}}}
           (parse-phones "ROUTE,-46.68418774557557,52.702632313282066,48.30677954454114,-84.68958586546985")))))

