head(H,[H|_]).


last([X],X).

last([_|T],H):-
	last(T,H).


tail(T,[_|T]).


init([_],[]).

init([H|T],[H|P]):-
	init(T,P).
	

prefix([],_).

prefix([H|P],[H|T]):-
	prefix(P,T).
	


suffix(X,X).

suffix([_|P],T):-
	suffix(P,T).
