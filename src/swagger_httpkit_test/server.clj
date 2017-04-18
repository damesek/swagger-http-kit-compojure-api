(ns swagger-httpkit-test.server
  (:gen-class)
  (:require [org.httpkit.server :as httpkit]
            [ring.middleware.params]
            [ring.middleware.json :refer [wrap-json-response]]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [ring.swagger.swagger2 :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.keyword-params]
            [ring.middleware.json]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.session.memory :refer [memory-store]]
            [schema.core :as s]))


(defroutes app

   (GET "/hello" [] (str "hello"))
   (GET "/apitest" [challenge :as request]
     (let [challenge (or (get-in request [:params :challenge])
                        (get-in request [:body :challenge])
                         "John Doe")]
    ;(spit "flubber.txt" request)
      {:status 200
       :body [challenge]}))
 (api
    {
     :swagger
     {:ui "/api-editor"
      :spec "/swagger.json"
      :data {:info {:title "Simple"
                    :description "Compojure Api example"}}
      :tags [{:name "api", :description "some apis"}]}}
  (context "/api" []
      :tags ["api"]
      (GET "/plus" []
        :return {:result Long}
        :query-params [x :- Long, y :- Long]
        :summary "adds two numbers together"
        (ok {:result (+ x y)}))
      (POST "/bot" [challenge]
        :return {:challenge String}
        :query-params [challenge]
        (ok {:challenge challenge})))))





(defn run! []
  (httpkit/run-server
      (->  app
         (ring.middleware.json/wrap-json-body {:keywords? true})
         (ring.middleware.json/wrap-json-response)
         (ring.middleware.keyword-params/wrap-keyword-params)
         (ring.middleware.params/wrap-params)
         (ring.middleware.json/wrap-json-params)
         )

      {:port 8080}
    (println "server started")))

(defn -main []
  (run!))
