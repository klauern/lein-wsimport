(defproject lein-wsimport "1.0.0"
  :description "JAX-WS import plugin for Clojure projects"
  :url "https://github.com/klauern/lein-wsimport"
  :dependencies [[com.sun.xml.ws/jaxws-tools "2.2.7-promoted-b73"]]
  :profiles { :dev { :dependencies [[midje "1.4.0"]]}}
  :plugins [[lein-midje "2.0.0-SNAPSHOT"]
            [lein-clojars "0.9.0"]]
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true)
