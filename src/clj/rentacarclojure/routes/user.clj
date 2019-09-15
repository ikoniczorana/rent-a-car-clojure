(ns rentacarclojure.routes.user
  (:require
    [compojure.core :refer :all]
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
    [clojure.pprint :as pp]))

(def user-schema
  {:firstname [st/required st/string]
   :lastname [st/required st/string]
   :username [st/required st/string]
   :password [st/required st/string]
   :email [st/required st/string]
   :assignedrole [st/required st/string]})

(defn user-valid? [params]
  (st/valid? {:firstname (:firstname params)
              :lastname (:lastname params)
              :username (:username params)
              :password (:password params)
              :email (:email params)
              :assignedrole (:assignedrole params)} user-schema))

(defn get-add-user-page [session &[message]]
  (if-not (authenticated? session)
    (redirect "/")
    (render-file "addUser.html" {:title "Add new user in db"
                                    :logged (:identity session)
                                    })))

(defn add-user [{:keys [params session]}]
  (user-valid? params)
  (println params)
  (controller/add-user params)
  (redirect "/users"))

(defn get-all-users [{:keys [params session]}]
  (if-not (authenticated? session)
    (render-file "users.html" {:users (controller/get-all-users)})
    (redirect "/login")))


(defresource delete-userr [{:keys [params session]}]
             (pp/pprint "ovde sam")
             :allowed-methods [:delete]
             (controller/delete-user (:userid params))
             :available-media-types ["application/json"])

(defn update-user [{:keys [params session]}]
             :allowed-methods [:put]
             :available-media-types ["application/json"]
             (println params)
             (controller/update-user params))

(defroutes user-routes
           (GET "/addUser" user (get-add-user-page (:session user)))
           (POST "/addUser" user (add-user user))
           (GET "/users" user (get-all-users (:session user)))
           (DELETE "/deleteuser" request (delete-userr request))
           (POST "/editUser" request (update-user request))
           )
