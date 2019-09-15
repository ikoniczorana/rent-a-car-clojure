(ns rentacarclojure.middleware.controller
  (:require [clojure.java.jdbc :as sql]
            [korma.core :as k]
            [korma.db :refer [defdb mysql]]
            [clj-time.coerce :as c]
            [clj-time.core :as t])
  (:import java.sql.DriverManager))

(def db-config(clojure.edn/read-string (slurp "configuration/migratus-conf.edn")))

(defdb db (mysql db-config))


(k/defentity user
             (k/table :user))

(defn add-user [params]
  (k/insert user
            (k/values params)))

(defn get-all-users[]
  (k/select user))

(defn find-user [params]
  (k/select user
            (k/where params)))

(defn get-user-by-email [params]
  (k/select user
            (k/where params)))

(defn delete-user [id]
  (k/delete user
            (k/where {:userid id})))

(defn update-user [params]
  (k/update user
            (k/set-fields params)
            (k/where {:userid (:userid params)})))


(k/defentity request
             (k/table :request))


(defn add-request [params]
  (k/insert request
            (k/values params)))



(defn delete-request [id]
  (k/delete request
            (k/where {:requestid id})))

(defn find-request [params]
  (k/select request
            (k/where params)))

(defn update-request [params]
  (k/update request
            (k/set-fields params)
            (k/where {:requestid (:requestid params)})))



(k/defentity city
             (k/table :city))

(defn get-all-cities []
  (k/select city))



(k/defentity car
             (k/table :car))

(defn get-all-cars[]
  (k/select car))

(defn get-all-requests[]
  (k/select request
            (k/fields :* [:car.type :ctype])
            (k/join car (= :request.carid :car.carid))))




