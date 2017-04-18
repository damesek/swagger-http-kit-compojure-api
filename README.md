# Swagger and Compojure API with http-kit, workable uberjar export
Compojure API test and Swagger API integration example (works with uberjar)

I found just Arttuka http-kit swagger tutorial. `https://github.com/arttuka/swagger-httpkit-test`. End of this tutorial you can read: "Doesn't currently work because of a suspected problem in compojure-api and AOT compiling." 

.. that wasn't good news for me .. and not so happy! I try to find a solution for this. That is my quick demonstration how works the swagger/ compojure-api with http-kit. Plus you can compile with uberjar.

First way to create REST-API:
```
(GET "/apitest" [challenge :as request]
     (let [challenge (or (get-in request [:params :challenge])
                        (get-in request [:body :challenge])
                         "John Doe")]
      {:status 200
       :body [challenge]}))
```
Second way with Swagger:

```
(POST "/bot" [challenge]
   :return {:challenge String}
   :query-params [challenge]
   (ok {:challenge challenge}))
   
; swagger api editor: localhost:8080/api-editor
```

How to run?

`lein run`

After this check in the browser: 

`localhost:8080`

How to compile with uberjar in this case?

`lein with-profile uber uberjar`

How to start server?

`java -jar target/swagger-httpkit-test-uberjar.jar`



Of course, you can improve this. That is just a quick tutorial.
Have a nice day,

Szabolcs
