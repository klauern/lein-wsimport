# lein-wsimport

A Leiningen plugin to utilize the JDK's `wsimport` task from a Leiningen project.

## Usage

FIXME: Use this for user-level plugins:

Put `[lein-wsimport "1.0.0-SNAPSHOT"]` into the `:plugins` vector of your
`:user` profile, or if you are on Leiningen 1.x do `lein plugin install
lein-wsimport 1.0.0-SNAPSHOT`.

FIXME: Use this for project-level plugins:

Put `[lein-wsimport "1.0.0-SNAPSHOT"]` into the `:plugins` vector of your project.clj.

FIXME: and add an example usage that actually makes sense:

    $ lein wsimport

## License

Copyright © 2012 Nick Klauer (klauer@gmail.com)

Distributed under the Eclipse Public License, the same as Clojure.


TODO:
=====

 - Write the thing
 - define `project.clj` options to take
 - write sample `project.clj` options
 - define command-line opts to pass
 - Read `project.clj` options, parse
 - define default position of options
 - Compile? (need to add the jdk-tools.jar if so)
