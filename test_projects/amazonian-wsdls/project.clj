(defproject amazonian-wsdls "0.1.0-SNAPSHOT"
  :description "Sample project for testing `lein-wsimport` with"
  :url "http://github.com/klauern/lein-wsimport"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-wsimport "1.0.0-SNAPSHOT"]]
  :wsimport {:wsdl-list [ "src/resources/AmazonS3.wsdl" "http://sdb.amazonaws.com/doc/2009-04-15/AmazonSimpleDB.wsdl" ]
             :extra-options ["-extension"]}
  :java-source-paths ["target/generated/java"]
  :dependencies [[org.clojure/clojure "1.3.0"]])
