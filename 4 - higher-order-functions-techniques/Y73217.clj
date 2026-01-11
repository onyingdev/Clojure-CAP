; dicotomic search

;split middle auxiliar function
(defn split-half [v]
  (let [mid (quot (count v) 2)] ;; Integer division (e.g., 5/2 = 2)
    (split-at mid v)))

;base case, returns true or false
; buit o 1 o 2, 2 because the first element is the source
(defn trivial [vct] (<= (count vct) 2)) 
; success on reaching base case, check if empty or it matches
;returns true if found
(defn directe ; enters an array, deconstruct, first one is the number to search for
    [vct]
    (if (<= (count vct) 1)
        false ; do nothing
        (let [[x & rstv] vct]
            (if (some #{x} rstv) 
                true
                false))))

; returns 2 halfs, treated
(defn dividir
  "vct té més d'un element"
  [vct]
  (let [[x & rstv] vct ; let is similar to do ;; el pivot serà el prier element del vector
        [first-half second-half] (split-half rstv) ; split in the middle, we use destructuring
        first-half (conj first-half x) ; place last element on both, for seaching
        second-half (conj second-half x)]
    [first-half,second-half])) ; first half and second half

; vencer returns the partial solution, evaluates if its found
(defn vèncer [vct _ [sol1 sol2]]
    (or sol1 sol2))

; main schema
(defn dIv
  [trivial directe dividir vèncer]
  (letfn [(dIv' [vct]
            (if (trivial vct)
              (directe vct)
              (let [[x1 x2] (dividir vct)
                    y1 (dIv' x1) ; y1 and y2 returns true or false
                    y2 (dIv' x2)]
                (vèncer vct [x1,x2] [y1,y2]))))]
    dIv'))

(def dicotomica (dIv trivial directe dividir vèncer))

; note, this is dicotomic search, not binary search, in order to do binary we must modify the main dIv function
; to discard the lower half or upper half, it would be more efficient.
; The condition would be, check the first number, must be <= x and the last >= x