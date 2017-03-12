perm([],[]).

perm([H|T],P):-
	perm(T,W),
	select(H,P,W).
