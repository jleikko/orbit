(ns orbit.io-parse
  (:require [clojure.string :as str]))

(defn parse-seed [raw-seed-line]
  {:seed nil})

(defn parse-sats [raw-sats-lines]
  nil)

(defn parse-phones [raw-phones-line]
  {:phones {:calling {:lat nil
                      :lon nil
                      :alt nil}
            :called {:lat nil
                     :lon nil
                     :alt nil}}})

(defn parse-input [file-content]
  (let [raw-lines (str/split-lines file-content)
        seed (parse-seed (first raw-lines))
        sats (parse-sats (drop-last (rest raw-lines)))
        phones (parse-phones (last raw-lines))]
    (merge seed sats phones)))
