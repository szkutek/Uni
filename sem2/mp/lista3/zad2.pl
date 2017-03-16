% filter(+LNum, ?LPos)
filter([],[]).

filter([H|T], [H|S]):-
	H>=0,!,	
	filter(T,S).

filter([H|T],P):-
	H<0,
	filter(T,P).


% count(+Elem, +List, ?Count)
count(_,[],0).

count(X,[X|S],N):-
	count(X,S,K),
	!,
	N is K+1.

count(X,[_|S],N):-
	count(X,S,N).


% exp(+Base, +Exp, ?Res)

exp1(B,E,R):-
	R is B^E.


exp2(_,0,1):-!.

exp2(X,Y,R):-
	Y2 is Y-1,	
	exp2(X,Y2,R2),
	R is R2 * X.

