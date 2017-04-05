% Definiujemy moduł zawierający rozwiązanie.
% Należy zmienić nazwę modułu na {imie}_{nazwisko} gdzie za
% {imie} i {nazwisko} należy podstawić odpowiednio swoje imię
% i nazwisko bez wielkich liter oraz znaków diakrytycznych
:- module(agnieszka_szkutek, [resolve/4, prove/2]).

% definiujemy operatory ~/1 oraz v/2
:- op(200, fx, ~).
:- op(500, xfy, v).

% Szukanie rezolwenty.

% resolve(+Var, +Clause1, +Clause2, -Resolvent)
% zmienna Var ma pozytywne wystąpienie w klauzuli Clause1 oraz negatywne wystąpienie w klauzuli Clause2

resolve(V, C1, C2, Result):-
    rozdziel1(C1,L1),
    rozdziel1(C2,L2),
    rozw(V, L1,L2,Res),!,
    polacz1(Res,Result).

polacz1([H],H):-!.
polacz1([H|T],H v Res):-
    polacz1(T,Res).

rozw(V, L1, L2, Res):-
    select(V,L1,R1),!,
    select(~V,L2,R2),!,
    append(R1,R2,Res).
    % sort(Res1,Res).

rozdziel1(L,[L]):-atom(L),!.
rozdziel1(~L,[~L]):-atom(L).
rozdziel1(L v C, [L|W]):-
	rozdziel1(C,W).

rozdziel([],[]).
rozdziel([C|T],[C3|W]):-
	rozdziel1(C,C2),
	sort(C2,C3),
	rozdziel(T,W).




res(R1,R2,Res):-
    R1\==[], R2\==[], Res = R1 v R2, !;
    R1\==[], Res = R1, !;
    R2\==[], Res = R2, !.










% Główny predykat rozwiązujący zadanie.
% UWAGA: to nie jest jeszcze rozwiązanie; należy zmienić jego
% definicję.
prove(Clauses, Proof) :-
  Clauses = [p v q v ~r, ~p v q, r v q, ~q, p],
  Proof   = [(p v q v ~r, axiom), (~p v q, axiom), (q v ~r, (p, 1, 2)),
    (r v q, axiom), (q, (r, 4, 3)), (~q, axiom), ([], (q, 5, 6))].
