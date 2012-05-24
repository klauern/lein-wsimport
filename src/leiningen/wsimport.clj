(ns leiningen.wsimport
  (:import (com.sun.tools.ws.WsImport)))

(def default-opts (atom {}))


(defn wsimport
  "I don't do a lot."
  [project & args]
  (println "Hi!"))