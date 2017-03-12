ptak(p).
dzdzownica(d).
ryba(r).

kot(my_cat).
lubi(X,Y):-
	ptak(X),
	dzdzownica(Y).
lubi(X,Y):-
	kot(X),
	ryba(Y).
przyjaciele(X,Y):-
	lubi(X,Y),
	lubi(Y,X).
przyjaciele(my_cat,me).
jada(my_cat,Y):-
	lubi(my_cat,Y).
	
/*
?- jada(my_cat,X).
X = r.
*/
