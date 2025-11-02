(defn build-palindrome [n]
  (letfn [(rev [arr]
            (if (empty? arr)
              '()
              (concat (rev (rest arr)) (list (first arr)))))]
    (if (odd? (count n))
      (concat (rev n) n)
      (concat (rev n) (rest n)))))

(defn remove-list [x y] ;remove list with letfn
    (letfn [(remove-num [lst n]
        (if (empty? lst)
            '()
            (let [num-1 (first lst) num-2 (rest lst)]
                (if (= num-1 n)
                    (remove-num num-2 n)
                    (cons  num-1 (remove-num num-2 n))))))]
        (if (empty? y)
            x
            (remove-list (remove-num x (first y)) (rest y)))))


(defn odds-n-evens [lst]
  (letfn [(split [xs odds evens]
            (if (empty? xs) ; base case return 
              (cons (reverse odds) (list (reverse evens)))
              (let [x (first xs)
                    tail (rest xs)]
                (if (even? x)
                ; good strategy for recursive calls is to introduce "tracking" paramters
                  (recur tail odds (conj evens x)) ; if its even recur, but updating even 
                  (recur tail (conj odds x) evens)))))] ; otherwise
    (split lst '() '())))

; missing one

; ----------------------------- LEARNING CODE -----------------------------

(defn my-reverse [n]
    (if (empty? n)
        '()
        (concat (reverse (rest n)) (list (first n)))))

(defn build-palindrome-test [n]
    (if (odd? (count n))
        (concat (my-reverse n) n)
        (concat (my-reverse n) (rest n))))

(defn remove-num-aux [lst n]
    (if (empty? lst)
        '()
        (let [num-1 (first lst) num-2 (rest lst)] ;if not empty, check the first value
            (if (= num-1 n)
                (remove-num-aux num-2 n) ;if yes, pop
                (cons num-1 (remove-num-aux num-2 n)))))) ;no attach and recur
       
(defn remove-list-aux [x y]
    (if (empty? y)
        x
        (remove-list-aux (remove-num-aux x (first y)) (rest y))))

; head and tail strat
; inefficient
(defn get-odds [lst]
    (if (empty? lst)
        '()
        (let [head (first lst) tail (rest lst)] 
            (if (even? head) ; check if is even
                (get-odds tail) ;if yes, keep only tail
                (cons head (get-odds tail)))))) ; if no keep head

(defn get-evens [lst]
    (if (empty? lst)
        '()
        (let [head (first lst) tail (rest lst)] 
            (if (odd? head) ; check if is odd
                (get-evens tail) ;if yes, keep only tail
                (cons head (get-evens tail)))))) ; if no keep head

(defn odds-n-evens-auxVer [n]
    (cons (get-odds n) (list (get-evens n))))