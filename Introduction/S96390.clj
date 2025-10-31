(defn my-count1 [x]
    (if (empty? x)
      0
      (inc (my-count1 (rest x)))))  ;return the increment of whatever result it has 

(defn my-count2 [x]
  (loop [it 0 list x] 
    (if (empty? list)
      it
      (recur (inc it) (rest list)))))

(defn my-maximum1 [n]
  (if (= (my-count2 n) 1)
    (first n)
    (let [first-num (first n) 
          second-num (second n)]
      (if (<= first-num second-num)
        (my-maximum1 (rest n))
        (my-maximum1 (concat (list first-num) (rest (rest n))))))))
    
(defn my-maximum2 [n]
    (loop [max (first n) list (rest n)]
      (if (empty? list)
        max
        (let [second-num (first list)]
            (if (< max second-num)
                (recur second-num (rest list))
                (recur max (rest list)))))))

(defn average1 [n]
    (if (empty? n)
        0
        (letfn [(aux  [arr total count] ;define auxiliar function, array total count
        ;if not empty keep recurring, and inc count, add stack first and send rest
        ;when empty check total and count
            (if (empty? arr) 
                (if (= count 0) 
                    0 ;if 0, no numbers
                    (/ total count)) ;if more divide
                (aux (rest arr) (+ total (first arr)) (inc count))))] 
            (aux n 0 0))))

(defn sum [n]
    (if (empty? n)
        0
        (+ (first n) (sum (rest n)))))

(defn averagetest [n]
    (let [num (my-count1 n)]
        (if (= num 0)
            0
            (/ (sum n) num))))

;using loop and recur
(defn average2 [n]
    (loop [n n total 0 count 0]
        (if (empty? n)
            (if (= count 0)
                0
                (/ total count))
            (recur (rest n) (+ total (first n)) (inc count)))))