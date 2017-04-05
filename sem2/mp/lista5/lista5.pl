% 1
diffapp(_, Back, Back).
% OL1 = [L|H1]
diffappend(OL1-H1,H1-H2,OL1-H2).

appn1(L,OL):-
    appn(L,[OL]-OL).
appn([],_-[]).
appn([[]|T],A):-appn(T,A).
appn([[H1|T1]|T],OL-End):-
    End = [H1|E1],
    appn([T1|T],OL-E1).

% 2
flattn().
