(ns clotlib
  (:require [clotlib.distributions :refer :all]
            [clotlib.filters :refer :all]
            [clotlib.grapher :refer [new-graph]]))


(new-graph "My Graph"
           {:min 0
            :max 100
            :data (take 500 (linscale (absclip (normal) 2) 50 30))})
