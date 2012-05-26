(ns leiningen.options-array-tests
  (:use midje.sweet)
  (:use leiningen.wsimport))

(fact "excluding options"
      (compose-options-array {:compile-java-sources true}) => ["-s" "target/generated/java"
                                                               "-keep"])

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