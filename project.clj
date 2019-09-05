(defproject rentacarclojure "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[ch.qos.logback/logback-classic "1.2.3"]
                 [cheshire "5.8.1"]
                 [clojure.java-time "0.3.2"]
                 [cprop "0.1.14"]
                 [expound "0.7.2"]
                 [funcool/struct "1.4.0"]
                 [luminus-jetty "0.1.7"]
                 [luminus-transit "0.1.1"]
                 [luminus/ring-ttl-session "0.3.3"]
                 [markdown-clj "1.10.0"]
                 [metosin/muuntaja "0.6.4"]
                 [metosin/reitit "0.3.9"]
                 [metosin/ring-http-response "0.9.1"]
                 [mount "0.1.16"]
                 [nrepl "0.6.0"]
                 [org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.cli "0.4.2"]
                 [org.clojure/tools.logging "0.5.0"]
                 [org.webjars.npm/bulma "0.7.5"]
                 [org.webjars.npm/material-icons "0.3.0"]
                 [org.webjars/webjars-locator "0.36"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-core "1.7.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [selmer "1.12.14"]
                 [funcool/struct "1.3.0"]
                 [mysql/mysql-connector-java "8.0.15"]
                 [buddy/buddy-auth "2.1.0"]
                 [com.cerner/clara-rules "0.19.0"]
                 [org.clojure/java.jdbc "0.7.9"]
                 [korma/korma "0.4.3"]
                 [migratus "1.2.2"]
                 [ring-server "0.5.0"]
                 [compojure "1.6.1"]
                 [liberator "0.15.2"]
                 [org.slf4j/slf4j-log4j12 "1.7.26"]
                 ]

  :min-lein-version "2.0.0"
  
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :resource-paths ["resources"]
  :target-path "target/%s/"
  :main ^:skip-aot rentacarclojure.core

  :plugins [[lein-ring "0.12.5"]
            [migratus-lein "0.7.1"]
            ]
  :migratus {:store :database
             :migration-dir "migrations"
             :db (clojure.edn/read-string (slurp "configuration/migratus-conf.edn"))}
  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :uberjar-name "rentacarclojure.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:jvm-opts ["-Dconf=dev-config.edn"]
                  :dependencies [[pjstadig/humane-test-output "0.9.0"]
                                 [prone "2019-07-08"]
                                 [ring/ring-devel "1.7.1"]
                                 [ring/ring-mock "0.4.0"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.24.1"]]
                  
                  :source-paths ["env/dev/clj"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:jvm-opts ["-Dconf=test-config.edn"]
                  :resource-paths ["env/test/resources"]}
   :profiles/dev {}
   :profiles/test {}})
