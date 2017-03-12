bezposrednie(wroclaw,warszawa).
bezposrednie(wroclaw,krakow).
bezposrednie(wroclaw,szczecin).
bezposrednie(szczecin,lublin).
bezposrednie(szczecin,gniezno).
bezposrednie(warszawa,katowice).
bezposrednie(gniezno,gliwice).
bezposrednie(lublin,gliwice).

connection(X,Y):-
	bezposrednie(X,Y).
connection(X,Y):-
	bezposrednie(X,Z),
	connection(Z,Y).

/*
?- bezposrednie(wroclaw,lublin).
false.

?- bezposrednie(wroclaw,X).
X = warszawa ;
X = krakow ;
X = szczecin.

?- bezposrednie(X,Z),bezposrednie(Z,gliwice).
X = szczecin,
Z = lublin .

?- bezposrednie(X,Z),bezposrednie(Z,gliwice).
X = szczecin,
Z = lublin ;
X = szczecin,
Z = gniezno ;
false.

?- bezposrednie(T,X),bezposrednie(X,Z),bezposrednie(Z,gliwice).
T = wroclaw,
X = szczecin,
Z = lublin .

?- bezposrednie(T,X),bezposrednie(X,Z),bezposrednie(Z,gliwice);bezposrednie(T,B),bezposrednie(B,gliwice);bezposrednie(T,gliwice).
T = wroclaw,
X = szczecin,
Z = lublin ;
T = wroclaw,
X = szczecin,
Z = gniezno ;
T = szczecin,
B = lublin ;
T = szczecin,
B = gniezno ;
T = gniezno ;
T = lublin.

*/
