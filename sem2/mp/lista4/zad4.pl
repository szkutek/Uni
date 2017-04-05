mirror(leaf,leaf).

mirror(node(L,X,R),node(R1,X,L1)):-
	mirror(R,R1), 
	mirror(L,L1).


flat1(leaf,[]).

flat1(node(L,X,R), Y):-
	flat1(L, L1),
	flat1(R, R1),
	append(L1, [X|R1], Y).


% node( node(leaf, 1, node(leaf, 2, leaf)) , 3, node(leaf, 5, leaf))
