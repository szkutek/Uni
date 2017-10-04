permi([],[]).
permi([H|T],S) :-
	permi(T,R),
	select(H,S,R).

sublist([],[]).
sublist([H|T],[H|S]) :-
	sublist(T,S).
sublist([_|T],S) :-
	sublist(T,S).
