;; Takes a list and a target value and returns the index of the target value in the list.
;; If the target value is not in the list, returns -1.
;; Type: (list Any) Any -> Number
(define (linear-search lst target)
  (local ((define (linear-search-helper lst target index)
    (cond
      [(empty? lst) -1]
      [(equal? (first lst) target) index]
      [else (linear-search-helper (rest lst) target (+ index 1))])))
  (linear-search-helper lst target 0)))


(check-expect (linear-search '(1 2 3 4 5) 3) 2)
(check-expect (linear-search '(1 2 3 4 5) 6) -1)
(check-expect (linear-search '(1 2 3 4 5 -1) -2) -1)
