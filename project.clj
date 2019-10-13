(defproject ye "0.1.0-SNAPSHOT"
  :description ""
  :url "https://github.com/rinx/ye"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/spec.alpha "0.2.176"]
                 [org.clojure/test.check "0.10.0-alpha3"]
                 [org.clojure/tools.cli "0.4.2"]
                 [orchestra "2019.02.06-1"]
                 [expound "0.7.2"]
                 [io.forward/yaml "1.0.9"]
                 [metosin/jsonista "0.2.2"]
                 [camel-snake-kebab "0.4.0"]]
  :profiles {:dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]]
                   :source-paths ["dev"]
                   :repl-options {:init-ns user}}
             :uberjar {:aot :all
                       :main ye.core}})
