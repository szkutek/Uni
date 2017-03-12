sublist(_,[]).

sublist([H|X],[H|Y]):-
	sublist(X,Y).

sublist([_|X],Y):-
	sublist(X,Y).
