(ns rentacarclojure.routes.login
  (:require [compojure.core :refer :all]
            [struct.core :as st]
            [ring.util.response :refer [redirect]]
            [selmer.parser :refer [render-file]]
            [rentacarclojure.middleware.controller :as controller]
            [compojure.response :refer [render]]
            [buddy.auth :refer [authenticated?]]
            [liberator.core :refer [defresource]]
            [liberator.representation :refer [ring-response as-response]]
            [clojure.set :refer [rename-keys]]
            [rentacarclojure.layout :as layout]))

(def login-schema
  {:email [st/required st/string]
   :password [st/required st/string]})


(defn get-login-page [&[error]]
  (render-file "html/login.html" {:title "Login"
                                   :error error}))

(defn validate-user? [params]
  (st/valid? {:email (:email params)
              :password (:password params)} login-schema))

(defn get-user [params]
  (let [errors (validate-user? params)]
    (if (empty? errors)
      (first (controller/get-user-by-email(select-keys params [:email]))))))


(defn login-page-submit [{:keys [params session]}]
  (let [user (get-user params)]
    (cond
      (not (validate-user? params))
      (get-login-page "Enter email and password")
      (empty? user)
      (get-login-page "Enter credentials")
      :else
      (assoc (redirect "/home"):session (assoc session :identity user)))) )

(defn logout [request]
  (-> (redirect "/login")
      (assoc :session {})))

(defroutes login-routes
           (GET "/login" [] (get-login-page))
           (POST "/login" request (login-page-submit request))
           (GET "/logout" request (logout request))
           )
