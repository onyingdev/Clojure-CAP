(defn eql [n m]
    (and ; if both ok return true
        (= (count n) (count m)) ; check same length
        (every? true? (map = n m)))) ;every? will treat each element individually
        ; true? will check if its true

; filter version
(defn prod-of-evens [n]
    (reduce * (filter even? n)))

; ----------------------------- LEARNING CODE -----------------------------

; take while version, this version will stop the moment theres an odd on the list
(defn prod-of-evens-tw [n]
    (reduce * (take-while even? n)))