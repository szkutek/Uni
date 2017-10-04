 {-# LANGUAGE FlexibleContexts #-} 
-- # zad 2
ssm :: [Integer] -> [Integer]
ssm = reverse . foldl f [] where
	f [] x = [x]
	f (a:as) x
		| a < x  =  x:a:as
		| otherwise = a:as

-- # zad 5
lengthL :: [a] -> Integer
lengthL = foldl f 0 where 
	f n _ = n+1
-- lengthL = foldl (\ n _ -> n+1) 0


lengthR :: [a] -> Integer
lengthR = foldr (\ _ n -> n+1) 0

(+++) :: [a] -> [a] -> [a]
(+++) = flip $ foldr (:)

concat2 :: [[a]] -> [a]
concat2 = foldr (++) []

reverse2 :: [a] -> [a]
reverse2 = foldl f [] where
	f [] x = x:[]
	f as x = x:as
-- reverse2 = foldl (\ as x -> x:as) []

sum2 :: Num a => [a] -> a
sum2 = foldl (+) 0

-- # zad 7
loop :: a
loop = loop
ones :: [Integer]
ones = 1 : ones

-- # zad 6

f1 x =  map -1 x
f2 x =  map (-1) x
f3 x =  [x] : [1]
f4 x = x * sin . 1

