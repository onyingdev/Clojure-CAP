; parenthesis, ah this one is fun
; the conditions are: we cannot have more closed parenthesis than opend parenthesis.
; to do that we count, add one for every opened and substract for each closed, this checks the correct amount.
; the MOST IMPORTANT part is that in any given moment the number can be negative. this checks the parenthesis are closed properly
; if its negative you have closed more than you opened previously and its WRONG, and your code sucks.

(defn initial [n] ; n represents the total parenthesis we can open
[1 n []]) ; 1 current parenthesis, we start with an open parenthesis, n total p, and [] current solutions
; this is the recurive function
(defn succ )

; this is the objective, checks when is it finished
(defn obj [[p n psol]] 
(> p n)) ; when we reached the end its finished

; main schema
(defn bTck [succ obj]   ;; ja coneixeu flip i foldr, les fem servir aquí
  (letfn [(bTck' [v]
            (cond
              (empty? v)     []
              (obj (peek v)) (conj (bTck' (pop v)) (peek v))
              :else          (let [x (peek v)]
                               (recur (foldr (flip conj) (pop v) (succ x))))))]
    (fn [inicial]
      (bTck' [inicial]))))