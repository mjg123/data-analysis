(defproject clotlib "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [quil "2.2.5" :exclusions [org.clojure/clojure]]]
  :plugins [[cider/cider-nrepl "0.8.2"]]
  :main clotlib)