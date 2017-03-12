sibling(X,Y):-
	parent(Z,X),
	parent(Z,Y).
sister(X,Y):-
	female(X),
	sibling(X,Y).
grandson(X,Y):-
	parent(Y,Z),
	parent(Z,X).
cousin(X,Y):-
	male(X),
	parent(Z,X),
	parent(W,Y),
	sibling(Z,W).
descendant(X,Y):-
	parent(Y,X).
descendant(X,Y):-
	parent(Y,Z),
	descendant(X,Z).
is_mother(X):-
	female(X),
	parent(X,_).
is_father(X):-
	male(X),
	parent(X,_).
	
female(eve).
female(helen).
female(ivonne).
female(anna).
male(adam).
male(john).
male(mark).
male(joshua).
male(david).

parent(adam,helen).
parent(adam,ivonne).
parent(adam,anna).
parent(eve,helen).
parent(eve,ivonne).
parent(eve,anna).
parent(john,joshua).
parent(helen,joshua).
parent(ivonne,david).
parent(mark,david).


/*
?- descendant(john,mark).
false.

?- descendant(X,adam).
X = helen ;
X = ivonne ;
X = anna ;
X = joshua ;
X = david ;
false.

?- sister(X,ivonne).
X = helen ;
X = helen ;
X = ivonne ;
X = ivonne ;
X = anna ;
X = anna.

?- cousin(X,Y).
X = Y, Y = joshua ;
X = Y, Y = joshua ;
X = joshua,
Y = david ;
X = joshua,
Y = david ;
X = david,
Y = joshua ;
X = david,
Y = joshua ;
X = Y, Y = david ;
X = Y, Y = david ;
false.

*/

