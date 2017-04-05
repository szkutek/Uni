%1.
%a. stos

% put(+E,+S,-R)
put(E,S,[E|S]).

% get(+S,-E,-R)
get([E|R],E,R).

empty([]).

% addall(-E, +G, +S, -R)
% addall(E,G,S,R):-

%b. lista różnicowa
put2(E,S-[E|T],S-T).

get2([E|R]-T, E, R-T).

empty2(H-H).
