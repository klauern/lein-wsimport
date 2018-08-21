# lein-wsimport
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fklauern%2Flein-wsimport.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fklauern%2Flein-wsimport?ref=badge_shield)


A Leiningen plugin to utilize the JDK's [`wsimport`](http://docs.oracle.com/javase/6/docs/technotes/tools/share/wsimport.html) task from a Leiningen project.

## Why?
Simply put, this is a thin wrapper around the `wsimport` command-line tool provided by Oracle's JDK.  This is polish for your Leiningen project in case you want to save typing `wsimport -some -options, etc.` multiple times for each .WSDL.  

I created this because I am new to Clojure and want to learn/contribute at the same time. 

## Usage
Currently, this plugin only works against your `project.clj` configuration settings, so if you haven't already, add this to you're `:plugins` vector of your project.clj:

```clj
[lein-wsimport "1.0.0"]
```

Running this plugin from the command-line is simple enough:

    $ lein wsimport


## Configuration

WsImport is a task that generates .java and java .class files from a SOAP `wsdl` file, so you need to specify that your project will be using java sources:

```clj
:java-source-paths [ "target/generated/java" ] ;; by default, WSDL sources are generated here
```

To get this to do something with your wsdls, you will have to configure in your `project.clj` a `:wsimport` map.  A sample is provided below (with all the available options):

```clj          
:wsimport { :wsdl-list [ "Sample.wsdl" "ec2.wsdl" … ]
            :compile-java-sources true ;; or false (by default)
            :java-output-directory "target/generated/java" ;; by default
            :keep-java-sources true ;; by default
            :java-package-name "com.corporate.prefix.package"
            :quiet-output true ;; don't show the entirety of the output from Sun's WsImport task
            :jaxb-binding-files [ "binding1" "binding2" ]
            :extra-options ["-extension" "-catalog" ] ;; takes pretty much anything that you'd call from the command-line. call `wsimport` to see what's available
          }
```

Minimally, all you would have to do if you have vanilla WSDLs that you'd like to codegen for (vanilla, error-free SOAP, lol) is specify `:wsdl-list`:

```clj
:wsimport { :wsdl-list ["List.wsdl" "of.wsdl" "wsdls.wsl"] }
```

Then, from the command-line, just call `lein wsimport` to get your sources generated and/or compiled.


### Limitation: no separation of project configs per profile
I have not written any handling of profiles and handling multiple and possibly competing WSDL options.  I know there might be cases where you would have a couple `.wsdl` files you want to bring in that each will require separate options.  I don't have anything in place to handle that (yet).  As is the open-source way, patches are welcome.

## Alternatives

There's only one alternative that I know of, [clj-soap](https://bitbucket.org/taka2ru/clj-soap).  It uses Axis2 under the covers, and if you're a fan of that framework (I'm not) it's a good option.  I would love to see this project used with [Apache CXF](http://cxf.apache.org) instead, but there aren't a whole lot of alternatives out in the SOAP space.  This is both good and bad:  

  - bad -- not a lot of variety or options in Clojure for enterprisey things (SOAP is far from dead in the enterprise, like COBOL); 
  - good -- we all hope SOAP is dying a slow painful death for which new languages decide not to waste alot of time developing against.

  I hope for the latter, honestly, but this plugin wouldn't exist if I didn't need to work with SOAP or expected others to have to wade through legacy APIs.

## License

Copyright © 2012 Nick Klauer (klauer@gmail.com)

Distributed under the Eclipse Public License, the same as Clojure.



[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fklauern%2Flein-wsimport.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Fklauern%2Flein-wsimport?ref=badge_large)

## References:

SOAP is a beast, here's some tips and help:

- [`clj-soap`](https://bitbucket.org/taka2ru/clj-soap) - SOAP framework with Axi2 back-end that is written in Clojure if you don't want to use the Clojure/Java Interop with this plugin
- [JAX-WS](http://jax-ws.java.net) - Framework for which much of the SOAP/Java interfaces are based off of.  This will probably explain why `wsimport` generates what it does, and how you might be able to use it.
- [Soapuser.com](http://www.soapuser.com/index.html) - hahaha I just had to link this.  If you don't know what SOAP is, this is probably a good place to read about it, but you can tell it's a bit…dated?
- [using the client api with a local WSDL](http://metro.java.net/guide/ch02.html#developing-client-application-with-locally-packaged-wsdl) - Again, this is just the Metro docs, but you get the picture.  We're not generating a wrapper around JAX-WS, so the JAX-WS site is your best resource for understanding what `wsimport` generates for you.

TODO:
=====

- Implement command-line options parser ?
- more tests
- more wsdl examples
- profile separation of `wsdl` options from a default, blanket set of options