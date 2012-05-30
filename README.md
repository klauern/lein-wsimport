# lein-wsimport

A Leiningen plugin to utilize the JDK's [`wsimport`](http://docs.oracle.com/javase/6/docs/technotes/tools/share/wsimport.html) task from a Leiningen project.

## Why?
So the question I had when I was writing this might be the same one you have.  Why?  What does this give you?

Simply put, this is a thin wrapper around the `wsimport` command-line tool provided by your JDK tooling.  There is fundamentally nothing this plugin will give you that whittling out your own `wsimport -command -line -opts wsdl-file-here.wsdl` won't.  For me, it serves two purposes:

1. I am new to Clojure and wanted to create something marginally useful
2. I want my project to wrap my web service creation.


## Usage

Currently, this plugin only works against your `project.clj` configuration settings, so if you haven't already, add this to you're `:plugins` vector of your project.clj:

```clj
    [lein-wsimport "1.0.0-SNAPSHOT"]
```

Running this plugin from the command-line is simple enough:

    $ lein wsimport


## Configuration

WsImport is a task that generates .java and java .class files from a SOAP `wsdl` file, so you need to specify that your project will be using java sources:

```clj
    :java-source-paths [ "target/generated/java" ] ;; by default, WSDL sources are generated here
```

To get this to do something with your wsdls, you will have to configure in your `project.clj` a `:wsimport` map.  A sample is provided below (with all the options):

```clj          
    :wsimport { :wsdl-list [ "Sample.wsdl" "ec2.wsdl" … ]
                :compile-java-sources true ;; or false (by default)
                :java-output-directory "target/generated/java" ;; by default
                :keep-java-sources true ;; by default
                :java-package-name "com.corporate.prefix.package"
                :quiet-output true ;; don't show the entirety of the output from Sun's WsImport task
                :jaxb-binding-files [ "binding1" "binding2" ]
                :extra-options ["-extension" "-catalog" ] ;; takes pretty much anything that you'd call from the command-line. call `wsimport` to see what's available          
```

Then, from the command-line, just call `lein wsimport` to get your sources generated and/or compiled.


## What's missing?
There are a couple things I'm aware of and I hope to figure out.

### project-only, no pure command-line functionality
Right now, it's a project-only configuration.  While this is nothing more than a simple wrapper around Sun's `wsimport` task, which is part of the [JAX-WS tooling](http://jax-ws.java.net/), it's main benefit is to allow you to generate and regenerate your SOAP/Java wrapper around a WSDL service.

### no separation of project configs per profile
I can see cases where you have a couple `.wsdl` files you want to bring in that will require separate options for each (and possibly differing and competing options).  I don't have anything in place to handle that (yet).  As is the open-source way, patches welcome.

## I don't want to talk Clojure to Java, give me something better!

Okay.  I would recommend that you look at the only alternative I know of, [clj-soap](https://bitbucket.org/taka2ru/clj-soap).  It uses Axis2 under the covers, and if you're a fan of that framework (I'm not) it's a worthy alternative.  I would love to see this project used with [Apache CXF](http://cxf.apache.org), but there aren't a whole lot of alternatives out in the SOAP space.  This is both good and bad:  

  - bad - not alot of variety or options in Clojure; 
  - good - we all hope SOAP is dying a slow painful death for which new languages decide not to waste alot of time developing against.  
  
  I hope for the latter, honestly, but this plugin wouldn't exist if I didn't need to work with SOAP or expected others to have to wade through legacy APIs.

## License

Copyright © 2012 Nick Klauer (klauer@gmail.com)

Distributed under the Eclipse Public License, the same as Clojure.


## References:

SOAP is a beast, here's some tips and help:

- [`clj-soap`](https://bitbucket.org/taka2ru/clj-soap) - SOAP framework with Axi2 back-end that is written in Clojure if you don't want to use the Clojure/Java Interop with this plugin
- [JAX-WS](http://jax-ws.java.net) - Framework for which much of the SOAP/Java interfaces are based off of.  This will probably explain why `wsimport` generates what it does, and how you might be able to use it.
- [Soapuser.com](http://www.soapuser.com/index.html) - hahaha I just had to link this.  If you don't know what SOAP is, this is probably a good place to read about it, but you can tell it's a bit…dated?
- 

TODO:
=====

- Implement command-line options parser ?
- more tests
- more wsdl examples
- profile separation of `wsdl` options from a default, blanket set of options