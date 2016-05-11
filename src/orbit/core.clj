(ns orbit.core
  (:require [orbit.net :refer :all]
            [orbit.common :refer :all]
            [orbit.parse :refer :all]))

(defn -main
  "Finds a route between satellites"
  [& args]
  (let [raw-data (http-get-body! data-file-url)
        parsed-data (parse-input raw-data)
        mock-route "SAT7,SAT20"]

    mock-route))
