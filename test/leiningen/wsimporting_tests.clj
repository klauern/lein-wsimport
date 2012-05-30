(ns leiningen.wsimporting-tests
  (:use [midje.sweet]
        [leiningen.wsimport])
  (:require [leiningen.core.user :as user]
            [leiningen.core.classpath :as classpath]
            [clojure.java.io :as io]))

(def ec2-wsdl (io/resource "test/resources/ec2.wsdl"))


;; (fact "testing import of Amazon EC2 WSDL service"
;;      (fail "Not gonna work on it yet."))