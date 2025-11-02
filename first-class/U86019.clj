; pipelining, using macros makes it much easier and cleaner

(def f1
    #(->> %
        (filter even?)
        (map (fn [x] (* x 2)))
        (reduce +)))
; write it like this, is much cleaner
; remember that using ->> will push % into the back of the first f
; the f will be pushed into the back of the second etc
; -> pushes it in the front

(def f2
    #(->> %
        (filter pos?)
        (take 3)
        (map (fn [x] (* 10 x)))))

(def f3
    #(->> %
        (filter (fn [x] (>= x 5)))
        (map (fn [y] (* 3 y)))
        (sort)
        (reverse)))

(def f4
    #(->> %
        (filter (fn [x] (not (= (mod x 3) 0))))
        (take 5)
        (map (fn [y] (* y y)))))

; most of the uses ->> until now i couldnt find a reason to use ->