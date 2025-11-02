(defn punt [x y]
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

; enter initial point and a list of points
(defn mes-propera [init points]
    ((apply min-key #(init :dst %) points) :crt)) ; using min-key will return the element of the point
    ; iwth the smallest key however its a type punt, so we need to extract crt
    ; (apply min-key #(key condition) list)



; respuesta del chat: we can see that in the example it must be included in one singular function
; that means one function multiple arities
(defn punt-chat [x y]
  (fn
    ;; 1-arity: :crt or :plr
    ([msg]
     (cond
       (= msg :crt) [x y]
       (= msg :plr) (let [r (Math/sqrt (+ (* x x) (* y y)))
                           theta (Math/toDegrees (Math/atan2 y x))]
                       [r theta])
       :else (throw (ex-info "Unknown message" {:msg msg}))))

    ;; 2-arity: :dst with another point
    ([msg other]
     (if (= msg :dst)
       (let [[ox oy] (other :crt)]
         (Math/sqrt (+ (Math/pow (- x ox) 2)
                       (Math/pow (- y oy) 2))))
       (throw (ex-info "Unsupported operation with 2 args" {:msg msg}))))))


; multiple arity functions
(defn mult [x y]
    (fn 
        ([arg1]
            (println "single argument"))
        ([arg1 arg2]
            (println "two argument"))))
