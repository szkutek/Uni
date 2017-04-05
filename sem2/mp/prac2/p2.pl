:- op(200, fx, ~).
:- op(500, xfy, v).
resolve(q, p v q, ~q v r, r v p).


% resolve(+Var, +Clause1, +Clause2, -Resolvent)
% zmienna Var ma pozytywne wystąpienie w klauzuli Clause1 oraz negatywne wystąpienie w klauzuli Clause2

resolve(V, C1, C2, Result):-
    rozdziel1(C1,L1),
    rozdziel1(C2,L2),
    rozw(V, L1,L2,Res),
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

% flat(D,L):-
%     flat(D,L,[]).
% flat(leaf,Y,Y).
% flat(node(L,X,R),Y,A):-
%     flat(R,R1,A),
%     flat(L,Y,[X|R1]).
