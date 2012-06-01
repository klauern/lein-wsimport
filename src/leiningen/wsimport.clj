(ns leiningen.wsimport
  "Generate Java code from SOAP .wsdls using the JDK's wsimport task"
  (:require [clojure.java.io :as io]) 
  (:import (com.sun.tools.ws WsImport)))

(def opts (atom {:compile-java-sources false
                 :java-output-directory "target/generated/java"
                 :keep-java-sources true
                 :quiet-output false}))

;; This is the meat of the plugin.  WsImport#doMain only takes two sets of parameters:
;; - a String for the WSDL URI to import
;; - an array of String arguments to pass, configuring the generation strategy.
;; To see what all of the possible options, as long as you have a JDK installed and
;; in your path, type `wsimport` and read the verbose output.

(defn compose-options-array
  "Create an array of options to pass in to WsImport#doMain out of
   a map of settings (usually gathered from the project settings)" 
  [wsdl-file wsimport-opts]
  (let [ws-ary   (transient [])    ;; this feels wrong
        all-opts (conj @opts wsimport-opts)]   
    (if-not (:compile-java-sources all-opts) 
      (conj! ws-ary "-Xnocompile"))
    (if-let [out-dir (:java-output-directory all-opts)]
      (doseq [val ["-s" out-dir]]
        (conj! ws-ary val)))
    (if (:keep-java-sources all-opts)
      (conj! ws-ary "-keep"))
    (if-let [pkg (:java-package-name all-opts)]
      (conj! ws-ary "-p" pkg))
    (if (:quiet-output all-opts)
      (conj! ws-ary "-quiet"))
    (if-let [xtra-opts (:extra-options all-opts)]
       (reduce conj! ws-ary xtra-opts))
    (if-let [jaxb-binding-files (:jaxb-binding-files all-opts)]
      (doseq [jbinding jaxb-binding-files]  ;; seems kind of ugly, not sure this is a great solution
        (conj! ws-ary "-b")
        (conj! ws-ary jbinding)))
    (conj! ws-ary wsdl-file)
    (persistent! ws-ary)))  ;; this seems like a confession washing away sins...   

(defn import-wsdls
  "Call WsImport#doMain from Sun's JDK using an array of WSDL's to import
   and a set of user and default-specified options"
  [wsdl-list wsdl-options]
  (let [all-opts (conj @opts wsdl-options)
        f (clojure.java.io/file (all-opts :java-output-directory))]
    (if-not (.exists f)
      (.mkdirs f)))
  (doseq [wsdl wsdl-list]
    (WsImport/doMain 
           (into-array 
             (compose-options-array wsdl wsdl-options)))))

(defn wsimport
  "Generate Java code from SOAP .wsdls using the JDK's wsimport task"
  ([project]
    (import-wsdls (-> project :wsimport :wsdl-list) (project :wsimport))))
