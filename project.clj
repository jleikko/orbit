(defproject orbit "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [org.clojure/tools.trace "0.7.9"]
                 [clj-http "2.1.0"]]
  :main ^:skip-aot orbit.core
  :target-path "target/%s"
  :plugins [[lein-auto "0.1.2"]]
  :profiles {:uberjar {:aot :all}})
