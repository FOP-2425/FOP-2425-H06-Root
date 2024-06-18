;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname dragonCurve) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;;#lang racket

;; Alle 4 Fkt. implementieren lassen!
;; Rekursiv implementieren lassen in Java!!!

;; Type: natural natural -> natural
;; Returns: a raised to the power of b, mathematically speaking a^b
(define (pow a b)
  (cond
    [(= b 0) 1]
    [else (* a (pow a (- b 1)))]))


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
  (cond
    [(= idx 0) (cons elem (rest lst))]
    [else (cons (first lst) (replace-at-idx (rest lst) (- idx 1) elem))]))


;; Obviously rekursiv implementieren lassen!!

;; Type: integer -> (list of string)
;; Returns: A list containing the drawing instructions for a dragon curve of order n
(define (dragon n)
  (cond
    [(<= n 0) '()]
    [(= n 1) (list "R")]
    [else (concatenate (concatenate (dragon (- n 1)) (list "R")) (replace-at-idx (dragon (- n 1)) (- (pow 2 (- n 2)) 1) "L"))]))



;;;;;;;;;;;;;;;;;;;
(check-expect (dragon 0) '())
(check-expect (dragon 1) '("R"))
(check-expect (dragon 2) '("R" "R" "L"))
(check-expect (dragon 3) '("R" "R" "L" "R" "R" "L" "L"))
(check-expect (dragon 4) '("R" "R" "L" "R" "R" "L" "L" "R" "R" "R" "L" "L" "R" "L" "L"))

(check-expect (concatenate '(1 2 3) '(4 5 6)) '(1 2 3 4 5 6))

(check-expect (pow 2 0) 1)
(check-expect (pow 2 1) 2)
(check-expect (pow 2 2) 4)
(check-expect (pow 2 3) 8)
(check-expect (pow 0 5) 0)


;; 0 ist Teil von natural
;;(natural? 0)
