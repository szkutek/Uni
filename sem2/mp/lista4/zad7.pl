revall(L,R):-
	revall(L,[],R).

revall([],R,R).

revall([[H1|T1]|T2],A,R):-
	!,
	revall([H1|T1],H2),
	revall(T2,[H2|A],R).

revall([H|T],A,R):-
	revall(T,[H|A],R).
