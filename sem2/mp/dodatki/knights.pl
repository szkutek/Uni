%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                 A solution to the Knight Tour problem                %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% SWI-Prolog

% Board fields are represented in the form of structures
% field(I,J,L,N), where
% (I,J) is a pair of coordinates on the board
% L     is a list of fields reachable in one move
% N     is a pointer to the next node in constructed path

% combineFields(+F,+A) iterates through the list F
% of fields f(I,J,L,N) instantiating L to the list of fields
% found in A which are available in one move from (I,J)
combineFields([],_).                    % all fields have been updated
combineFields([field(I,J,L,_)|T],A) :-
   findFields(A,(I,J),L),
   combineFields(T,A).

% findFields(+A,+C,-L) instantiates L to the list of fields
% from the list A of all fields which are available from position C
findFields([],_,[]).
findFields([F|T],(I,J),[F|G]) :-
   F = field(I1,J1,_,_),
   canMoveTo((I,J),(I1,J1)),
   !,
   findFields(T,(I,J),G).
findFields([_|T],C,F) :-
   findFields(T,C,F).

% canMoveTo(+CurrentPos,?NextPos) checks wheather NextPos is reachable
% from CurrentPos; doesn't check wheather NextPos is inside the board
canMoveTo((I1,J1),(I2,J2)) :-
   move(DI,DJ),
   I2 is I1+DI,
   J2 is J1+DJ.

% move(?DeltaX,?DeltaY) describes 8 possible moves of the knight
move(-2,-1).
move(-2,1).
move(-1,-2).
move(-1,2).
move(1,-2).
move(1,2).
move(2,-1).
move(2,1).

% findPath(+N,+F) traverses the board starting at field F and searches
% for a path of length N
findPath(N,field(_,_,F,L)) :-
   N>0,
   !,
   member(L,F),
   L = field(_,_,_,Y),
   var(Y),
   N1 is N-1,
   findPath(N1,L).
findPath(0,_).

% getPath(+F, ?P) extracts a list of coordinates P from the constructed
% path starting from field F
getPath(F, []) :-
   var(F),
   !.
getPath(field(I,J,_,F), [(I,J)|P]) :-
   getPath(F,P).
   
% knightTour(+S,+C,?L) solves the Knight Tour problem on the board
% of size S=(X,Y) starting from field C=(I,J)
knightTour(S,(I,J),L) :-
   S = (X,Y),
   N is X*Y-1,
   findall(field(U,V,_,_), (between(1,X,U), between(1,Y,V)), B),
   combineFields(B,B),
   member(F,B),
   F = field(I,J,_,_),
   !,
   findPath(N,F),
   getPath(F,L).

% the main program

main :- knightTour((8,8),(1,1),L),
        write(L),
        nl,
        fail.

% TWI, May 3, 2009
