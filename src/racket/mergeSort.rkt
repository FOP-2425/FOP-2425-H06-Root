;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname mergeSort) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Function to merge two sorted lists
(define (merge left right)
  (cond
    [(empty? left) right]
    [(empty? right) left]
    [(<= (first left) (first right))
     (cons (first left) (merge (rest left) right))]
    [else
     (cons (first right) (merge left (rest right)))]))

;; Function to split a list into two halves
(define (split lst)
  (local ((define (helper lst n)
    (if (or (empty? lst) (= n 0))
        (list '() lst)
        (let ([split-result (helper (rest lst) (- n 1))])
          (list (cons (first lst) (first split-result)) (second split-result))))))
  (helper lst (quotient (length lst) 2))))

;; Function to perform merge sort
(define (merge-sort lst)
  (if (or (empty? lst) (empty? (rest lst)))
      lst
      (let ([split-lists (split lst)])
        (merge (merge-sort (first split-lists)) (merge-sort (second split-lists))))))

;; Example usage
(merge-sort (list 5 3 8 10 9 4 2 7 13 12 1 11  6)) ; Returns (list 1 2 3 4 5 6 7 8)
