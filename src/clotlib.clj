(ns clotlib
  (:require [quil.core :as q])
  (:require [quil.applet :as qa]))

;;; Distributions

(def TWO_PI (* 2 Math/PI))

(defn linscale
  "Apply a linear scaling such that an input bounded
   by (0,1) generates an output bounded by (lower,upper)"
  [source lower upper]
  (map #(+ lower (* (- upper lower) %))
       source))

(defn absclip
  "Reject values which are outside the range
  [-mx,mx]"
  [source mx]
  (filter #(> mx (Math/abs %)) source))

(defn uniform
  "Returns a lazy seq of numbers with a uniform
  distribution, in the range [0,1)"
  [] (repeatedly rand))

(defn normal-dist
  "Returns a lazy seq of numbers with a normal distribution.
  mean=0, sd=1"
  []
  ;; Box-Muller Transform
  ((fn nested [source]
     (let [u1 (first source)
           u2 (second source)
           R  (Math/sqrt (* -2 (Math/log u1)))
           z0 (* R (Math/cos (* TWO_PI u2)))
           z1 (* R (Math/sin (* TWO_PI u2)))]
       (lazy-seq
        (cons z0
              (cons z1
                    (nested (drop 2 source)))))))
   (uniform)))

;;;;;; DRAWING

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


;;;;;; TESTING

(def dist1 {:min 0
            :max 100
            :data (take 500 (linscale (absclip (normal-dist) 2) 50 30))})

(defn draw-data []

  (q/background 240)

  (q/stroke 220)
  (q/rect padding padding
          (- (q/width) (* padding 2))
          (- (q/height) (* padding 2)))
  
  (q/stroke 0)
  (-> dist1
      jitter-1d
      plot!))

(qa/applet
  :title "ex1"
  :setup #(do (setup) (draw-data))
  :mouse-clicked draw-data ;; would like this to be :resized
  :size [640 480]
  :features [:resizable])

(println "loaded")
