(defproject solids "0.1.0-SNAPSHOT"
  :description "The Platonic solids in clojurescript."
  :url "http://github.com/kanej/solids"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :plugins [[lein-cljsbuild "0.3.2"]]
  :cljsbuild {:builds
              [{; The path to the top-level ClojureScript source directory:
                :source-paths ["src"]
                ; The standard ClojureScript compiler options:
                ; (See the ClojureScript compiler documentation for details.)
                :compiler {:output-to "public/js/main.js"
                           ; default: target/cljsbuild-main.js
                           :optimizations :whitespace
                           :pretty-print true}}]})
