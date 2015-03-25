(ns clotlib.memoized-analysers
  (:require [clotlib.analysers :as a]))

(dorun (map
        #(def %1 (memoize %2)) ;; FIXME
        (ns-publics 'clotlib.analysers)))

