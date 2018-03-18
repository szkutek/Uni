#lang racket


;; zad 3
{define (reverse l)
  (if (null? l)
      null
      (append (reverse (cdr l)) (list (car l)) )
  )}


{define (reverse-iter l)
  {define (iter l a)
    (if (null? l)
      a
      (iter (cdr l) (cons (car l) a) ) ) }
  (iter l '())}
