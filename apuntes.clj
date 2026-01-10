; def 
; is used to bind values, it can be any data or function
; define global variables but we are usually modifying binds
; similar to var, but not quite. 
(def s 1) ; s = 1
(def f (+ s 2)) ; f = 3


; lists
; basically contains an array of stuff
; this will give ERROR since evaluation and list uses the same parenthesis system
(1 2 3 4)
("hola" 2 3 4)
;use quoting to not evaluate the list and treated as an element
'(1 2 3 4)
'()
(def x ("a" "b" 2 3 4)) ; wrong
(def x '("a" "b" 2 3 4)) ; ok x = ("a" "b" 2 3 4) 
; list can have operations too, see below


; conditionals
(if boolean-expression
    true-case
    false-case)
(when bool-expression
    true-case)
(cond  ; this is a more general case 
    bool-expr case
    bool-expr case
    ...
    bool-expr case)


; anonimous functions, you gonna see this very regularly
(fn [parameters1 param2 ...] (expression-body))
(def valor_absolut (fn [x] ((if (> x 0) + -) x))) ; we are binding 'valor_absolut' with fn 
(def valor_absolut #((if (> % 0) + -) %) ) ; same thing, # for short, also has more aura. use %1, %2 ... %n to indicate paramters. %& for sequential input
; defn is macro to do the same shit but we skip extra lines 
(defn nom-de-funció "Comemnt" [p1 p2 ... pN]   expr1 ... exprM )
(defn valor_absolut "Calcula |x|" [x] ((if (> x 0) + -) x)) ; same thing
; we can use to define diferent input parameters too, for instance we want different result for 1 param but another for 2 params.
(defn producte
   "Retorna 1, el paràmetre o el producte depenent del nombre de paràmetres"
   ([] 1) ; no params
   ([x] x) ; 1 param
   ([x y] (* x y)) ; 2 params
   ([x y & things] things)) ; 2 params and infinite sequence (& things)


; evaluates expressions sequentially, thats it. the last one is the return. so is implicit pretty much in all the body of every function
(do 
   (println "Efecte colateral: escrivim un missatge") ;; S'escriu el missatge
   (* 5 4 3 2 1)                      ;; Aquest valor, 120, es perd 
   (quot 343 5))                      ;; Aquest valor és el retorn del do



; let
; alright another common special form, basically binds stuff locally
; very useful for temporal data
(let [s1 ex1  s2 ex2 ... sN exN]   expr1 ... exprM )
(let [num1 1 num2 2 num3 3] 
    (+ num1 num2 num3)) ; note that we can obviously use the local binds inside the body of let
    ; we can still use value from outside the let. thats cool.
(defn f [x] (let [num1 1 num2 2 num3 3] 
    (+ num1 num2 num3 x)))

; let can also be used to deconstruct, very important. This is called Destructuring
; for instance, we want to destructure the vector into separate elements
(def vct [1 2 3 4 5])
(let [[head & tail] vct]
  (println head) ;; 1
  (println tail)) ;; (2 3 4 5)
; for complex destructuring
(def user-data
  {:name "Neo"
   :stats {:health 100 :mana 50}
   :inventory ["Red Pill" "Blue Pill"]})

(let [{:keys [name] {:keys [health]} :stats [item1] :inventory} user-data]
       (println name "has" health "HP and chose the" item1))




; how to call auxiliar function? 
(defn smaller? [x y] (if (< x y) ; say we have this that evaluates if x < y
    true
    false))  
(defn f [x y] (if (smaller? x y) ; basicaly do this, remember to match the param number, dumbass.
    (+ x y)
    (- x y)))



; letfn
; same thing with let, but guess what we can do functions too
; i got kinda lazy writing down this stuff so fk u.
(letfn [(name1 [args] body)
        (name2 [args] body)]
  ;; body where both name1 and name2 are available
  (name1 ...))



; how to input arrays
(f [1 2 3 4] [1 2 3 4])
(defn [v1 v2] (...))



; closure, my god my head, aaaaah
; this is basically the schema for multiarity closure functions
; remember its basically a function that returns a function with a predefined local environment
; the first function is used as an "initializer" the second one is the one that its actually called
(defn make-closure []
  (fn 
    ;; Arity 0: No parameters
    ([] 
      (println "Default behavior"))
    
    ;; Arity 1: One parameter
    ([x] 
      (println "You passed:" x))))

;another implementation of closures are state machines, very cool, very common and a lot of headache
(defn machine-builder [config-params]         ;; LAYER 1: Configuration
  (letfn [(state-holder [current-state]       ;; LAYER 2: State Memory
            
            (letfn [(input-handler [input]    ;; LAYER 3: Action / Transition
                      
                      (if (stop-condition? input)
                        ;; CASE A: Stop and return final result
                        current-state
                        
                        ;; CASE B: Calculate new state and RECURSE
                        (let [next-state (calculate current-state input)]
                          (state-holder next-state))))]
              
              ;; Return the input handler to the user
              input-handler))]

    ;; Initialize the machine with starting state
    (state-holder initial-value)))



(def a (animal)) ; return grr
(def g (gat (animal))) ; return gat


; closures can also implement class and subclass
(defn parent-class []
  (fn [func]
    (cond ; this defines a similar thing to class function in python
      (= func :do1) "parent-do1"
      (= func :do2) "parent-do2"
      (= func :do3) "parent-do3"
      :else nil)))
; subclass
(defn subclass [parent]
    (fn [func]
        (cond
            (= func :do1) "subclass-do1" ; this one overrides the parent class for own
            :else (parent func)))) ; the rest uses the parent

(def a (parent-class)) ; a is the parent
(def b (subclass (parent-class))) ; b is the sub
(a :do1) ; "subclass-do1"
(b :do1) ; "parent-do1"



; divide and conquer
(def solucio (dIv trivial directe dividir vencer))
; trivial function that detects base case
; directe function to apply to base case
; dividir divide the in case we are not in base case
; vencer action to compose the subproblems, merge them together


; define initial stack, initial state
(defn initialNQ [n]  ;; columna 1, solució parcial buida []
  [1 n []])
; objective goal function, the goal check
(defn objNQ [[c n psol]]  ;; acabem quan la columna a considerar c > n
  (> c n)) ; if c > n means we already check every column
; main function
(defn succNQ [[c n psol]]  ;; donats una columna, n i una solució parcial, teh current state
  (letfn [(valid [psol [c r]] ; checks candidate queen vs all previous queens
            (letfn [(test [[c' r']]
                      (and (not= (+ c' r') (+ c r)) ; check diagonal
                           (not= (- c' r') (- c r)) ; check diagona;
                           (not= r' r)))] ; check row
              (every? identity (map test psol))))] ; returns a list of boolean if one is false throws false
    (for [r (range 1 (inc n)) :when (valid psol [c r])] ; c and psol comes from the beginning (on top), r is initialized here
      [(inc c) n (conj psol [c r])]))) ; returns this at success! ej [3 4 [[1 3] [2 1]]]

; backtrack main schema
(defn bTck [succ obj]   
  (letfn [(bTck' [v]
            (cond
              (empty? v)     []
              (obj (peek v)) (conj (bTck' (pop v)) (peek v))
              :else          (let [x (peek v)]
                               (recur (foldr (flip conj) (pop v) (succ x))))))]
    (fn [inicial]
      (bTck' [inicial]))))

(defn solucio-NReines [n]
  (let [[[_ _ s] & cua] ((bTck succNQ objNQ) (initialNQ n))]
    s))
(defn nombre-solucions-NReines [n]
  (count ((bTck succNQ objNQ) (initialNQ n))))
; i genuienly have no idea how this works










; custom foldr and foldl functions
(defn foldl [f x0 s]
  (if (empty? s) x0
      (let [[cap & cua] s]
        (recur f (f x0 cap) cua))))

(defn foldr [f x0 s]
    (if (empty? s) x0
        (let [[cap & cua] s]
            (f cap (foldr f x0 cua)))))

; custom flip
(defn flip [f]
  (fn [& xs]
    (apply f (reverse xs))))



; common operants, might not be all of them but should be the most common ones
+ : Returns the sum of numbers.
- : Subtracts numbers (or negates if one argument).
* : Returns the product of numbers.
/ : Divides the first number by the rest.
quot : Returns the quotient of integer division.
rem : Returns the remainder of integer division.
mod : Returns the modulus (floored remainder).
inc : Increments a number by 1.
dec : Decrements a number by 1.
max : Returns the greatest number in a set.
min : Returns the smallest number in a set.
map: automatically maps variables from a map or list ; very useful for iteration
reduce : basically applys for all the sequence, reduces using a function ; very useful

= : Returns true if values are equal (value equality).
== : Returns true if numbers are equal, ignoring type (e.g., 1 vs 1.0).
not= : Returns true if values are strictly not equal.
< : Less than.
> : Greater than.
<= : Less than or equal to.
>= : Greater than or equal to.
compare : Returns -1, 0, or 1 based on comparison sort order.
identical? : Tests if two arguments are the exact same object in memory.

and : Boolean AND (short-circuiting).
or : Boolean OR (short-circuiting).
not : Boolean NOT (inverts truth value).
true? : Returns true if the argument is specifically true.
false? : Returns true if the argument is specifically false.
nil? : Returns true if the argument is nil.

bit-and : Bitwise AND.
bit-or : Bitwise OR.
bit-xor : Bitwise exclusive OR.
bit-not : Bitwise complement.
bit-shift-left : Shifts bits to the left.
bit-shift-right : Shifts bits to the right (sign-preserving).
unsigned-bit-shift-right : Shifts bits right (zero-fill).
bit-set : Sets a specific bit to 1.
bit-clear : Sets a specific bit to 0.
bit-flip : Toggles the value of a specific bit.
bit-test : Checks if a specific bit is set.

' (Quote) : Prevents evaluation of the form (e.g., 'foo).
@ (Deref) : Reads the current value of a reference/atom (shorthand for deref).
# (Dispatch) : Used for regex (#"pattern") or anonymous functions (#(inc %)).
^ (Meta) : Attaches metadata to the following form.
#' (Var) : Gets the Var object itself, not its value.
-> (Thread-first) : Pipes result of previous form as first arg of next.
->> (Thread-last) : Pipes result of previous form as last arg of next.
&

def : Defines a global variable (Var).
if : Conditional branch (checks if truthy or falsey).
do : Evaluates multiple expressions, returning the last one.
let : Creates local bindings (variables) within a scope.
quote : Returns the form unevaluated (e.g., (quote x) is just x).
var : Returns the Var object binding (like a pointer to the variable).
fn : Defines an anonymous function.
loop : Establishes a recursion point.
recur : Jumps back to the nearest loop or function start (tail recursion).
throw : Throws an exception.
try : Starts a block to catch exceptions.
catch : Catches an exception in a try block.
finally : Code that always runs after a try block.
monitor-enter / monitor-exit : Low-level Java synchronization primitives.

when : Like if, but no "else" branch (implicit do).
when-not : Inverse of when (executes if falsey).
if-not : Inverse of if (executes "then" if falsey).
cond : Multi-branch conditional (like switch or else if chains).
case : Fast constant-time matching on values (like strict switch).
for : List comprehension (generates a sequence, not a for-loop).
dotimes : Executes a body N times.
while : Repeats body while a condition is true (requires side effects).

;list operations
cons : Adds an item to the front of a sequence.
conj : Adds an item to a collection in the most efficient way (front for list, back for vector).
first : Returns the first item.
rest : Returns the sequence without the first item (never returns nil).
next : Returns the sequence without the first item (returns nil if empty).
get : Looks up a value in a map or vector by key/index.
assoc : Associate (add/update) a key-value pair in a map/vector.
dissoc : Remove a key from a map.
count : Returns the number of items.
empty? : Returns true if the collection has no items.
contains? : Checks if a key (or index) exists in a collection.
peek
pop
list : makes a list
list?

union : Merges two sets.
intersection : Returns elements present in both sets.
difference : Returns elements in the first set but not the others.
select : Returns a set of items matching a predicate.
subset? : Checks if one set is fully contained in another.
superset? : Checks if one set fully contains another.

atom : Creates a managed mutable reference.
reset! : Sets the value of an atom immediately.
swap! : Updates an atom by applying a function to the current value.
locking : Executes body within a synchronized lock (mutex).
future : Runs a body of code in a separate thread.
delay : Defines code that runs only when usually dereferenced (lazy eval).
promise : Creates a value that will be delivered later.
deliver : Provides the value for a promise.

apply : Calls a function with arguments provided as a list.
partial : Returns a new function with some arguments pre-filled.
comp : Composes functions (creates f(g(x))).
identity : Returns the argument unchanged.
complement : Returns a function that outputs the opposite boolean truth.