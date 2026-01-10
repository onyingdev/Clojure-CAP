; VORASAK ONYING
; SANTIAGO VILCACUNDO

; make a function animal and code in the sound
; animals return the sound of animal
; parlarn prints n times the sound
(ns animals)

(defn animal []
  (fn [func]
    (cond ; this defines a similar thing to class function in python
      (= func :speak) "grr"
      :else nil)))

(defn gat [parent]
  (fn [func]
    (cond ; 
      (= func :speak) "meu"
      :else (parent func)))) ; this class represent the original, 
      ; but when it matches it overrides
      ; in case animals had other functions like walk or roar
      ; it would access the parent instead.

(defn tigre [parent]
  (fn [func] ; tigre uses the parent class
    (parent func)))

(defn parlarn [obj n]
  (let [sound (obj :speak)] 
    (println (str/join " " (repeat n sound)))))