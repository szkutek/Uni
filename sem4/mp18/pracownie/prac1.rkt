#lang racket

;; Powinniśmy liczyć błąd względny!

{define (cube-root x)
  {define (square x) (* x x)}
  {define (cube x) (* x x x)}
  {define epsilon 0.000001}

  {define (good-enough? approx-x)
    (<
     (abs (- x (cube approx-x)))
     epsilon)}
  
  {define (approximate y)
    (/ (+ (/ x (square y)) (* 2 y)) 3)}

  {define (cube-root-iter y)
    (cond
      [(good-enough? y) y]
      [else             (cube-root-iter (approximate y))])}

  (cube-root-iter 1.0)}


{define (test x cubed-x)
  {define approx-root (cube-root cubed-x)}
  (printf "różnica między ~v a przybliżoną wartością ~v wynosi ~v\n" x approx-root (abs (- x approx-root)))}


"Przykłady, dla których funkcja cube-root działa poprawnie:"
(test 0.03 0.000027)
(test 0.2 0.008)
(test 1.005 1.015075125)
(test 2 8)
(test 503.3 127491370.937)
(test 50250000000234 126884390626772593875008254467000012812904)

(test -0.03 -0.000027)
(test -0.2 -0.008)
(test -1.005 -1.015075125)
(test -2 -8)
(test -503.3 -127491370.937)
(test -50250000000234 -126884390626772593875008254467000012812904)

"Dla poniższych przykładów funkcja nie działa poprawnie przy zadanym epsilonie. Zadziała poprawnie, gdy zmniejszymy epsilon do 0.000000000001"
(test 0.0003 0.000000000027)
(test -0.0003 -0.000000000027)

