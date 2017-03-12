mily(X):- czlowiek(X),odwiedza(X).

styka_sie(X,Y):-
	zwierze(X),
	mieszka(X),
	czlowiek(Y), % mily(Y)
	odwiedza(Y).
	
nieszczesliwe(X):-
	smok(X),
	mieszka(X).
	
szczesliwe(X):-
	zwierze(X),
	mily(Y),
	styka_sie(X,Y).

smok(X):- zwierze(X).
