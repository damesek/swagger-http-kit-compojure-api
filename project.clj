(defproject swagger-httpkit-test "0.1.0-SNAPSHOT"
  :description "Testing ring-swagger with httpkit"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.2.0"]
                 [ring "1.5.0" :exclusions [clj-time]]
                 [compojure "1.5.1"]
                 [ring/ring-json "0.2.0"]
                 [metosin/compojure-api "1.1.9"]]
                 
  :main swagger-httpkit-test.server
  :plugins [[lein-ring "0.10.0" :exclusions [org.clojure/clojure]]]
  :aot [swagger-httpkit-test.server]
  :uberjar-name "httpkit-test-uberjar.jar"
  :ring {:handler swagger-httpkit-test.server/app}
  :profiles {:dev {:plugins [[lein-ring "0.10.0"]]}
             :uber {:dependencies [[org.clojure/clojure "1.8.0"]
                                   [http-kit "2.2.0"]
                                   [ring "1.5.0" :exclusions [clj-time]]
                                   [compojure "1.5.1"]
                                   [ring/ring-json "0.2.0"]]               
                       :main swagger-httpkit-test.server
                       :uberjar-name "httpkit-test-uberjar.jar"
                       :aot :all
                       :plugins [[lein-ring "0.10.0"]]}})
