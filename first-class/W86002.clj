(defn eql [n m]
    (and ; if both ok return true
        (= (count n) (count m)) ; check same length
        (every? true? (map = n m)))) ;every? will treat each element individually
        ; true? will check if its true

; filter version
(defn prod-of-evens [n]
    (reduce * (filter even? n)))

(defn my-reverse [n]
    (reduce (fn [acc num] (cons num acc)) '() n))

; reduce (operator) (collections),it will apply the operator to each element for collection
; passes de accumulated result
; we basically add the first number in the front and pass the whole list
; the first collection is the accumulated list
; num is the first element treated of n (they need to match)

; use map to multiply each element by column and reduce sum
(defn scalar-product [n m]
    (reduce + (map * n m)))

; take while equal, then count resulting list
(defn count-in [lst m]
   (map #(count (filter (fn [x] (= x m)) %)) lst))
;  here we are using anonymous function #(...), its another way to define them
; %1 %2 %3 %... enters as parameters, equivalent to (fn [x] (= x n))
; flatten merge all the lists in one list, useful
; map enters only collections, but not collection of colletions
; map basically treat each element of the list, we can use this to count each list
; we input % as filter parameter, each individual list will be given by map

; use take-while, keep in mind words start with letter that is not ' '
; and ends when there is ' '
; skip the firsts ' '
; we could clear the first spaces by using drop-while
(defn first-word [phrase]
    (apply str 
        (take-while #(not (= % \space)) ; get first word
            (drop-while #(= % \space) phrase)))) ; str without the first spaces

; ----------------------------- LEARNING CODE -----------------------------

; take while version, this version will stop the moment theres an odd on the list
(defn prod-of-evens-tw [n]
    (reduce * (take-while even? n)))

(defn count-num [lst m]
    (count (filter (fn [x] (= x m)) lst)))
; count-num, example of using (f #(...) param)  function

(defn clear-spaces [p]
    (apply str (filter #(not (= % \space)) p)))
; str transforms the list into string

(defn drop-spaces [p]
    (apply str (drop-while #(= % \space) p)))

(defn word-one [p]
    (apply str (take-while #(not (= % \space)) p)))