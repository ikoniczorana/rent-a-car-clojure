(ns rentacarclojure.routes.home
  (:require
    [compojure.core :refer :all]
    [rentacarclojure.layout :as layout]
    [compojure.core :refer :all]
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

(defn home-page [&[error]]
  (render-file "login.html" {:title "Login"
                             :error error}))

(def login-schema
  {:email [st/required st/string]
   :password [st/required st/string]})


(defn get-login-page [&[error]]
  (render-file "/" {:title "Login"
                    :error error}))

(defn validate-user? [params]
  (st/valid? {:email (:email params)
              :password (:password params)} login-schema))

(defn get-user [params]
  (first (controller/get-user-by-email(select-keys params [:email]))))


(defn login-page-submit [{:keys [params session]}]
  (let [user (get-user params)]
    (cond
      (not (validate-user? params))
      (home-page "Enter email and password")
      (empty? user)
      (home-page "Enter credentials")
      :else
      (assoc (redirect "/home"):session (assoc session :identity user)))) )

(defn logout [request]
  (-> (redirect "/")
      (assoc :session {})))

(defn home [session]
  (render-file "home.html" {:title "Home"
                             :logged (:identity session)
                            :admin (layout/is-admin? session)}))



(defroutes home-routes
           (GET "/" [] (home-page))
           (POST "/" request (login-page-submit request ))
           (GET "/home" request (home (:session request))))
