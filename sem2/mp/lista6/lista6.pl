%1.
%a. stos

% put(+E,+S,-R)
put(E,S,[E|S]).
% get(+S,-E,-R)
get([E|R],E,R).
empty([]).
% addall(-E, +G, +S, -R)
addall(E,G,S,R):-
    findall(E,G,L),
    putall(L,S,R).
putall([],S,S).
putall([H|T],S,Res):-
    put(H,S,NewS),
    putall(S,NewS,Res).

%b. lista różnicowa
put2(E,S-[E|T],S-T).

get2([E|R]-T, E, R-T).

empty2([H]-H).

% 3.
 % insert/3
 % find/2
 % findMax/2
 % delMax/2
% delete/3
 % empty/1
emptyT(leaf).

insert(Elem,leaf,node(leaf,Elem,leaf)).
insert(Elem,node(L,X,R),node(L,X,R2)):-
    Elem > X,!,
    insert(Elem,R,R2).
insert(Elem,node(L,X,R),node(L2,X,R)):-
    Elem < X,
    insert(Elem,L,L2).

find(Elem, node(_,Elem,_)):-!.
find(Elem, node(L,X,_)):-
    Elem < X,!,
    find(Elem,L).
find(Elem, node(_,X,R)):-
    Elem > X,
    find(Elem,R).

findMax(node(_,X,leaf),X):-!.
findMax(node(_,X,R),Max):-
    findMax(R,Max).

deleteMax(node(leaf,X,leaf),leaf,X):-!.
deleteMax(node(L,X,leaf),L,X):-!.
deleteMax(node(L,X,R),node(L,X,R2),Max):-
    deleteMax(R,R2,Max).

deleteMin(node(leaf,X,leaf),leaf,X):-!.
deleteMin(node(leaf,X,R),R,X):-!.
deleteMin(node(L,X,R),node(L2,X,R),Min):-
    deleteMin(L,L2,Min).

deleteT(Elem,node(leaf,Elem,leaf),leaf).

deleteT(Elem,node(L,Elem,R),node(L,MaxR,R2)):-
    deleteMax(R,R2,MaxR),!.

deleteT(Elem,node(L,X,R),node(L2,X,R)):-
    Elem < X,!,
    deleteT(Elem,L,L2).
deleteT(Elem,node(L,X,R),node(L,X,R2)):-
    Elem > X,
    deleteT(Elem,R,R2).
