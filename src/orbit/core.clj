(ns orbit.core
  (:require [orbit.networking :refer :all]))

(defn -main
  "Finds a route between satellites"
  [& args]
  (println (http-get-body "https://space-fast-track.herokuapp.com/generate")))
