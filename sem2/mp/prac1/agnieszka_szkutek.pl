:- module(agnieszka_szkutek, [solve/2]).
:- op(200, fx, ~).
:- op(500, xfy, v).


solve(Clauses, Sol):-
	rozdziel(Clauses,Clauses2),
	sort(Clauses2,Cn),
	solve(Cn,[],Sol).

solve([],Solution,Solution).
solve([C1|Cr],Sol,Solution):-
	select(L,C1,_),
	ustal_wart(L,Sol1,NL),
	redukuj_klauzule(L,NL,[C1|Cr],W),
	solve(W,[Sol1|Sol],Solution).

redukuj_klauzule(_,_,[],[]).
redukuj_klauzule(L,NL,C,W):-% L prawdziwe, NL nieprawdziwe
	usun_spelnione(L,C,W1),
	usun_falsze(NL,W1,W).

usun_spelnione(_,[],[]):-!.
usun_spelnione(L,[C1|Cr],W):-
	member(L,C1),!,
	usun_spelnione(L,Cr,W).
usun_spelnione(L,[C1|Cr],[C1|W]):-
	usun_spelnione(L,Cr,W).

usun_falsze(_,[],[]):-!.
usun_falsze(N,[C1|Cr],[C2|W]):-
	select(N,C1,C2),!,
	usun_falsze(N,Cr,W).
usun_falsze(N,[C1|Cr],[C1|W]):-
	usun_falsze(N,Cr,W).


ustal_wart(L,(L,t),~L):-atom(L),!.
ustal_wart(~L,(L,f),L):-atom(L).

bez_negacji(L,L):-atom(L).
bez_negacji(~L,L):-atom(L).


rozdziel1(L,[L]):-atom(L),!.
rozdziel1(~L,[~L]):-atom(L).
rozdziel1(L v C, [L|W]):-
	rozdziel1(C,W).

rozdziel([],[]).
rozdziel([C|T],[C3|W]):-
	rozdziel1(C,C2),
	sort(C2,C3),
	rozdziel(T,W).
