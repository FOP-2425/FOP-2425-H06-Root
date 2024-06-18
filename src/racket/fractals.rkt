;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname dragonCurve) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;;#lang racket

;; Alle 4 Fkt. implementieren lassen!
;; Rekursiv implementieren lassen in Java!!!

;; Type: natural natural -> natural
;; Returns: a raised to the power of b, mathematically speaking a^b
(define (pow a b)
  (if (= b 0) 1
    (* a (pow a (- b 1)))))


;;; keyword append war nicht in der VL dran, deswegen definieren wir es selbst (mit anderem Namen)
;; Das hier iterativ in Java implementieren lassen!!!! Zwingend iterativ!!!

;; Type: (list of any) (list of any) -> (list of any)
;; Returns: A new list that contains all and only the elements of lst1 and lst2 in their respective order
(define (concatenate lst1 lst2)
  (if (empty? lst1) lst2
      (cons (first lst1) (concatenate (rest lst1) lst2))))


;; define contract s.t. idx has to be a valid index
;; Geht in Java mit einer Zeile. Aber wir sagen da, dass sie neues Array zurückgeben sollen wegen reference

;; Type: (list of any) natural any -> (list of any)
;; Returns: A new list with the same contents as lst, but with the element at index idx replaced with elem
(define (replace-at-idx lst idx elem)
  (if (= idx 0) (cons elem (rest lst))
    (cons (first lst) (replace-at-idx (rest lst) (- idx 1) elem))))


;; Obviously rekursiv implementieren lassen!!

;; Type: natural -> (list of symbol)
;; Returns: A list containing the drawing instructions for a dragon curve of order n
(define (dragon n)
  (cond
    [(<= n 0) (list 'D)]
    [(= n 1) (list 'D 'R 'D)]
    [else (concatenate (concatenate (dragon (- n 1)) (list 'R)) (replace-at-idx (dragon (- n 1)) (- (pow 2 (- n 1)) 1) 'L))]))



;; Koch Fractal
;; symbol=? Kam schon in VL 04a dran!!
;; Lass die das komplett allein übersetzen, keine Vorgabe dass es genau so sein soll wie hier.

;; Type: (list of symbol) symbol (list of symbol) -> (list of symbol)
;; Returns: A list where every occurence of the symbol elem in list lst1 is replaced by the list lst2
(define (replace-with-list lst1 elem lst2)
  (cond
    [(empty? lst1) empty]
    [(symbol=? (first lst1) elem) (concatenate lst2 (replace-with-list (rest lst1) elem lst2))]
    [else (cons (first lst1) (replace-with-list (rest lst1) elem lst2))]))

;; Type: natural -> (list of symbol)
;; Returns: A list containing the drawing instructions for a koch snowflake of order n
(define (koch-snowflake n)
  (if (<= n 0) (list 'D 'R 'R 'D 'R 'R 'D)
    (replace-with-list (koch-snowflake (- n 1)) 'D (list 'D 'L 'D 'R 'R 'D 'L 'D))))



;;;;;;;; TESTS ;;;;;;;;;;;
(check-expect (dragon 0) (list 'D))
(check-expect (dragon 1) (list 'D 'R 'D))
(check-expect (dragon 2) (list 'D 'R 'D 'R 'D 'L 'D))
(check-expect (dragon 3) (list 'D 'R 'D 'R 'D 'L 'D 'R 'D 'R 'D 'L 'D 'L 'D))
(check-expect (dragon 4) (list 'D 'R 'D 'R 'D 'L 'D 'R 'D 'R 'D 'L 'D 'L 'D 'R 'D 'R 'D 'R 'D 'L 'D 'L 'D 'R 'D 'L 'D 'L 'D))

(check-expect (koch-snowflake 0) (list 'D 'R 'R 'D 'R 'R 'D))
(check-expect (koch-snowflake 1) (list 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D))
(check-expect (koch-snowflake 2) (list 'D 'L 'D 'R 'R 'D 'L 'D 'L 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'L 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'L 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'L 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'L 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'R 'R 'D 'L 'D 'L 'D 'L 'D 'R 'R 'D 'L 'D))

(check-expect (concatenate '(1 2 3) '(4 5 6)) '(1 2 3 4 5 6))

(check-expect (pow 2 0) 1)
(check-expect (pow 2 1) 2)
(check-expect (pow 2 2) 4)
(check-expect (pow 2 3) 8)
(check-expect (pow 0 5) 0)


;; 0 ist Teil von natural
