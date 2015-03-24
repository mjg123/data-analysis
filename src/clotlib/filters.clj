(ns clotlib.filters)

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

;; TODO: shift, scale
