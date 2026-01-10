; VORASAK ONYING
; SANTIAGO VILCACUNDO

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


; main function

; knn enters a distance function and a vector of maps, returns a function
; the return function that enters a vector
; fdist returns the distance between 2 vectors
; plants is a vector of maps, with :y being the name of the plant and :x the value of the vector
; first get the minimum distance with all plants, then obtain the name of the plant.
; map applying for all plant outer get minimum
; apply first class in order to return a function
(defn knn [fdist plants] 
    (fn [p] ; p is the new plant to classify
        (:y (apply min-key #(fdist (:x %) p) plants))))

; apply is very similar to map, it apply the function over a given list