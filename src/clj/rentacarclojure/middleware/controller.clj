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


(defn find-user [params]
  (k/select user
            (k/where params)))

(defn get-user-by-email [params]
  (k/select user
            (k/where params)))


