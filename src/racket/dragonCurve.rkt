;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname dragonCurve) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Alle 4 Fkt. implementieren lassen!

;; natural -> natural
;; Rekursiv implementieren lassen in Java!!!
(define (power a b)
  (cond
    [(= b 0) 1]
    [(= b 1) a]
    [else (* a (power a (- b 1)))]))


;;; keyword append war nicht in der VL dran, deswegen definieren wir es selbst (mit anderem Namen)
;; Das hier iterativ in Java implementieren lassen!!!! Zwingend iterativ!!!
(define (concatenate lst1 lst2)
  (if (empty? lst1) lst2
      (cons (first lst1) (concatenate (rest lst1) lst2))))


;; define contract s.t. idx has to be a valid index
;; Geht in Java mit einer Zeile. Aber wir sagen da, dass sie neues Array zurückgeben sollen wegen reference
(define (replace-at-idx lst idx elem)
  (cond
    [(= idx 0) (cons elem (rest lst))]
    [else (cons (first lst) (replace-at-idx (rest lst) (- idx 1) elem))]))


;; Obviously rekursiv implementieren lassen!!
(define (dragon order)
  (if (= order 1)
      (list 'R)
      (concatenate (concatenate (dragon (- order 1)) (list 'R)) (replace-at-idx (dragon (- order 1)) (- (power 2 (- order 2)) 1) 'L))))



;;;;;;;;;;;;;;;;;;;
(dragon 3)
(length (dragon 4))

(append-lists '(1 2 3) '(4 5 6))

(power 2 6)
