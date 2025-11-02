; warning some part of these codes are wrong

(defn abs-value [x]
  (if (>= x 0)
    x
    (* x -1)))

(defn power1 [x p]
  (if (= p 0)
    1
    (* x (power1 x (dec p)))))

(defn power2 [x p]
  (if (= p 0) 1   
      (loop [result x exp p] 
        (if (= exp 1)
          result
          (recur (* result x) (dec exp))))))

(defn factorial [x]
  (if (= x 0)
    1
    (* x (factorial(dec x)))))

(defn prime? [p]
  (if (or (= p 0) (= p 1) (even? p))
    false
    (if (= (mod (factorial (- p 1)) p) (mod -1 p))
      true
      false)))


; ----------------------------- LEARNING CODE -----------------------------
