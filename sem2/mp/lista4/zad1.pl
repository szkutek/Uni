len(L,S):-
	len(L,0,S).

len([],A,A).

len([_|T],A,S):-
	A \== S,
	A1 is A+1,
	len(T,A1,S).
