(ns rentacarclojure.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[rentacarclojure started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[rentacarclojure has shut down successfully]=-"))
   :middleware identity})
