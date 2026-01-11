; VORASAK ONYING
; SANTIAGO VILCACUNDO

; NEED CORRECTION!
; interpret

(ns petit-lp)

; we need to define the main avaluation function
; 
(defn aval [expr env]
  (let [[op arg1 arg2] expr]
    (case op
        ; base case, we return the env
        :val arg1
        :var (get env arg1)

        ; operands
        :add (let [v1 (aval arg1 env) v2 (aval arg2 env)]
            (when (and v1 v2) (+ v1 v2)))
      
        :sub (let [v1 (aval arg1 env) v2 (aval arg2 env)]
            (when (and v1 v2) (- v1 v2)))
      
        :mul (let [v1 (aval arg1 env) v2 (aval arg2 env)]
            (when (and v1 v2) (* v1 v2)))
      
        :div (let [v1 (aval arg1 env) v2 (aval arg2 env)]
            (when (and v1 v2)
               (if (zero? v2)
                (do (println ">> Divisió per zero!") nil) ; in case we are dividint by zero
                (/ v1 v2))))
      nil))) ; returns nothing in case its not recognized.

; we need to return the whole environment, where the data is stored 
; thats why we need another function
; 
(defn executar-pas [env accio]
  (let [[cmd arg1 arg2] accio]
    (case cmd
      :view 
      (let [resultat (aval arg1 env)]
        (when resultat (println ">>" resultat))
        env)

      :ass 
      (let [valor (aval arg2 env)]
        (if valor
        ; (assoc map key value), it returns a new value
          (assoc env arg1 valor)
          env))
      env)))


(defn interpretar [programa]
  (reduce executar-pas {} programa))