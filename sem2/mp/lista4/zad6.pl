% concat_number(+Digits, ?Num)
concat_number(D,X):-
	concat_number(D,0,X).
concat_number([],X,X).
concat_number([H|T],A,X):-
	W is A*10 + H,
	concat_number(T,W,X).

sublist(_,[]).
sublist([H|X],[H|Y]):-
	sublist(X,Y).
sublist([_|X],Y):-
	sublist(X,Y).

%%
% zapytanie([U,S,A],[U,S,S,R],[P,E,A,C,E]).
% Litery = [A,C,E,P,R,S,U]
litery([],_).
litery([H|T],[H|R]):-
    \+ member(H,R),
    litery(T,R).
litery([_|T],R):-
    litery(T,R).


zapytanie([Hx|X1],[Hy|Y1],[Hz|Z1]):-
    % litery([Hx|X1],Litery),
    % litery([Hy|Y1],Litery),
    % litery([Hz|Z1],Litery),
    Cyfry = [0,1,2,3,4,5,6,7,8,9],
    sublist(Cyfry,C1),
    sublist(Cyfry,C2),
    sublist(Cyfry,C3),
    permutation(C1,[Hx|X1]),
    permutation(C2,[Hy|Y1]),
    permutation(C3,[Hz|Z1]),
    Hx \= 0, Hy \= 0, Hz \= 0,
    concat_number(X1,Xn),
    concat_number(Y1,Yn),
    concat_number(Z1,Zn),
    Zn is Xn + Yn,!.
