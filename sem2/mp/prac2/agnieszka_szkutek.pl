:- module(agnieszka_szkutek, [resolve/4, prove/2]).
:- op(200, fx, ~).
:- op(500, xfy, v).

% resolve(+Var, +Clause1, +Clause2, -Resolvent)
% zmienna Var ma pozytywne wystąpienie w klauzuli Clause1 oraz negatywne wystąpienie w klauzuli Clause2

resolve(V, C1, C2, Result):-
    rozdziel1(C1,P1,N1),
    rozdziel1(C2,P2,N2),
    rozw(V, P1,N2,Res),!,
    flatten([Res,N1,P2],ToSort),
    sort(ToSort,Sorted),
    polacz1(Sorted,Result).

polacz1([],[]):-!.
polacz1([H],H):-!.
polacz1([H|T],H v Res):-
    polacz1(T,Res).

rozw(V, L1, L2, Res):-
    select(V,L1,R1),!,
    select(~V,L2,R2),!,
    append(R1,R2,Res).

rozdziel1(L,[L],[]):-atom(L),!.
rozdziel1(~L,[],[~L]):-atom(L).
rozdziel1(L v C, [L|W1], W2):-
    atom(L),!,
	rozdziel1(C,W1,W2).
rozdziel1(~L v C, W1, [~L|W2]):-
    atom(L),
	rozdziel1(C,W1,W2).


%---------------------------------------------------------
%---------------------------------------------------------
% prove(Clauses, Proof) :-
%   Clauses = [p v q v ~r, ~p v q, r v q, ~q, p],
%   Proof   = [(p v q v ~r, axiom), (~p v q, axiom), (q v ~r, (p, 1, 2)), (r v q, axiom), (q, (r, 4, 3)), (~q, axiom), ([], (q, 5, 6))].


prove(Klauzule, Dowod):-
    rozdziel(Klauzule, ListaPar1),
    usun_tautologie(ListaPar1, ListaPar2),
    rezolwuj(ListaPar2, Wynik),
    wypisz_dowod(Wynik, Dowod),
    \+ pusty(Dowod).

pusty([]).

rozdziel([],[]).
rozdziel([C|T],[[PC3,NC3]|W]):-
	rozdziel1(C,PC2,NC2),
	sort(PC2,PC3),
	sort(NC2,NC3),
	rozdziel(T,W).

usun_tautologie([],[]).
usun_tautologie([L1|ListaPar], Result):-
    czy_tautologia(L1),!,
    usun_tautologie(ListaPar, Result).
usun_tautologie([L1|ListaPar], [L1|Result]):-
    usun_tautologie(ListaPar, Result).

czy_tautologia([[L|_],N]):-
    member(~L, N),!.
czy_tautologia([[_|P],N]):-
    czy_tautologia([P,N]).

rezolwuj(Lista, Wynik):-
    rezolwuj(Lista, [], Wynik).
rezolwuj([],Wynik,Wynik).
rezolwuj(Lista, Akum, Wynik):-
    select(L1,Lista ,Lista1),
    select(L2,Lista1,Lista2),
    resolve2(V,L1,L2,Res),
    \+ member(Res, Lista2),
    \+ czy_tautologia(Res),
    dodaj_do_wyniku(Akum, [[L1,axiom],[L2,axiom],[Res,[V,L1,L2]]],X),
    (Res == [ [],[] ],rezolwuj([],X,Wynik) ;
    rezolwuj([Res|Lista2],X,Wynik) ),!.

resolve2(V, [P1,N1], [P2,N2], [SortedP,SortedN]):-
    select(V, P1,NewP1),
    select(~V,N2,NewN2),!,
    append(NewP1,P2,ToSortP),
    append(N1,NewN2,ToSortN),
    sort(ToSortP,SortedP),
    sort(ToSortN,SortedN).

dodaj_do_wyniku(A,[],A).
dodaj_do_wyniku(A,[[H,Orig]|T],W):-
    member([H,_],A),
    dodaj_do_wyniku(A,T,W);
    (
    Orig == axiom,
    append(A,[[H,Orig]],A2)
    ;
    [V,L1,L2] = Orig,
    nth1(N1,A,[L1,_]), nth1(N2,A,[L2,_]),
    Orig2 = [V,N1,N2],
    append(A,[[H,Orig2]],A2)
    ),
    dodaj_do_wyniku(A2,T,W).

wypisz_dowod(Lista,Wynik):-
    wypisz_dowod(Lista,[],Wynik).
wypisz_dowod([],Wynik,Wynik).
wypisz_dowod([[L1,Orig]|Lista],Akum,Wynik):-
    flatten(L1, L2),
    polacz1(L2, Klauzula),
    append(Akum, [(Klauzula,Orig)], Akum2),
    wypisz_dowod(Lista,Akum2,Wynik).
