fib :: Integer -> Integer

fib n = fibs n 1 0 
fibs 0 a _ = a 
 
fibs n a b = fibs (n-1) (a+b) a

