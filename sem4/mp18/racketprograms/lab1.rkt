#lang racket

;; zad 4
{define (sum-bigger-squares-leq x y z)
  {define (sum-squares x y) (+ (* x x) (* y y))}
  {define (leq x y) (or (= x y) (< x y))}
  (cond ; minimum
    [(and (leq z x) (leq z y)) (sum-squares x y)]
    [(and (leq x y) (leq x z)) (sum-squares y z)]
    [(and (leq y x) (leq y z)) (sum-squares x z)]
    )
  }
{define (sum-bigger-squares x y z)
  {define (sum-squares x y)
    (+ (* x x) (* y y))}
  {define min-x-y-z (min x y z)}
  (cond ; minimum
    [(= min-x-y-z z) (sum-squares x y)]
    [(= min-x-y-z x) (sum-squares y z)]
    [else (sum-squares x z)]
)}

;; zad 8
(define (power-close-to b n)
  (define (power-iter e)
    (cond
      [(< (expt b e) n) (power-iter (+ e 1))]
      [else e]))
  (power-iter 0))

;; zad pracownia
{define (square x) (* x x)}
{define (cube x) (* x x x)}
{define (good-enough? x approx-x eps) (< (abs (- x (cube approx-x))) eps)}
{define (approximate x approx-x)
  (/ (+ (/ x (square approx-x)) (* 2 approx-x)) 3)}
{define (cube-root-iter x approx-x)
  (if (good-enough? x approx-x 0.001)
      approx-x
      (cube-root-iter x (approximate x approx-x)))}
{define (cube-root x)
  (cube-root-iter x 1.0)}