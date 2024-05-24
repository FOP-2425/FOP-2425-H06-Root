#lang racket/gui
;; Der Code zur Visualisierung funktioniert nur wenn man den gesamten Sprachumfang von Racket verwendet.
;; Der wichtige Codeteil (Funktion draw-koch-snowflake) ist jedoch syntaktisch korrekt gemäß der "Advanced Student" Sprachsyntax


(define DEPTH 5) ; Maximale Rekursionstiefe
(define INITIAL-LENGTH 400) ; Anfangslänge der Seite des gleichseitigen Dreiecks


(define (draw-line g x1 y1 x2 y2)
  (send g draw-line x1 y1 x2 y2)
)

;; Das Konstrukt begin kommt erst in VL 04d vor.... Es gibt nicht wirklich einen anderen Weg, in der Advanced Student Language mehrere Expressions nacheinander zuzulassen.
;; local und cond kamen schon vor...

;; Oder muss ich das (für die Testbarkeit mit JUnit) sowieso umschreiben sodass man hier eine Liste von Punkten erstellt? Bzw. Arrays in Java? Wäre extrem dumm iwie

;; Darauf hinweisen, dass der Identifier 60° in Java nicht erlaubt ist.

;;;;;;;;;;;;;;;;;
;; 60 Grad Winkel im Bogenmaß ausgedrückt
;; Die Funktionen sin und cos erwarten einen Winkel im Bogenmaß
(define 60° 1.0472)

(define (draw-koch-snowflake g depth x y length angle)
  (cond [(= depth 0) (draw-line g x y (+ x (* length (cos angle))) (- y (* length (sin angle))))]
        [else (local ((define new-length (/ length 3.0))
                      (define x1 (+ x  (* new-length (cos angle))))
                      (define y1 (- y  (* new-length (sin angle))))
                      (define x2 (+ x1 (* new-length (cos (+ angle 60°)))))
                      (define y2 (- y1 (* new-length (sin (+ angle 60°)))))
                      (define x3 (+ x2 (* new-length (cos (- angle 60°)))))
                      (define y3 (- y2 (* new-length (sin (- angle 60°))))))
                (begin (draw-koch-snowflake g (- depth 1) x  y  new-length angle)
                       (draw-koch-snowflake g (- depth 1) x1 y1 new-length (+ angle 60°))
                       (draw-koch-snowflake g (- depth 1) x2 y2 new-length (- angle 60°))
                       (draw-koch-snowflake g (- depth 1) x3 y3 new-length angle)))]))
;;;;;;;;;;;;;;;;;



(define (draw-fractal g width height)
  (draw-koch-snowflake g DEPTH (/ width 4) (/ (+ height INITIAL-LENGTH) 2) INITIAL-LENGTH 0))

(define frame (new frame%
                 [label "Koch Snowflake"]
                 [width 800]
                 [height 600]))

(define canvas (new canvas%
                  [parent frame]
                  [paint-callback (lambda (canvas g)
                                    (draw-fractal g (send canvas get-width) (send canvas get-height)))]))

(send frame show #t)
