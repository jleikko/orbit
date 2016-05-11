(ns orbit.parse
  (:require [clojure.string :as str]
            [orbit.common :refer :all]
            [orbit.coords :refer :all]))

(defn parse-seed [raw-seed-line]
  {:seed (Double/parseDouble (subs raw-seed-line 7))})

(defn parse-sat-line [raw-sat-line]
  (let [splitted (str/split raw-sat-line #",")
        id (get splitted 0)
        lat (get-double splitted 1)
        lon (get-double splitted 2)
        alt (get-double splitted 3)
        xyz (cartesian lat lon alt)]
    (merge {:id id :lat lat :lon lon :alt alt} xyz)))

(defn parse-sats [raw-sat-lines]
    {:sats (map parse-sat-line raw-sat-lines)})

(defn parse-phones [raw-phones-line]
  (let [splitted (str/split raw-phones-line #",")
        calling-lat (get-double splitted 1)
        calling-lon (get-double splitted 2)
        calling-alt phone-altitude
        calling-xyz (cartesian calling-lat calling-lon calling-alt)
        called-lat (get-double splitted 3)
        called-lon (get-double splitted 4)
        called-alt phone-altitude
        called-xyz (cartesian called-lat called-lon called-alt)]
    {:phones {:calling (merge {:id "CALLING"
                               :lat calling-lat
                               :lon calling-lon
                               :alt calling-alt}
                              calling-xyz)
              :called  (merge {:id "CALLED"
                               :lat (get-double splitted 3)
                               :lon (get-double splitted 4)
                               :alt phone-altitude}
                              called-xyz)}}))

(defn parse-input [file-content]
  (let [raw-lines (str/split-lines file-content)
        seed (parse-seed (first raw-lines))
        sats (parse-sats (rest (butlast raw-lines)))
        phones (parse-phones (last raw-lines))]
    (merge seed sats phones)))


(defn parse-output [route]
  (str/join "," (rest (butlast (map #(:id %) route)))))


