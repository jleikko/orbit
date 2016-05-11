(ns orbit.net
  (:require [clj-http.client :as http-client]))

(defn http-get-body! [url]
  (:body (http-client/get url)))
