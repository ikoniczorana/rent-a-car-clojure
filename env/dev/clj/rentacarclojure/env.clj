(ns rentacarclojure.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [rentacarclojure.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[rentacarclojure started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[rentacarclojure has shut down successfully]=-"))
   :middleware wrap-dev})
