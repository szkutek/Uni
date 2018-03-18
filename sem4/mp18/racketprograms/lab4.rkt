#lang racket

;;; ZADANIE 1

(define (concatMap f xs)
  (if (null? xs)
      null
      (append (f (car xs)) (concatMap f (cdr xs)))))

(define (from-to s e)
  (if (= s e)
      (list s)
      (cons s (from-to (+ s 1) e))))

(define (queens board-size)
  ;; Return the representation of a board with 0 queens inserted
  (define (empty-board)
    (list)
    )
  ;; Return the representation of a board with a new queen at
  ;; (row, col) added to the partial representation `rest'
  (define (adjoin-position row col rest)
    (cons (cons row col) rest)
    )
  ;; Return true if the queen in k-th column does not attack any of
  ;; the others
  (define (safe? k positions)
    (let ([x (caar positions)]
          [y (cdar positions)])
      (define (rec pos)
        (if (empty? pos)
            true
            (if (or (= x (caar pos)) (= y (cdar pos))
                    (= (abs (- x (caar pos))) (abs (- y (cdar pos)))))
                false
                (rec (cdr pos)))
            )
        )
      (rec (cdr positions))
      )
    )
    
  ;; Return a list of all possible solutions for k first columns
  (define (queen-cols k)
    (if (= k 0)
        (list (empty-board))
        (filter
         (lambda (positions) (safe? k positions))
         (concatMap
          (lambda (rest-of-queens)
            (map (lambda (new-row)
                   (adjoin-position new-row k rest-of-queens))
                 (from-to 1 board-size))
            )
          (queen-cols (- k 1))))))
  (queen-cols board-size))


;;; ZADANIE 3

{define (btree? t)
  (or (eq? t 'leaf)
      (and (list? t)
           (= 4 (length t))
           (eq? (car t) 'node)
           (btree? (caddr t))
           (btree? (cadddr t))))}

{define (mirror t)
  (if (eq? t 'leaf)
      'leaf
      (list 'node (cadr t) (mirror (cadddr t)) (mirror (caddr t)))
      )}

;;; ZADANIE 4

{define (flatten-zachowaj-strukture t)
  (if (eq? t 'leaf)
      'leaf
      (list (flatten (caddr t)) (cadr t) (flatten (cadddr t)))
      )}

{define (flatten t)
  (if (eq? t 'leaf)
      '()
      (append (flatten (caddr t)) (cons (cadr t) (flatten (cadddr t))))
      )}