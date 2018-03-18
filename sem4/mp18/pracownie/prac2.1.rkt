#lang racket

{define (cont-frac num den eps)
  {define (F num den n val-1 val0)
    {define (iter Fn-2 Fn-1 k)
      (if (> k n)
          Fn-2
          (iter Fn-1
                (+ (* (den n) Fn-1) (* (num n) Fn-2))
                (+ k 1)))}
    (iter val-1 val0 0)}

  {define (A n)
    (F num den n 1 0)}
  {define (B n)
    (F num den n 0 1)}
  
  {define (good-enough? res-k res-k+1)
    (<
     (abs (- res-k res-k+1))
     eps)}

  {define (improve-f k)
    (/ (A k) (B k))}
  
  {define (iter k res)
    (cond
      [(good-enough? res (improve-f k)) (improve-f k)]
      [else                             (iter (+ k 1) (improve-f k))])} 

  (iter 1 0.0)}


"Przybliżona wartość ułamka z zadania 6 (przy zadanej dokładności eps = 0.0001): "
(cont-frac (lambda (i) 1.0) (lambda (i) 1.0) 0.0001)
