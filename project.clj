(defproject lein-wsimport "1.2.0-SNAPSHOT"
  :description "JAX-WS import plugin for Clojure projects"
  :url "https://github.com/klauern/lein-wsimport"
  :dependencies [[com.sun.xml.ws/jaxws-tools "4.0.2"]]
  :profiles { :dev { :dependencies [[midje "1.6-beta1"]]}}
  :plugins [[lein-midje "3.1.2"]
            [lein-clojars "0.9.1"]]
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true)
