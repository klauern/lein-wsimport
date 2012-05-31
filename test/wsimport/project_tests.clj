(ns wsimport.project-tests
  (:use [midje.sweet]
        [leiningen.wsimport])
  (:require [leiningen.core.user :as user]
            [leiningen.core.classpath :as classpath]
            [clojure.java.io :as io]))

(def ec2-wsdl (io/resource "test/resources/ec2.wsdl"))
(def s3-wsdl "http://s3.amazonaws.com/doc/2006-03-01/AmazonS3.wsdl")
(def sdb-wsdl "http://sdb.amazonaws.com/doc/2009-04-15/AmazonSimpleDB.wsdl")
(def mturk-wsdl "http://mechanicalturk.amazonaws.com/AWSMechanicalTurk/2012-03-25/AWSMechanicalTurkRequester.wsdl")
(def sqs-wsdl "http://queue.amazonaws.com/doc/2011-10-01/QueueService.wsdl")

(def ebay-finding-svc "http://developer.ebay.com/webservices/finding/latest/FindingService.wsdl")
(def ebay-trading-svc "http://developer.ebay.com/webservices/latest/ebaySvc.wsdl")

(def ms-search-wsdl "http://api.search.live.net/search.wsdl")
