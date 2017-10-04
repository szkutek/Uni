 {-# LANGUAGE FlexibleContexts #-} 

import Control.Monad 
import Data.List 
-- permi([],[]).
-- permi([H|T],S) :-
--   permi(T,R),
--   select(H,S,R).

-- select(H,[H|T],T).
-- select(X,[H|T],[H|S]) :-
--   select(X,T,S).

-- zad 1
-- 1.
permi1 :: [a] -> [[a]]
permi1 [] = [[]]
permi1 (x:xs) = concatMap (insert' x) (permi1 xs)

permi2 :: [a] -> [[a]]
permi2 [] = [[]]
permi2 (x:xs) = [z | ys<-permi2 xs, z <- insert' x ys ]

permi3 :: [a] -> [[a]]
permi3 [] = return []
permi3 (x:xs) = do
		ys <- permi3 xs
		zs <- insert' x ys
		return zs

insert' x [] = [[x]] 
insert' x l@(y:ys) = [x:l] ++ (map (y:) $ insert' x ys)

-- zad 2
perms1 :: Eq a => [a] -> [[a]]
perms1 [] = [[]]
perms1 xs = concatMap (\ x -> map (x:) (perms1 $ delete' x xs ) ) xs

perms2 :: Eq a => [a] -> [[a]]
perms2 [] = [[]]
perms2 xs = [y:ys | y <- xs, ys <- perms2 $ delete' y xs]

perms3 :: Eq a => [a] -> [[a]]
perms3 [] = return []
perms3 xs = do
	y <- xs
	ys <- perms3 $ delete' y xs
	return (y:ys)

delete' y (x:xs) = 
	if y == x 
		then xs 
		else x : (delete' y xs)


-- zad 3
sublist1 :: [a] -> [[a]]
sublist1 [] = [[]]
sublist1 (x:xs) = concatMap f $ sublist1 xs where
	f ys = [x:ys, ys]

sublist2 :: [a] -> [[a]]
sublist2 [] = [[]]
sublist2 (x:xs) = [res | ys <- sublist2 xs, res <- [x:ys, ys]]


sublist3 :: [a] -> [[a]]
sublist3 [] = return []
sublist3 (x:xs) = do
	ys <- sublist3 xs
	[x:ys, ys]
	-- res <- [x:ys, ys]
	-- return res