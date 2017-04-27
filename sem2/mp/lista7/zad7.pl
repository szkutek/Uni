:-use_module(library(arithmetic)).

!(X,_):-X<0,fail.
!(0,1):-!.
!(X,Y):-
    X1 is X-1,
    !(X1,Y1),
    Y is X * Y1.

:-arithmetic_function(!/1).
:-op(150, yf, !).



'!!'(0,1):-!.
'!!'(1,1):-!.
'!!'(X,Y):-
    0 is X mod 2,!,
    X1 is X-1,
    '!!'(X1,Y).
'!!'(X,Y):-
    X1 is X-2,
    '!!'(X1,Y1),
    Y is X * Y1.

:-arithmetic_function('!!'/1).
:-op(150, yf, '!!').
