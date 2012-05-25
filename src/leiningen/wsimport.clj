(ns leiningen.wsimport
  (:require [clojure.java.io :as io]) 
  (:import (com.sun.tools.ws WsImport)))

(def opts (atom {:compile-java-sources false
                 :output-directory "target/generated/java"
                 })) 
(def default-opts (atom ["-Xnocompile" "-d" "target/generated/java"]))
(defn compose-options-array
  "Create the options array to pass to make the wsimport call,
   using both the project options (if specified), or just the argument
   listing" 
  ([project]
    
    )
  ([project & args]
    )) 

(defn wsimport
  "I don't do a lot."
  ([project]
    ;; ??  learn the project arguments for this, figure out what needs to be gotten
    (WsImport/doMain (into-array @default-opts)))
  ([project & args]
    ;; process command-line options... (compile directly?)
    ;; make the output directory if it doesn't already exist
    ;; call doMain with options
    (do
      (let [f (clojure.java.io/file (:output-directory opts))]
        (if-not (.exists f)
        (.mkdirs f)))
      (WsImport/doMain (into-array 
                         (swap! default-opts
                                (fn [dopts] (vec (concat dopts args)))))))))