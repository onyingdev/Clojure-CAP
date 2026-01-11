; VORASAK ONYING
; SANTIAGO VILCACUNDO

; NEED CORRECTION
; cavall

; we know that the table is a predefined 8x8
(defn dins [[r c]] 
    (and (<= c 8) (> c 0) (<= r 8) (> r 0)))

; interesting, we cannot use iterations or recursive (for and recur) to loop n times and check it
; we can predefine the jumps and map it, filtering the positions if they are not in
; 1, 1 is left bottom corner
; note its first rowns and them columns

(defn moviments [[r c]]
  (let [moves [[-2 -1] [-2 1] [-1 -2] [-1 2] [1 -2] [1 2] [2 -1] [2 1]]]
        (->> moves
            (map (fn [[dr dc]] (list (+ r dr) (+ c dc))))
            (filter dins))))

(defn pot-anar3 [p q]
  (let [tres-salts-xf 
    (comp 
        (mapcat moviments) ; mapcat 
        (mapcat moviments) 
        (mapcat moviments))]
    (contains? (into #{} tres-salts-xf [p]) q)))