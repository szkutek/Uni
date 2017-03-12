% min(+L,?Min) znajduje min z listy L
min([H|T],Min):-
	min(T,H,Min).

min([],Min,Min).

min([H|T],X,Min):-
	H =< X,
	min(T,H,Min),!.

min([H|T],X,Min):-
	H > X,
	min(T,X,Min).


% select_min(+NumList, ?Min, ?Rest)

select_min([H|T],Min,Rest):-
	select_min(T,H,Min,[H|T],Rest).

select_min([],Min,Min,L,Rest):-
	select(Min,L,Rest).

select_min([H|T],X,Min,L,Rest):-
	H =< X,
	select_min(T,H,Min,L,Rest),!.

select_min([H|T],X,Min,L,Rest):-
	H > X,
	select_min(T,X,Min,L,Rest).


% sel_sort(L,?SortedL)

sel_sort([],[]).

sel_sort(L,[Min|S]):-
	select_min(L,Min,Rest),
	sel_sort(Rest,S),
	!.
