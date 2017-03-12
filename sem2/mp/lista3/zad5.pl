% insert(+NumList, +Elem, ?Res)

insert([],K,[K]).

insert([H|T],K,[K,H|T]):-
	H >= K,
	!.

insert([H|T],K,[H|Res]):-
	H < K,
	insert(T,K,Res).
	

% ins_sort(L,?SortedL)

ins_sort([],[]).

ins_sort([H|T],Res):-
	ins_sort(T,S),
	!,
	insert(S,H,Res).
