perm([],[]).

perm(L,[K|T]):-
	select(K,L,W),
	perm(W,T).
