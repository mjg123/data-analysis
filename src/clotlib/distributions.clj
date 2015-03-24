(ns clotlib.distributions)

(def TWO_PI (* 2 Math/PI))

(defn uniform
  "Returns a lazy seq of numbers with a uniform
  distribution, in the range [0,1)"
  [] (repeatedly rand))

(defn normal
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
