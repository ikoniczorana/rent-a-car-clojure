(ns rentacarclojure.routes.request
(:require
  [compojure.core :refer :all]
  [rentacarclojure.layout :as layout]
  [clojure.java.io :as io]
  [ring.util.http-response :as response]
  [compojure.core :refer :all]
  [struct.core :as st]
  [ring.util.response :refer [redirect]]
  [selmer.parser :refer [render-file]]
  [rentacarclojure.middleware.controller :as controller]
  [compojure.response :refer [render]]
  [buddy.auth :refer [authenticated?]]
  [liberator.core :refer [defresource]]
  [clojure.data.json :as json]
  [clojure.java.io :as io]
  [liberator.representation :refer [ring-response as-response]]
  [clojure.set :refer [rename-keys]]
  [clojure.string :as str]
  [rentacarclojure.layout :as layout]))

(def request-schema
  {:name [st/required st/string]
   :birthyear [st/required st/number]
   :idnumber [st/required st/string]
   :driverlicence [st/required st/string]
   :beginningyear [st/required st/number]

   :carid [st/required st/number]
   :userid [st/required st/number]})

(defn request-valid? [params]
  (st/valid? {:name (:name params)
              :birthyear (read-string (:birthyear params))
              :idnumber (:idnumber params)
              :driverlicence (:driverlicence params)
              :beginningyear (read-string (:beginningyear params))

              :carid (read-string (:carid params))
              :userid (read-string (:userid params))} request-schema))



(defn get-add-request-page [session &[message]]
  (if-not (authenticated? session)
    (redirect "/")
    (render-file "addRequest.html" {:title "Add new request for rent"
                                    :cities (controller/get-all-cities)
                                    :cars (controller/get-all-cars)
                                    :admin (layout/is-admin? session)
                                    :logged (:identity session)})))



(defn add-request [{:keys [params session]}]
  (request-valid? params)
  (println params)
  (controller/add-request params)
  (redirect "/requests"))

(defn get-all-request [session &[message]]
  (render-file "requests.html" {:requests (controller/get-all-requests)
                                  :admin (layout/is-admin? session)}))

(defn edit-request [{:keys [params session]}]
  :allowed-methods [:put]
  :available-media-types ["application/json"]
  (println params)
  (controller/update-request params))

(defroutes request-routes
  (GET "/addRequest" request (get-add-request-page (:session request)))
  (POST "/addRequest" request (add-request request))
  (GET "/requests" request (get-all-request (:session request)))
  (POST "/editRequest" request (edit-request request)))


