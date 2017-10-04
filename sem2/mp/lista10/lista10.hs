 {-# LANGUAGE FlexibleInstances #-} 
-- ZAD 1
nat2 :: [(Integer, Integer)]
nat2 = [(x,y-x) | y<-[0..], x<-[0..y]]


-- ZAD 2
halve :: [a] -> ([a],[a])
halve xs = (take h xs, drop h xs)
  where h = (length xs) `div` 2

halve2 xs = halve' [] xs xs
halve' xs (y:ys) (a0:a1:as) = 
  halve' (y:xs) ys as
halve' xs ys [_] = (xs, ys)
halve' xs ys [] = (xs, ys)

merge2 :: Ord a => ([a],[a]) -> [a]
merge2 (xs, []) = xs
merge2 ([], xs) = xs
merge2 (x:xs, y:ys)
  | x<=y = x : (merge2 (xs, y:ys))
  | otherwise = y : (merge2 (x:xs, ys))


msort [] = []
msort [x] = [x]
msort xs =
  merge2 . cross (msort,msort) . halve $ xs

cross :: (a -> c, b -> d) -> (a,b) -> (c,d)
cross (f,g) = pair (f . fst, g . snd)
pair :: (a -> b, a -> c) -> a -> (b,c)
pair (f,g) x = (f x, g x)


-- ZAD 3
d235 :: [Integer]
d235 = 1 : (merge_unique (merge_unique n2 n3) n5 )
  where n2 = [2,4..]
        n3 = [3,6..]
        n5 = [5,10..]

merge_unique :: Ord a => [a] -> [a] -> [a]
merge_unique xs [] = xs
merge_unique [] xs = xs
merge_unique (x1:x2:xs) (y1:y2:ys)
  | x1==x2 = merge_unique (x2:xs) (y1:y2:ys)
  | y1==y2 = merge_unique (x1:x2:xs) (y2:ys)
  | x1<y1 = x1 : (merge_unique (x2:xs) (y1:y2:ys))
  | x1>y1 = y1 : (merge_unique (x1:x2:xs) (y2:ys))
  | otherwise = x1 : (merge_unique (x2:xs) (y2:ys))

-- merge_unique :: Ord a => [a] -> [a] -> [a]
-- merge_unique xs [] = xs
-- merge_unique [] xs = xs
-- merge_unique (x:xs) (y:ys)
--   | x<y = x : (merge_unique xs (y:ys))
--   | x>y = y : (merge_unique (x:xs) ys)
--   | otherwise = x : (merge_unique xs ys)

 
-- ZAD 4
msortn :: Ord a => [a] -> Int -> [a]
msortn xs 0 = []
msortn (x:xs) 1 = [x]
--msortn [] _ = []
msortn xs n = merge2 ( (msortn xs k), (msortn (drop k xs) (n-k)) )
  where k = n `div` 2
  
-- ZAD 6
class Monoid2 a where
  (***) :: a -> a -> a
  e :: a
infixl 6 ***
infixr 7 ^^^
(^^^) :: Monoid2 a => a -> Integer -> a
a ^^^ 0 = e
a ^^^ n = a *** a ^^^ (n-1)

data Mtx2x2 a = Mtx2x2 a a a a deriving Show
instance Monoid2 (Mtx2x2 Integer) where
  -- (***) (Mtx2x2 a1 a2 a3 a4) (Mtx2x2 b1 b2 b3 b4) = 
  Mtx2x2 a1 a2 a3 a4 *** Mtx2x2 b1 b2 b3 b4 = 
    Mtx2x2 (a1*b1+a2*b3) (a1*b2+a2*b4) (a3*b1+a4*b3) (a3*b2+a4*b4)
  e = Mtx2x2 1 0 0 1

fib2 :: Integer -> Integer
fib2 0 = 1
fib2 n = m where
  Mtx2x2 _ _ _ m = Mtx2x2 0 1 1 1 ^^^ (n-1)


