(ns knn)

; create distance functions

; euclidean
; for each index, i power2 the difference of index, then sum all and sqrt
; define another function to enter y
; tip for building this kind of functions is to go in and out, go from the inner one to the outer one
(defn de [vx vy]
    (if (not= (count vx) (count vy)) ; check same length
        false
        (Math/sqrt (reduce + (map #(Math/pow (- %1 %2) 2) vx vy)))))


; manhattan
; map applying substraction and absolute value to each, reduce sum
(defn dm [vx vy] 
    (if (not= (count vx) (count vy))
        false
        (reduce + (map #(Math/abs (- %1 %2)) vx vy))))

; Hamming
; check if %1 and %2 matches, if they dont = 1, if they do = 0, reduce sum, divide by size
(defn dh [vx vy]
    (if (not= (count vx) (count vy))
        false
       (/ (reduce + (map #(if (= %1 %2) 0 1) vx vy)) (count vx))))