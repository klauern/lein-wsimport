(defproject lein-wsimport "1.0.0-SNAPSHOT"
  :description "JAX-WS Import plugin for Clojure projects"
  :url ""
  :dependencies [[com.sun.xml.ws/jaxws-tools "2.2.7-promoted-b73"]]
  :profiles { :dev { :dependencies [[midje "1.4.0"]
                                    ]}}
  :java-source-paths ["target/generated/java"] ; Java source is stored separately
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :wsimport { :wsdl-list ["test/resources/ec2.wsdl"] 
              :extra-options ["-extension"] }
  :eval-in-leiningen true)
