(ns leiningen.wsimport
  (:import (com.sun.tools.ws WsImport)))

(def default-opts (atom ["-Xnocompile" "-d" "target/generated/java"]))
(defn map-project-onto-opts
  "Map the project settings onto the existing default opts"
  [opts]
  )

(defn wsimport
  "I don't do a lot."
  ([project]
    ;; ??  learn the project arguments for this, figure out what needs to be gotten
    (WsImport/doMain (into-array @default-opts)))
  ([project & args]
    ;; process command-line options... (compile directly?)
    ;; make the output directory if it doesn't already exist
    ;; call doMain with options
    (WsImport/doMain (into-array 
                       (swap! default-opts
                              (fn [dopts] (vec (concat @dopts args))))))))