% node( node(leaf, 1, node(leaf, 2, leaf)) , 3, node(leaf, 5, leaf))

flat1(leaf,[]).

flat1(node(L,X,R), Y):-
	flat1(L, L1),
	flat1(R, R1),
	append(L1, [X|R1], Y).



insert(E,leaf,node(leaf,E,leaf)):-!.

insert(E,node(L,X,P),node(W,X,P)):-
	E =< X,
	insert(E,L,W),
	!.

insert(E,node(L,X,P),node(L,X,W)):-
%	E > X,
	insert(E,P,W).


treesort([],D,D).

treesort([H|T],D,Y):-
	insert(H,D,D2),
	treesort(T,D2,Y).

treesort(L,Y):-
	treesort(L,leaf,D),
	%!,
	flat1(D,Y).
