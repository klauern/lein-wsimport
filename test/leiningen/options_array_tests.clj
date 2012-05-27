(ns leiningen.options-array-tests
  (:use midje.sweet)
  (:use leiningen.wsimport))

(facts "about composing the wsimport task options array"
      (compose-options-array "Sample.wsdl" {:compile-java-sources true}) => ["-s" "target/generated/java" "-keep" "Sample.wsdl"]
      (compose-options-array "Sample.wsdl" {:java-output-directory "oput"}) => ["-Xnocompile" "-s" "oput" "-keep" "Sample.wsdl"]
      (compose-options-array "Sample.wsdl" {:quiet-output true}) => ["-Xnocompile" "-s" "target/generated/java" "-keep" "-quiet" "Sample.wsdl"]
      (compose-options-array "Sample.wsdl" {:extra-options ["some" "opts" "here"]}) => ["-Xnocompile" "-s" "target/generated/java" "-keep" "some" "opts" "here" "Sample.wsdl"]
      (compose-options-array "Sample.wsdl" {:keep-java-sources false}) => ["-Xnocompile" "-s" "target/generated/java" "Sample.wsdl"])

(facts "about default options"
      (@opts :compile-java-sources) => false
      (@opts :java-output-directory) => "target/generated/java"
      (@opts :extra-options) => nil
      (@opts :keep-java-sources) => true
      (@opts :quiet-output) => false
      (@opts :java-package-name) => nil
      (@opts :wsdl-files) => nil)

(fact "about the default options array is"
      (compose-options-array "Sample.wsdl" {}) => ["-Xnocompile" "-s" "target/generated/java" "-keep" "Sample.wsdl"])