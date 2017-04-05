connection(wroclaw,warszawa).
connection(wroclaw,krakow).
connection(wroclaw,szczecin).
connection(szczecin,lublin).
connection(szczecin,gniezno).
connection(warszawa,katowice).
connection(gniezno,gliwice).
connection(lublin,gliwice).
connection(gliwice,wroclaw).

trip(X,Y,T):-
	trip(X,Y,T,[Y]).

trip(X,X,A,A).

trip(X,Y,T,A):-
	connection(Z,Y),
	\+ member(Z,A),
	trip(X,Z,T,[Z|A]).
