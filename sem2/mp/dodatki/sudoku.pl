/*  Prosty program rozwiązujący sudoku

  Autor: Marek Piotrów, marzec 2017

  Plansza jest reprezentowana jako lista 9 list 9-elementowych
  (wierszy). W wierszu puste pola są reprezentowane przez zmienne.
  W trakcie analizy informacje o cyfrach występujących w poszczególnych
  wierszach, kolumnach i kwadratach 3x3 przechowywane są w strukturze
  sets. We wstępnej fazie zbierane są na liście informacje o pustych
  polach i budowana jest struktura sets. Lista ta jest używana do
  przypisywania pustym polom wartości (z nawrotami). Po wstępnej fazie
  lista ta jest sortowana ze względu na liczbę wartości, które może
  przyjąć dane puste pole.

*/

initSets(sets(InRows,InCols,InSqrs)) :-
	InRows = s(t([],[],[]),t([],[],[]),t([],[],[])),
	InCols = s(t([],[],[]),t([],[],[]),t([],[],[])),
	InSqrs = s(t([],[],[]),t([],[],[]),t([],[],[])).

getSet(0,s(t(S,_,_),_,_),S).
getSet(1,s(t(_,S,_),_,_),S).
getSet(2,s(t(_,_,S),_,_),S).
getSet(3,s(_,t(S,_,_),_),S).
getSet(4,s(_,t(_,S,_),_),S).
getSet(5,s(_,t(_,_,S),_),S).
getSet(6,s(_,_,t(S,_,_)),S).
getSet(7,s(_,_,t(_,S,_)),S).
getSet(8,s(_,_,t(_,_,S)),S).

getSetsSum(sets(InRows,InCols,InSqrs),RowNr,ColNr,SqrNr,Sum) :-
	getSet(RowNr,InRows,Sr),
	getSet(ColNr,InCols,Sc),
	getSet(SqrNr,InSqrs,Ss),
	append(Sr,Sc,S), append(S,Ss,Sum).

updateSet(0,Val,s(t(S1,S2,S3),SS2,SS3),s(t([Val|S1],S2,S3),SS2,SS3)).
updateSet(1,Val,s(t(S1,S2,S3),SS2,SS3),s(t(S1,[Val|S2],S3),SS2,SS3)).
updateSet(2,Val,s(t(S1,S2,S3),SS2,SS3),s(t(S1,S2,[Val|S3]),SS2,SS3)).
updateSet(3,Val,s(SS1,t(S1,S2,S3),SS3),s(SS1,t([Val|S1],S2,S3),SS3)).
updateSet(4,Val,s(SS1,t(S1,S2,S3),SS3),s(SS1,t(S1,[Val|S2],S3),SS3)).
updateSet(5,Val,s(SS1,t(S1,S2,S3),SS3),s(SS1,t(S1,S2,[Val|S3]),SS3)).
updateSet(6,Val,s(SS1,SS2,t(S1,S2,S3)),s(SS1,SS2,t([Val|S1],S2,S3))).
updateSet(7,Val,s(SS1,SS2,t(S1,S2,S3)),s(SS1,SS2,t(S1,[Val|S2],S3))).
updateSet(8,Val,s(SS1,SS2,t(S1,S2,S3)),s(SS1,SS2,t(S1,S2,[Val|S3]))).

updateSets(Elem,sets(InRows,InCols,InSqrs),RowNr,ColNr,SqrNr,
	   sets(InRows1,InCols1,InSqrs1)) :-
	updateSet(RowNr,Elem,InRows,InRows1),
	updateSet(ColNr,Elem,InCols,InCols1),
	updateSet(SqrNr,Elem,InSqrs,InSqrs1).


sudoku(T) :-
	prepare(T,ToSolve,Used),
        optimize(ToSolve,Used,ToSolveOpt),
	solve(ToSolveOpt,Used,T).

prepare(T,ToS,Usd) :- initSets(Used), prepare(T,0,0,[],Used,ToS,Usd).
prepare(T,RowNr,ColNr,ToSolve,Used,ToS,Usd) :-
  RowNr < 9, !,
  (
    ColNr < 9, !, nth0(RowNr,T,Row), nth0(ColNr,Row,Elem),
       ColNew is ColNr + 1, SqrNr is (RowNr div 3) * 3 + ColNr div 3,
       (
	 var(Elem), !,
	 prepare(T,RowNr,ColNew,[(Elem,RowNr,ColNr,SqrNr)|ToSolve],Used,ToS,Usd);
         between(1,9,Elem), updateSets(Elem,Used,RowNr,ColNr,SqrNr,Used1),
	 prepare(T,RowNr,ColNew,ToSolve,Used1,ToS,Usd)
       );
    RowNew is RowNr + 1, prepare(T,RowNew,0,ToSolve,Used,ToS,Usd)
  );
  ToS = ToSolve, Usd = Used.

solve([],_,T) :- T = [W1,W2,W3,W4,W5,W6,W7,W8,W9], nl,
	write(W1), nl, write(W2), nl, write(W3), nl,
	write(W4), nl, write(W5), nl, write(W6), nl,
	write(W7), nl, write(W8), nl, write(W9), nl.
solve([(_,X,Row,Col,Sqr)|ToSolve],Used,T) :-
	getSetsSum(Used,Row,Col,Sqr,Sum), !,
	between(1,9,X), \+ member(X,Sum), updateSets(X,Used,Row,Col,Sqr,Used1),
	solve(ToSolve,Used1,T).

optimize(ToSolve,Used,ToSolveOpt) :-
        computeSizes(ToSolve,Used,ToSolveWithSizes),
	sort(ToSolveWithSizes,ToSolveOpt).

computeSizes([],_,[]).
computeSizes([(X,R,C,S)|ToS], Used, [(Size,X,R,C,S)|ToSwS]) :-
	getSetsSum(Used,R,C,S,Sum), sort(Sum,Set), length(Set,L),
        Size is 9 - L, computeSizes(ToS,Used,ToSwS).


/************** easy

sudoku(
 [[_,8,_, 9,_,_, 4,_,_], [5,_,6, _,8,_, 7,9,_], [9,4,3, _,6,_, _,8,_],
  [_,9,_, _,_,_, 1,5,8], [6,_,_, 5,2,8, _,_,4], [8,7,5, _,_,_, _,6,_],
  [_,5,_, _,4,_, 6,3,9], [_,6,9,_,7,_,  8,_,1], [_,_,8, _,_,9, _,4,_]]).

*************** medium

sudoku(
 [[_,_,2, 4,5,_, 7,3,_], [8,4,_, _,7,_, _,_,_], [_,9,5, 3,2,_, _,_,_],
  [_,3,8, 7,_,2, _,_,_], [9,_,4, _,_,_, 3,_,7], [_,_,_, 9,_,3, 1,8,_],
  [_,_,_, _,3,6, 5,7,_], [_,_,_,_,8,_,  _,9,4], [_,7,1, _,9,4, 8,_,_]]).

**************** hard

sudoku(
 [[2,5,3, 1,_,4, _,8,_], [9,_,_, _,_,_, _,_,_], [_,_,4, 7,_,_, 9,3,_],
  [8,_,2, _,_,_, _,4,_], [_,3,6, _,_,_, 8,7,_], [_,7,_, _,_,_, 2,_,3],
  [_,4,9, _,_,3, 5,_,_], [_,_,_,_,_,_, _,_,7], [_,2,_, 6,_,7, 4,9,8]]).

**************** very hard

sudoku(
 [[2,_,_, 8,_,1, _,5,_], [_,_,_, _,9,_, 7,_,_], [_,_,5, _,_,2, _,_,8],
  [6,9,_, _,_,4, 5,2,_], [_,4,2, _,_,_, 3,8,_], [_,5,8, 2,_,_, _,4,6],
  [5,_,_, 1,_,_, 8,_,_], [_,_,7, _,2,_, _,_,_], [_,1,_, 9,_,3, _,_,4]]).

sudoku(
 [[_,2,_, _,_,_, _,_,_], [9,_,_, 3,_,_, _,_,_], [5,6,_, 1,9,_, 7,_,8],
  [7,_,_, 2,3,_, _,1,_], [4,_,_, 7,_,8, _,_,9], [_,5,_, _,4,1, _,_,7],
  [8,_,7, _,1,3, _,6,2], [_,_,_, _,_,4, _,_,5], [_,_,_, _,_,_, _,4,_]]).

sudoku(
 [[8,3,_, _,_,_, _,_,_], [_,_,1, _,8,_, 2,_,9], [_,2,4, _,1,7, _,_,_],
  [_,9,2, _,5,3, _,_,6], [_,_,5, _,9,_, 8,_,_], [3,_,_, 4,6,_, 5,9,_],
  [_,_,_, 5,7,_, 3,6,_], [7,_,3, _,4,_, 9,_,_], [_,_,_, _,_,_, _,7,8]]).

*/


