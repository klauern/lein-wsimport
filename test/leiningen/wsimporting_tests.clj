(ns leiningen.wsimporting-tests
  (:use midje.sweet)
  (:use leiningen.wsimport))

(def ec2-service "test/resources/ec2.wsdl")

(fact "testing import of Amazon EC2 WSDL service"
      (fail "Not gonna work on it yet."))