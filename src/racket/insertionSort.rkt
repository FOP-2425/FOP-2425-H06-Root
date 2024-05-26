;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname insertionSort) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Function to insert an element into a sorted list
(define (insert elem sorted-list)
  (cond
    [(empty? sorted-list) (list elem)]
    [(<= elem (first sorted-list)) (cons elem sorted-list)]
    [else (cons (first sorted-list) (insert elem (rest sorted-list)))]))

;; Function to perform insertion sort
(define (insertion-sort lst)
  (local ((define (sort-helper unsorted sorted)
    (if (empty? unsorted)
        sorted
        (sort-helper (rest unsorted) (insert (first unsorted) sorted)))))
  (sort-helper lst '())))

;; Example usage
(insertion-sort (list 5 3 8 4 2 7 1 6)) ; Returns (list 1 2 3 4 5 6 7 8)
