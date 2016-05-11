(ns orbit.core
  (:require [orbit.net :refer :all]
            [orbit.common :refer :all]
            [orbit.parse :refer :all]
            [orbit.route :refer :all]))

(defn -main
  "Finds a route between satellites"
  [& args]
  (let [raw-data (http-get-body! data-file-url)
        parsed-data (parse-input raw-data)
        route (find-route parsed-data)]
    (prn (parse-output route))))
