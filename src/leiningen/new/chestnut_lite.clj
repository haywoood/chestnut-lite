(ns leiningen.new.chestnut-lite
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "chestnut-lite"))

(defn chestnut-lite
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' chestnut-lite project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["dev/user.clj" (render "user.clj" data)]
             ["project.clj" (render "project.clj" data)]
             ["resources/public/css/style.css" (render "style.css" data)]
             ["resources/public/index.html" (render "index.html" data)]
             ["src/cljs/{{sanitized}}/core.cljs" (render "core.cljs" data)]
             ["project.clj" (render "project.clj" data)])))
