(def mides 
    #(->> %
        (map (fn [x] (count x)))
        (filter odd?)
        (reduce +)))


; NOTE: (noms {"Pep" 19 "Anna" 25 "Joan" 15 "Pau" 8 "Montse" 22})
; the {} denotes a map, it has key and value
(defn noms [people]
    (sort #(compare (first %1) (first %2)) 
        (for [[name age] people :when (> age 18)] name)))

; accessing map with predefined key naming
; use map to treat each element
; filter and then reduce
(defn suma [lst]
   (map (fn [{quantitat :quantitat preu :preu}] (* preu quantitat)) lst))

; not you cannot use defn with an anonymous function, defn expects a parameter [x]
; however def does not
(def suma 
    #(->> %
        (map (fn [{quantitat :quantitat preu :preu}] (* preu quantitat)))
        (filter (fn [x] (>= x 100)))
        (reduce +)))









; el patron
(defn run-pipeline [data & steps]
  (reduce (fn [d step] (step d)) data steps))

; note that this does not work, {} indicates map
(defn noms-wrong [people] ; '() [] ok {} not ok persistent map
    (let [[name age] people]
        name)) 
; for {} we need to use for, filter, functions that loops through maps

(defn mides-nomacro [n]
    (reduce + (filter odd? (map #(count %) n))))

; enter lst as {} no []
(defn preu [lst] (let [{quantitat :quantitat preu-unitari :preu-unitari} lst]
    (* quantitat preu-unitari)))
