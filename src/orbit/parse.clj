(ns orbit.parse
  (:require [clojure.string :as str]
            [orbit.environment :refer :all]))

(defn parse-seed [raw-seed-line]
  {:seed (Double/parseDouble (subs raw-seed-line 7))})

(defn parse-sat-line [raw-sat-line]
  (let [splitted (str/split raw-sat-line #",")]
    {(get splitted 0) {:lat (get-double splitted 1)
                       :lon (get-double splitted 2)
                       :alt (get-double splitted 3)}}))

(defn parse-sats [raw-sat-lines]
  (let [sats (apply merge (map parse-sat-line raw-sat-lines))]
    {:sats sats}))

(defn parse-phones [raw-phones-line]
  (let [splitted (str/split raw-phones-line #",")]
    {:phones {:calling {:lat (get-double splitted 1)
                        :lon (get-double splitted 2)
                        :alt phone-altitude}
              :called  {:lat (get-double splitted 3)
                        :lon (get-double splitted 4)
                        :alt phone-altitude}}}))

(defn parse-input [file-content]
  (let [raw-lines (str/split-lines file-content)
        seed (parse-seed (first raw-lines))
        sats (parse-sats (drop-last (rest raw-lines)))
        phones (parse-phones (last raw-lines))]
    (merge seed sats phones)))
