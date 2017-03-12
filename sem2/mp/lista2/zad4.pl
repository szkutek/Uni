even([]).

even([_,_|X]):-
	even(X).

palindrom(X):-
	reverse(X,X).
	
singleton([_]).
