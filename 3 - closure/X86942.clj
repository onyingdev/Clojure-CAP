; same function but use atom, that remembers the point, can be called anytime
; :setx and sety to change them

(defn punt [x y]
    (let [memx (atom x) memy (atom y)]
        (fn 
            ([arg] ; :crt :plr
                (cond 
                    (= arg :crt) (vec (list @memx @memy))
                    (= arg :plr) (let [r (Math/sqrt (+ (* @memx @memx) (* @memy @memy)))
                           theta (Math/toDegrees (Math/atan2 @memy @memx))]
                       (vec (list r theta)))))
            ([arg p] ; :dst :setx :sety
                (cond
                    (= arg :dst)
                        (let [[px py] (p :crt)] ; destructure point, calling function to return as :crt
                            (Math/sqrt (+ (Math/pow (- @memx px) 2) ; whatever this is
                            (Math/pow (- @memy py) 2))))
                    (= arg :setx) 
                        (reset! memx p)
                    (= arg :sety) 
                        (reset! memy p))))))

(defn mes-propera [init points]
    ((apply min-key #(init :dst %) points) :crt))

; original
(defn punt-old [x y]
    (fn ; define multi arity function
        ([condition] ; :crt and :plr
            (cond 
                (= condition :crt) (list x y)
                (= condition :plr) (let [r (Math/sqrt (+ (* x x) (* y y)))
                           theta (Math/toDegrees (Math/atan2 y x))]
                       (list r theta)))) 
        ([condition p] ; :dst
            (if (= condition :dst)
                (let [[px py] (p :crt)] ; destructure point, calling function to return as :crt
                    (Math/sqrt (+ (Math/pow (- x px) 2) ; whatever this is
                       (Math/pow (- y py) 2))))))))
