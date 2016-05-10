(ns orbit.core
  (:require [orbit.networking :refer :all]
            [orbit.environment :refer :all]
            [orbit.io-parse :refer :all]))

(defn -main
  "Finds a route between satellites"
  [& args]
  (let [raw-data (http-get-body! data-file-url)
        parsed-data (parse-input raw-data)
        mock-route "SAT7,SAT20"]

    mock-route))
