(defn my-map [f rng]
    (for [x rng] (f x)))
; to call function, we need to call it like a function
; (f param), [x rng] binds rng to x which is the range of list

(defn my-filter [f rng]
    (for [x rng :when (f x)] x))
; binds rng to x, use conditional :when applying f to x
; returns x, we need to return x
; (for [param/bind :when ()] ret)

; equivalent to (map * (range ...) (range ...))
; iterator i, iterate and multiple each
(defn my-zip-with [f rng1 rng2]
    (for [i (range (min (count rng1) (count rng2)))] ; map gets the shortest list
        (f (nth rng1 i) (nth rng2 i)))) ; basically iterate for(i < n)
; using nth, basically enters a index position (nth '(1 2 3) 2) -> 2

; note for will iterate though all combinations of all the variables
(defn thingify [rng1 rng2]
    (for [i rng1 j rng2 
        :when (= 0 (mod i j))]
            [i j]))
; using when will call the condition only when it matches
; whe use if will call it even when we dont want to

; factors divisors 1 and itself, check up to n/2 (optimise, optional haha)
(defn factors [n]
    (for [i (range 1 (inc n))
        :when (= 0 (mod n i))]
            i))

; ----------------------------- LEARNING CODE -----------------------------

(defn t [n m]
    (if (= 0 (mod n m))
        true
        false))

        ; for(i < rng1 (for(j < rng2))) check if divisor
; careful with the parenthesis
; note this version uses rng instead of count
; will write nil if use if cond, need to use cond instead
(defn thingify-incorrect [rng1 rng2]
    (for [i rng1]
        (for [j rng2]
           (if (= 0 (mod i j))
                [i j]))))