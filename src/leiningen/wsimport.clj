(ns leiningen.wsimport
  (:import (com.sun.tools.ws.WsImport)))

(def default-opts (atom ["-Xnocompile" "-d" "src/main/java"]))

(defn wsimport
  "I don't do a lot."
  ([project]
  ;; ??  learn the project arguments for this, figure out what needs to be gotten
  (WsImport/doMain (into-array default-opts)))
  ([project & args]
    ;; process command-line options... (compile directly?)
    (WsImport/doMain (into-array default-opts args))))