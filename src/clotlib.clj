(ns clotlib
  (:require [clotlib.distributions :refer :all]
            [clotlib.filters :refer :all]
            [clotlib.grapher :refer [new-graph]]
            [clotlib.analysers :as a]))

(comment
  (new-graph "My Graph"
             {:min 0
              :max 100
              :data (take 500 (linscale (absclip (normal) 2) 50 30))}))

(defn foo []
  (let [nums (take 10 (normal))]
    (println nums)
    (println (a/summary nums))))
