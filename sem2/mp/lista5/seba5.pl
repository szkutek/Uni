% zad 1 - appn/2
% appn([[1,2,3],[4,5],[6,7,8,9]],Y). Y = [1,2,3,4,5,6,7,8,9]
appn(L, L1):-
	appn(L, [L1]-L1).

appn([], _-[]).
appn([[]|T], Acc):-
	appn(T, Acc).
appn([[H|T1]|T], L-End):-
	End = [H|End1],
	appn([T1|T], L-End1).

% zad 2 - flatten/2 użyj is_list(X).
% flattn([1,[2,3,[4,5],6,[7,[8,9]]]],X). X = [1,2,3,4,5,6,7,8,9]
flattn(X, Y):-
	flattn(X, [], Y).
flattn([], A, A).
flattn([H|T], Acc, Res):-
	is_list(H),
	!,
	flattn(T, Acc, Y),
	flattn(H, Y, Res).
flattn([H|T], Acc, [H|Y]):-
	flattn(T, Acc, Y).

% zad 3
halve(List, Left, Right):-
	halve(List, List, Left, Right). % dwa wskaźniki do listy

halve(Right, [], [], Right):-!. % pierwszy argument jest w połowie oryginału
halve(Right, [_], [], Right):-!. % został nam jeden element z końca, doklejamy do prawej
halve([H|T], [_,_|T2], [H|Left], Right):- % przesuwamy się w liście o 1 i w drugiej kopii o 2
	halve(T, T2, Left, Right).

% zad 4
merge([], L, L):-!.
merge(L, [], L):-!.
merge([H1|T1], [H2|T2], [H2|Res]):-
	H1 >= H2,
	merge([H1|T1], T2, Res).
merge([H1|T1], [H2|T2], [H1|Res]):-
	H1 < H2,
	merge(T1, [H2|T2], Res).

merge_sort([],[]):-!.
merge_sort([X],[X]):-!.
merge_sort(L, Res):-
	halve(L, Left, Right),
	merge_sort(Left, NewLeft),
	merge_sort(Right, NewRight),
	merge(NewLeft, NewRight, Res).

%zad 5


% zad 7

split(List, Med, Small, Big):-
	split(List, Med, Small, [], Big, []).
split([], _, Acc1, Acc1, Acc2, Acc2).
split([H|T], Med, Small, Acc1, Big, Acc2):-
	H < Med,
	!,
	split(T, Med, Small, [H|Acc1], Big, Acc2).
split([H|T], Med, Small, Acc1, Big, Acc2):-
	split(T, Med, Small, Acc1, Big, [H|Acc2]).

qsort(L, Res):-
	qsort(L, [], Res).
qsort([], A, A).

qsort([H|T], Acc, Res):-
	split(T, H, Small, Big),
	qsort(Big, Acc, NewAcc),
	qsort(Small,[H|NewAcc], Res).

% zad 8


% zad 9
sum(X, Y, Z):-
	integer(X),
	integer(Y),
	!,
	Z is X + Y.

sum(X, Y, Z):-
	integer(X),
	integer(Z),
	!,
	Y is Z - X.

sum(X, Y, Z):-
	integer(Y),
	integer(Z),
	!,
	X is Z - Y.

sum(X, Y, Z):-
	n(N),
	select(Sign, [-1,1], _),
	select(A, [X,Y], [B]),
	A is N * Sign,
	choose(N, 😎,
	Z is X + Y.

choose(X,X).
choose(X,Y):-
	X is -Y.
choose(X,Y):-
	X2 is X-1,
	X >= 0,
	choose(X2,Y).
n(0).
n(N):-
	n(M),
	N is M + 1.
