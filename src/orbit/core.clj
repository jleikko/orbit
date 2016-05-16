(ns orbit.core
  (:require [orbit.net :refer :all]
            [orbit.common :refer :all]
            [orbit.parse :refer :all]
            [orbit.route :refer :all]
            [clojure.tools.trace :refer :all]
            [clojure.pprint :refer :all]))

(defn -main
  "Finds a route between satellites"
  [& args]
  (let [raw-data (http-get-body! data-file-url)
        parsed-data (parse-input raw-data)
        routes (find-routes parsed-data)
        route (pick-optimal routes)]
    (prn "SEED" (:seed parsed-data))
    (prn "BEST ROUTE" (parse-output route))
    (parse-output route)))

