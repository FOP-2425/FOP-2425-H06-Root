;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname bubbleSort) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Function to perform a single pass of bubble sort
(define (bubble-pass lst)
  (cond
    [(empty? lst) '()]
    [(empty? (rest lst)) lst]
    [(> (first lst) (first (rest lst)))
     (cons (first (rest lst)) (bubble-pass (cons (first lst) (rest (rest lst)))))]
    [else
     (cons (first lst) (bubble-pass (rest lst)))]))

;; Function to perform bubble sort
(define (bubble-sort lst)
  (local ((define (bubble-sort-helper lst n)
    (if (<= n 1)
        lst
        (bubble-sort-helper (bubble-pass lst) (- n 1)))))
  (bubble-sort-helper lst (length lst))))

;; Example usage
(bubble-sort (list 5 3 8 4 2 7 1 6)) ; Returns (list 1 2 3 4 5 6 7 8)
