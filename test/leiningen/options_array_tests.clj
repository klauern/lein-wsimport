(ns leiningen.options-array-tests
  (:use midje.sweet)
  (:use leiningen.wsimport))

(fact "adding one option"
      (compose-options-array {:compile-java-sources true}) => ["-s" "target/generated/java" "-keep"]
      (compose-options-array {:java-output-directory "oput"}) => ["-Xnocompile" "-s" "oput" "-keep"]
      (compose-options-array {:quiet-output true}) => ["-Xnocompile" "-s" "target/generated/java" "-keep" "-quiet"]
      (compose-options-array {:wsdl-file "Sample.wsdl"}) => ["-Xnocompile" "-s" "target/generated/java" "-keep" "Sample.wsdl"]
      (compose-options-array {:extra-options ["some" "opts" "here"]}) => ["-Xnocompile" "-s" "target/generated/java" "-keep" "some" "opts" "here"]
      (compose-options-array {:keep-java-sources false}) => ["-Xnocompile" "-s" "target/generated/java"])

(fact "default options are"
      (@opts :compile-java-sources) => false
      (@opts :java-output-directory) => "target/generated/java"
      (@opts :extra-options) => nil
      (@opts :keep-java-sources) => true
      (@opts :quiet-output) => false
      (@opts :java-package-name) => nil)

(fact "default options array is"
      (compose-options-array {}) => ["-Xnocompile" "-s" "target/generated/java"
                                     "-keep"])