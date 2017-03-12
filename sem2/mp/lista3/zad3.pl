% factorial(+N, ?M)

factorial(0,1).

factorial(N,M):-
	N>0,
	K is N-1,
	factorial(K,F),
	!,
	M is N*F.

% concat_number(+Digits, ?Num)
	
concat_number(D,X):-
	concat_number(D,0,X).

concat_number([],X,X).

concat_number([H|T],A,X):-
	W is A*10 + H,
	concat_number(T,W,X).


% decimal(+Num, ?Digits)

decimal(X,D):-
	decimal(X,[],D).

decimal(0,D,D).

decimal(X,A,D):-
	Y is X div 10,
	H is X mod 10,
	decimal(Y,[H|A],D),
	!.
