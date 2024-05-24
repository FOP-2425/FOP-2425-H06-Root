;; Aus der Vorlesung kennen Sie bereits diesen Weg zur Berechnung
;; Bei Übersetzung in Java fordern, dass if mit ternärem Operator ersetzt wird!

;; Unbedingt darauf hinweisen, dass hier die Folge mit fib(0) = 0 beginnt!

;; Achtung! cond wird in der Vorlesung 04b, Folie 60 eingeführt! (Oder zählen nicht die Seitenzahlen des systematischen Durchlaufs sondern der kurzen Folien??)

;; Type: natural -> natural
(define (fib-rec-classic n)
  (if (<= n 1) n (+ (fib-rec-classic (- n 1)) (fib-rec-classic (- n 2))))
)


;; Auf eine andere Art und Weise
;; Hier werden Konstrukte aus der Vorlesung benutzt:
;; local, cond, Definition von eigenen Konstanten
;;(define fib0 0)
;;(define fib1 1)

;; Type: natural -> natural
(define (fibonacci-recursive-different n)
  (local ((define (do-the-recursion a b n)
           (cond
             [(<= n 0) a] ;; Rekursionsanker
             [else (do-the-recursion b (+ a b) (- n 1))])))
  (do-the-recursion 0 1 n))
)


(check-expect (fibonacci-recursive-different 0) 0)
(check-expect (fibonacci-recursive-different 1) 1)
(check-expect (fibonacci-recursive-different 2) 1)
(check-expect (fibonacci-recursive-different 3) 2)
(check-expect (fibonacci-recursive-different 4) 3)
(check-expect (fibonacci-recursive-different 5) 5)
(check-expect (fibonacci-recursive-different 6) 8)
(check-expect (fibonacci-recursive-different 7) 13)
(check-expect (fibonacci-recursive-different 8) 21)
(check-expect (fibonacci-recursive-different 9) 34)
