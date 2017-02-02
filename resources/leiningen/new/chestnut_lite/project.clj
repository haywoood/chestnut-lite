(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/clojurescript "1.9.456"]]

  :plugins [[lein-cljsbuild "1.1.3"]]

  :source-paths ["src/cljs"]

  :clean-targets ^{:protect false} [:target-path :compile-path "resources/public/js"]

  ;; nREPL by default starts in the :main namespace, we want to start in `user`
  ;; because that's where our development helper functions like (run) and
  ;; (browser-repl) live.
  :repl-options {:init-ns user}

  :cljsbuild {:builds
              [{:id "app"
                :source-paths ["src/cljs"]

                :figwheel true

                :compiler {:main {{name}}.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/{{name}}.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}

               {:id "min"
                :source-paths ["src/cljs"]
                :jar true
                :compiler {:main {{name}}.core
                           :output-to "resources/public/js/compiled/{{name}}.js"
                           :output-dir "target"
                           :source-map-timestamp true
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {:css-dirs ["resources/public/css"]}  ;; watch and update CSS

  :profiles {:dev
             {:dependencies [[figwheel "0.5.4-4"]
                             [figwheel-sidecar "0.5.4-4"]
                             [com.cemerick/piggieback "0.2.1"]
                             [org.clojure/tools.nrepl "0.2.12"]]

              :plugins [[lein-figwheel "0.5.4-4"]]

              :source-paths ["dev"]
              :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
