import Data.List
import Data.Char

-- zad4
fib :: Integer -> Integer
fib 0 = 1
fib 1 = 1
fib n = fib(n-1) + fib(n-2)
-- generuje nieużytki(dużo dodawań, generuje rekurencyjnie fib n-1 n-2)

fibb :: Integer -> Integer
fibb n = fibs!!n
fibs = 1:1:zipWith (+) fibs (tail fibs)

-- zad5
roots :: (Double, Double, Double) -> [Double]

roots a b c =
  if a == 0 then
    if b == 0 then []
    else [-c/b]
  else case compare d 0 of
  LT -> []
  EQ -> [-b/(2*a)]
  GT -> [(-b - sqrt(d) / (2*a)), (-b + sqrt(d) / (2*a))]
  where d = b*b - 4*a*c

  data Roots = No
  | One Double
  | Two (Double, Double)
  deriving Show

rootsn :: (Double, Double, Double) -> Roots

rootsn a b c =
  if a == 0 then
    if b == 0 then No
    else One (-c/b)
  else case compare d 0 of
  LT -> No
  EQ -> One (-b/(2*a))
  GT -> Two ((-b - sqrt(d) / (2*a)), (-b + sqrt(d) / (2*a)))
  where d = b*b - 4*a*c

-- myślę, że jest lepsza, ponieważ daje jaśniejszy obraz niż poprzednia: oprócz rozwiązań jest jasno zaznaczone ile ich jest
-- roots :: Double -> Double -> Double -> [Double]  "->" wiąże w prawo, więc funkcja
-- roots :: [Double] -> [Double] ta funkcja dostaje jako argument tablicę liczb Double

-- zad6
integerToString :: Integer -> String
integerToString 0 = "0" -- jak tego nie będzie to się zapętli bez Nothing Just
integerToString n =
	(reverse.unfoldr (\n ->
		if n == 0 then Nothing
		else Just((intToDigit.fromEnum)(n `mod` 10),n `div` 10))) n
-- from enum daje konwertuje argument na inta (tutaj to będzie konwersja wyniku n mod 10 na Int który zostanie przekazany do intToDigit)

-- zad7
newtype FSet a = FSet(a -> Bool)

empty :: FSet a
empty = FSet (\_ -> False)

singleton :: Ord a => a -> FSet a
singleton x = FSet (\y -> x == y)
--singleton n = FSet (\x -> x == n)

fromList :: Ord a => [a] -> FSet a
fromList xs = FSet(\x -> x `elem` xs)

union :: Ord a => FSet a -> FSet a -> FSet a
union (FSet a) (FSet b) = FSet(\x -> a x || b x)

intersection :: Ord a => FSet a -> FSet a -> FSet a
intersection (FSet a) (FSet b) = FSet (\x -> a x && b x)

member :: Ord a => a -> FSet a -> Bool
member x (FSet a) = a x


dodaj :: FSet a -> a -> FSet a
dodaj (FSet f) x = FSet (\n ->  f n || n==x)
