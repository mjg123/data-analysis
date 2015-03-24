(ns clotlib
  (:require [quil.core :as q]))

(defn setup []
  (q/smooth)
  (q/frame-rate 1)
  (q/background 240))


(def ex1-data (atom ["blah"]))

(defn addit [word]
  (swap! ex1-data conj word))

(defn draw-data [data]
  (fn []
    (let [d @data]
      (dorun (map println d)))))


(q/defsketch ex1
  :title "ex1"
  :setup setup
  :draw (draw-data ex1-data)
  :size [640 480])





