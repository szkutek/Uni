#lang racket

;; zad 2
{define (compose f g)
  (lambda (x)
    (f (g x)))}

{define (compose2 f g)
  {define (cmp x) (f (g x))}
  cmp}


{define (sq x) (* x x)}
{define (inc x) (+ x 1)}

;; zad 3
{define (id x) x}

{define (repeated p n)
  (if (< n 1)
      id
      [compose p (repeated p (- n 1))])}

;; zad 4
{define (prod term next a b)
  (if (> a b)
      1
      (* (term a)
         (prod term next (next a) b)))}

{define (prod-iter term next a b)
  {define (iter x res)
    (if (> x b)
      res
      [iter (next x) (* (term x) res)])}
  (iter a 1)}

{define (pi end)
  {define (term x) (/ (* (- x 1) (+ x 1)) (* x x))}
  {define (next x) (+ x 2)}
  (* 4 (prod term next 3.0 end))}

;; zad 5
{define (accumulate combiner null-value term a next b)
  (if (> a b)
      null-value
      (combiner (term a)
                (accumulate combiner null-value term (next a) next b)))}

{define (accumulate-iter combiner null-value term a next b)
  {define (iter x res)
    (if (> x b)
      res
      [iter (next x) (combiner (term x) res)])}
  (iter a null-value)}


{define (sum-acc term next a b)
  (accumulate + 0 term a next b)}
{define (prod-acc term next a b)
  (accumulate * 1 term a next b)}

;; zad 6
{define (cont-frac num den k)
  {define (cont-frac-tmp i)
    (if (> i k)
        0.0
        (/ (num i)
           (+ (den i)
              (cont-frac-tmp (+ i 1)))))}
  (cont-frac-tmp 1)
}

{define (cont-frac-iter num den k)
  {define (iter i res)
    (if (< i 1)
        res
        [iter (- i 1)
              (/ (num i) (+ (den i) res))])}
  (iter k 0.0)
}

{define phi ( cont-frac ( lambda ( i ) 1.0) ( lambda ( i ) 1.0) 100 )}

;; zad 7
{define (pi-zad7 end)
  (+ 3.0
     (cont-frac
      (lambda (i) (sq (- (* 2 i) 1)))
      (lambda (i) 6.0)
      end))}
(pi-zad7 20)

;; zad 8
{define (atan-cf x k)
  (/ x (+ 1.0
          (cont-frac
           (lambda (i) (sq (* i x)))
           (lambda (i) (+ (* 2 i) 1))
           k)))}

(atan 10)
(atan-cf 10 100)

;; zad 9
{define (build n d b)
  (/ n (+ d b))}

{define (repeated-build x n d b)
  (repeated (lambda (b) (build n d b)) x)}