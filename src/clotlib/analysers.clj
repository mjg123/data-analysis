(ns clotlib.analysers)

;; TODO - bucketize, sd, mean, mode, median, sum, etc

(defn summary [source]
  (let [sum (reduce + source)
        n   (count source)
        sorted (sort source)]
    {:sum sum
     :n n
     :mean (/ (* 1.0 sum) n)
     :median (if (odd? n)
               (nth sorted (/ (dec n) 2))
               (* 0.5 (+ (nth sorted (/ n 2))
                         (nth sorted (dec (/ n 2))))))
     :mode (key (first (sort-by (comp - val) (frequencies source))))}))
;; TODO mode is not always defined

