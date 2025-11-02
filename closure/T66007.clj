; partial applies the operand to all the numbers
; (op n1 n2 n3 ...)
; it keeps it and then applies the state to any number of function
; (partial f a b) returns a function g -> using (g x y ...) = (f a b x y ...)
; to enter any number of arguments use &, (defn f [& arguments]) will return it as a list
(defn my-partial [f & param]
        (letfn [(g [& p]
            (apply f (apply f param) p))]
        g))
; I HAVE BEEN DEBUGGING THIS BS FOR 3 HOURS AND IT WAS A WRONG PARENTHESIS 
; im gonna kms

; (apply + '(1 2 3) (+ 2 3))

; ((partial + 3) (+ 2 3) 2) 
;; equivalent
; (+ 3 (+ 2 3) 2)

; (f a (g b c) d)

(defn partial-test [f & param]
    (apply f param))

