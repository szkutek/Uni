%dom(Kolor,Wlasciciel,Zwierze,Papieros,Napoj):-

dom(anglik,czerwony).
zwierze(hiszpan,pies).
napoj(X,kawa):-
	dom(X,zielony).
napoj(ukrainiec,herbata).
papieros(X,winstony):-
	zwierze(X,waz).
papieros(X,koole):-
	dom(X,zolty).





sasiad(X,Y):-
	dom(X,zielony),
	dom(Y,bialy).
sasiad(X,Y):-
	dom(Y,zielony),
	dom(X,bialy).
sasiad(
