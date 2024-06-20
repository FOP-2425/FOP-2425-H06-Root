;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname linearSearch) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Takes a list and a target value and returns the index of the target value in the list.
;; If the target value is not in the list, returns -1.
;; Type: (list integer) integer -> integer
;; Returns: Index of first occurence of target or -1 if target is not in list.
(define (linear-search lst target)
  (local ((define (linear-search-helper lst target index)
    (cond
      [(empty? lst) -1]
      [(= (first lst) target) index]
      [else (linear-search-helper (rest lst) target (+ index 1))])))
  (linear-search-helper lst target 0)))


(check-expect (linear-search '(1 2 3 4 5) 3) 2)
(check-expect (linear-search '(1 2 3 4 5) 6) -1)
(check-expect (linear-search '(1 2 3 4 5 -1) -2) -1)
