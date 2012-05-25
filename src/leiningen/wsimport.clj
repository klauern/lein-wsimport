(ns leiningen.wsimport
  (:require [clojure.java.io :as io]) 
  (:import (com.sun.tools.ws WsImport)))


(def opts (atom {:compile-java-sources false
                 :java-output-directory "target/generated/java"
                 :keep-java-sources true
                 :quiet-output false
                 ;; :class-output-directory nil ;; I don't want to compile it, but they might.  leaving for docs later
                 ;; :jaxb-binding-files [ "path" "path1" "path2" ] ;; users to implement in project.clj
                 ;; :java-package-name "some.namespace.here" ;; users to implement in project.clj
                 ;; :extra-options [] ;; in case one of the weird options I don't use is specified, they can just
                                      ;; put in their array list of options ["-s" "something" "-b" "b-stuff", etc.]
                 })) 
(def default-opts (atom ["-Xnocompile" "-d" "target/generated/java"]))
(defn compose-options-array
  "Create the options array to pass to make the wsimport call,
   using both the project options (if specified), or just the argument
   listing" 
  ([project]
    (let [ws-ary   [] 
          all-opts (conj opts (:wsimport project))]
      (do
        (if-not (:compile-java-sources all-opts) 
          (conj ws-ary "-Xnocompile"))
        (if-let [out-dir (:java-output-directory all-opts)]
          (conj ws-ary "-s" out-dir))
        (if (:keep-java-sources all-opts)
          (conj ws-ary "-keep"))
        (if-let [pkg (:java-package all-opts)]
          (conj ws-ary "-p" pkg))
        (if (:quiet-output all-opts)
          (conj ws-ary "-quiet"))
        (if-let [xtra-opts (:extra-options all-opts)]
          (conj ws-ary xtra-opts)))))
  ([project & args]))

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

;; lots of potential options.  I don't see a need for many of them, but I'll probably
;; just wrap a couple of them:
;;   - -b for jaxws/jaxb binding files
;;   - httpproxy (should be implicit)
;;   - keep ? (this is kind of working already with the -Xnocompile, might have to be explicit
;;   - -p pkg
;;   - quiet
;;   - -s directory for source files
;;   - 
;; Usage: wsimport [options] <WSDL_URI>
;; 
;; where [options] include:
;;   -b <path>                 specify jaxws/jaxb binding files or additional schemas
;;                             (Each <path> must have its own -b)
;;   -B<jaxbOption>            Pass this option to JAXB schema compiler
;;   -catalog <file>           specify catalog file to resolve external entity references
;;                             supports TR9401, XCatalog, and OASIS XML Catalog format.
;;   -d <directory>            specify where to place generated output files
;;   -extension                allow vendor extensions - functionality not specified
;;                             by the specification.  Use of extensions may
;;                             result in applications that are not portable or
;;                             may not interoperate with other implementations
;;   -help                     display help
;;   -httpproxy:<host>:<port>  specify a HTTP proxy server (port defaults to 8080)
;;   -keep                     keep generated files
;;   -p <pkg>                  specifies the target package
;;   -quiet                    suppress wsimport output
;;   -s <directory>            specify where to place generated source files
;;   -target <version>         generate code as per the given JAXWS spec version
;;                             Defaults to 2.2, Accepted values are 2.0, 2.1 and 2.2
;;                             e.g. 2.0 will generate compliant code for JAXWS 2.0 spec
;;   -verbose                  output messages about what the compiler is doing
;;   -version                  print version information
;;   -wsdllocation <location>  @WebServiceClient.wsdlLocation value
;;   -clientjar <jarfile>      Creates the jar file of the generated artifacts along with the
;;                             WSDL metadata required for invoking the web service.
;; 
;; Extensions:
;;   -XadditionalHeaders              map headers not bound to request or response message to
;;                                    Java method parameters
;;   -Xauthfile                       file to carry authorization information in the format
;;                                    http://username:password@example.org/stock?wsdl
;;   -Xdebug                          print debug information
;;   -Xno-addressing-databinding      enable binding of W3C EndpointReferenceType to Java
;;   -Xnocompile                      do not compile generated Java files
;;   -XdisableSSLHostnameVerification disable the SSL Hostname verification while fetching
;;                                    wsdls
;; 
;; Examples:
;;   wsimport stock.wsdl -b stock.xml -b stock.xjb
;;   wsimport -d generated http://example.org/stock?wsdl
