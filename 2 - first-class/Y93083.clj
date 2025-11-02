; 80/100
; En aquest problema heu d’implementar una sèrie de funcions en Clojure utilitzant la funció @reduce@. 
; No cal que pregunteu si podeu fer servir funcions auxiliars, és evident que sí.
; fold and reduce are essentially the same thing
; looking closely reduce is a higher order recursion function
; tips: when coding take a look at the returns, that gives a hint on what the parameters of reduce should be

(defn my-repeat [lst x p] ; repeat x p times, repeat is already defined in clojure
    (if (= 0 p)
        lst
        (my-repeat (conj lst x) x (dec p))))
        
; Feu una funció power que, donats un enter x i un natural p, retorni x elevat a p, és a dir, xp.
; use (repeat ntimes x), clojure defined
(defn power [x p]
    (reduce * (my-repeat '() x p)))

(defn my-count [n]
    (reduce (fn [acc lst] (inc acc)) 0 n)) ; function [param] (return body, can be any type!)
; note reduce iterates automatically from a given list
; however it cannot handle multiple lists they must be reduced first

(defn average [n]
    (/ (reduce + n) (my-count n))) ; pretty straight foward

(defn my-reverse [n]
    (reduce (fn [acc num] (cons num acc)) '() n))
; i mean, im still using reduce to reverse the list and count not sure if this is valid hahaha
(defn  build-palindrome [n]
    (if (even? (my-count n))
        (concat (my-reverse n) (rest n))
        (concat (my-reverse n) n)))

; important reduce only returns 2 arguments
(defn odds-n-evens [n] ; x is the iterator, will automatically iterate through n
    (reduce (fn [[od ev] x] ; no need to type x on the return
        (if (even? x)
            (list od (my-reverse (conj ev x)))
            (list (my-reverse (conj od x)) ev)))
    '(() ()) n))
; here we needed to use a vector, since reduce only returns [acc x]
; we can declare that by using [[odds evens] x]
; reduce will only recur with acc and iterate throu x
; this case we needed lists so we can convert vectors as lists


(defn number-in [x lst]
    (some #(= x %) lst))

(defn remove-list [x y] ; iterate throug x, check if in y
    (reverse (reduce (fn [acc n] 
        (if (some #(= n %) y) ; some, checks if number is in list/vector very useful
            acc
            (conj acc n)))
    '() x)))

; ----------------------------- LEARNING CODE -----------------------------

; juxt version, so much easier
(defn odds-n-evens-juxt [n]
    ((juxt (partial filter odd?) (partial filter even?)) n))