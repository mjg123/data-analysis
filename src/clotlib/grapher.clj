(ns clotlib.grapher
  (:require [quil.core :as q]
            [quil.applet :as qa]))
  
(defn setup []
  (q/smooth)
  (q/frame-rate 25)
  (q/background 240)
  (q/no-fill))

(defn jitter-1d [dist]
  {:xbounds [(dist :min) (dist :max)]
   :ybounds [0 1]
   :points  (map (fn [p] [p (rand)]) (dist :data))})

(defn plotscale [dmin dmax wmin wmax d]
  (+ wmin (* (- wmax wmin)
             (/ d (- dmax dmin)))))

(defn o-at [[x y]]
  (q/ellipse x y 10 10))

(def padding 10)

(defn plot! [point-data]
  (let [{[xmin xmax] :xbounds
         [ymin ymax] :ybounds} point-data]
    (dorun (map (fn [[x y]] (o-at [(plotscale xmin xmax padding (- (q/width) padding) x)
                                   (plotscale ymin ymax padding (- (q/height) padding) y)]))
                (point-data :points)))))

(defn new-graph [name data]
  (let [draw-fn (fn []
                  (q/background 240)
                  
                  (q/stroke 220)
                  (q/rect padding padding
                          (- (q/width) (* padding 2))
                          (- (q/height) (* padding 2)))
                  
                  (q/stroke 0)
                  (-> data
                      jitter-1d
                      plot!))]
    (qa/applet
     :title name
     :setup #(do (setup) (draw-fn))
     :mouse-clicked draw-fn ;; would like this to be :resized
     :size [640 480]
     :features [:resizable])))


