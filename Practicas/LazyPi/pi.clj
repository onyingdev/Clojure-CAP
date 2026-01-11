

;; --------------------------------------------------------------------------
;; 1. Define infinite series pi4
;; --------------------------------------------------------------------------
;;  Definiu un sèrie infinita pi4 amb els termes de la sèrie de Taylor.
;;  Formula: 1 - 1/3 + 1/5 - 1/7 + ...

(def pi4
  (map / 
       (cycle [1 -1])
       (iterate #(+ 2 %) 1)))

;; --------------------------------------------------------------------------
;; 2. Define function pi(e)
;; --------------------------------------------------------------------------
;;  Definiu una funció pi que, donat un valor e, en retorni l'aproximació...
;; sumant els termes... fins que el valor absolut de la diferència de dos termes
;; consecutius sigui menor que e.

(defn pi [e]
  (let [terms pi4]
    ;; We use a loop to sum terms manually so we can check the stopping condition
    ;; on the current term before adding it (or immediately after).
    ;; The "difference between consecutive partial sums" is equal to the value 
    ;; of the current term being added.
    (loop [ts terms
           acc 0.0]
      (let [current-term (first ts)]
        (if (< (Math/abs (double current-term)) e)
          (* 4.0 acc) ;;  Multiply result by 4 to get Pi
          (recur (rest ts) (+ acc current-term)))))))

;; --------------------------------------------------------------------------
;; Test Cases (Joc de proves)
;; --------------------------------------------------------------------------

;; Uncomment the lines below to verify against

;; (println "First 10 terms of pi4:")
;; (println (take 10 pi4))
;; ;; Expected: (1 -1/3 1/5 -1/7 1/9 -1/11 1/13 -1/15 1/17 -1/19) 

;; (println "Pi with precision 0.0001:")
;; (println (pi 0.0001))
;; ;; Expected approx: 3.127307603135705 [cite: 11]

;; (println "Pi with precision 0.0000001:")
;; (println (pi 0.0000001))
;; ;; Expected approx: 3.1411453670589253 [cite: 13]