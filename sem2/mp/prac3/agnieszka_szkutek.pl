% W programie korzystam z fragmentów kodu autorstwa pana Tomasza Wierzbickego (while_parser.pl)
% :- module(agnieszka_szkutek, [parse/3]).
% [tokDef,tokVar(main),tokLBracket,tokVar('A'),tokRBracket,tokEq,tokVar('B')]

parse(_, Codes, Program) :-
  phrase(lexer(TokList), Codes),
  phrase(program(Program), TokList).

parse2(String, Program) :-
  string_to_list(String, Codes),
  phrase(lexer(TokList), Codes),
  phrase(program(Program), TokList).




lexer(Tokens) -->
 white_space,
 (  (  "(",       !, { Token = tokLBracket }
    ;  ")",       !, { Token = tokRBracket }
    ;  "[",       !, { Token = tokLSqBracket }
    ;  "]",       !, { Token = tokRSqBracket }
    ;  "..",      !, { Token = tokDots }
    ;  ",",       !, { Token = tokComma }
    ;  "=",       !, { Token = tokEq }
    ;  "<>",      !, { Token = tokNeq }
    ;  "<=",      !, { Token = tokLeq }
    ;  "<",       !, { Token = tokLt }
    ;  ">=",      !, { Token = tokGeq }
    ;  ">",       !, { Token = tokGt }
    ;  "^",       !, { Token = tokCaret }
    ;  "|",       !, { Token = tokVLine }
    ;  "+",       !, { Token = tokPlus }
    ;  "-",       !, { Token = tokMinus }
    ;  "&",       !, { Token = tokAnd }
    ;  "*",       !, { Token = tokTimes }
    ;  "/",       !, { Token = tokDiv }
    ;  "%",       !, { Token = tokPercent }
    ;  "@",       !, { Token = tokAt }
    ;  "#",       !, { Token = tokHash }
    ;  "~",       !, { Token = tokTilde }
    ;  "_",       !, { Token = tokWildcard }
    ;  digit(D),  !,
          number(D, N),
          { Token = tokNumber(N) }
    ;  letter(L), !, identifier(L, Id),
          { member((Id, Token), [ (def, tokDef),
                                  (else, tokElse),
                                  (if, tokIf),
                                  (in, tokIn),
                                  (let, tokLet),
                                  (then, tokThen) ]),
             !
          ;  Token = tokVar(Id)
          }
    ;  [_],
          { Token = tokUnknown }
    ),
    !,
       { Tokens = [Token | TokList] },
    lexer(TokList)
 ;  [],
       { Tokens = [] }
 ).

white_space -->
 [Char], { code_type(Char, space) }, !, white_space.
white_space -->
 [].

digit(D) -->
 [D],
    { code_type(D, digit) }.

digits([D|T]) -->
 digit(D),
 !,
 digits(T).
digits([]) -->
 [].

number(D, N) -->
 digits(Ds),
    { number_chars(N, [D|Ds]) }.

letter(L) -->
 [L], { code_type(L, alpha) }.

alphanum([A|T]) -->
 [A], { code_type(A, alnum) }, !, alphanum(T).
alphanum([]) -->
 [].

identifier(L, Id) -->
  alphanum(As),
    { atom_codes(Id, [L|As]) }.



program(P) --> definicje(P).

definicje([D1,D2]) --> definicja(D1),!, definicje(D2).
definicje([]) --> [], !.

definicja(Def) --> [tokDef],!, identyfikator(Id),
    [tokLBracket], wzorzec(Wz), [tokRBracket], [tokEq], wyrazenie(Wyr),
    {Def =.. [def, Id, Wz, Wyr]}.

wzorzec(wildcard(no)) --> [tokWildcard],!.
wzorzec(W) --> zmienna(W),!.
wzorzec(Wz) --> [tokLBracket],!, wzorzec(Wz),
    [tokRBracket].
wzorzec(Wz) --> wzorzec(W1), [tokComma],!, wzorzec(W2),
    {Wz =.. [pair, no, W1, W2]}.


wyrazenie(Wyr) --> [tokIf],!, wyrazenie(E1), [tokThen],
    wyrazenie(E2), tokElse, wyrazenie(E3),
    {Wyr =.. [if, no, E1, E2, E3]}.
wyrazenie(Wyr) --> [tokLet],!, wzorzec(P), '=',
    wyrazenie(E1), tokIn, wyrazenie(E2),
    {Wyr =.. [let, no, P, E1, E2]}.
wyrazenie(W) --> wyrazenie_op(W).


% wyrazenie_op(Wyr) --> wyrazenie_proste(Wyr).
% wyrazenie_op(Wyr) --> op_unarny(Op),!, wyrazenie_op(W),
%     Wyr =.. [op, no, Op, W].
% wyrazenie_op(Wyr) --> wyrazenie_op(W1),
%     op_binarny(Op),!, wyrazenie_op(W2),
%     Wyr =.. [op, no, Op, W1, W2].

wyrazenie_op(Wyr) -->
    w1(W1), op_binarny_2(Op),!, w1(W2),
    {Wyr =.. [op, no, Op, W1, W2]}
    ; w1(Wyr).
w1(Wyr) -->
    w2(W1), op_binarny_3(Op),!, w1(W2),
    {Wyr =.. [op, no, Op, W1, W2]}
    ; w2(Wyr).
w2(Wyr) --> w2(W1), op_binarny_4(Op),!, w3(W2),
    {Wyr =.. [op, no, Op, W1, W2]}
    ; w3(Wyr).
w3(Wyr) -->
    w3(W1), op_binarny_5(Op),!, w_unarne(W2),
    {Wyr =.. [op, no, Op, W1, W2]}.
w_unarne(Wyr) -->
    op_unarny(Op),!, wyrazenie_proste(W1),
    {Wyr =.. [op, no, Op, W1]}
    ; wyrazenie_proste(Wyr).


op_unarny(Op) -->
    [TokOp],
    {member( (TokOp, Op), [ (tokMinus, '-'),
                           (tokHash, '#'),
                           (tokTilde, '~')] )}.

op_binarny_2(Op) -->
    [TokOp],
    {member( (TokOp, Op), [ (tokEq, '='),
                           (tokNeq, '<>'),
                           (tokLt, '<'),
                           (tokLeq, '<='),
                           (tokGt, '>'),
                           (tokGeq, '>=')] )}.
op_binarny_3('@') --> [tokAt].
op_binarny_4(Op) -->
    [TokOp],
    {member( (TokOp, Op), [ (tokVLine, '|'),
                           (tokCaret, '^'),
                           (tokPlus, '+'),
                           (tokMinus, '-')] )}.
op_binarny_5(Op) -->
    [TokOp],
    {member( (TokOp, Op), [ (tokAnd, '&'),
                           (tokTimes, '*'),
                           (tokDiv, '/'),
                           (tokPercent, '%')] )}.



wyrazenie_proste(W) --> [tokLBracket], wyrazenie(W),
    [tokRBracket],!.
wyrazenie_proste(W) --> wybor_bitu(W),!.
wyrazenie_proste(W) --> wybor_bitow(W),!.
wyrazenie_proste(W) --> wyrazenie_atomowe(W),!.

wybor_bitu(W) --> wyrazenie_proste(E1), [tokLSqBracket],
    wyrazenie(E2), [tokRSqBracket],
    {W =.. [bitsel, no, E1, E2]}.
wybor_bitow(W) --> wyrazenie_proste(E1),
    [tokLSqBracket],
    wyrazenie(E2), [tokDots], wyrazenie(E3),
    [tokRSqBracket],
    {W =.. [bitsel, no, E1, E2, E3]}.

wyrazenie_atomowe(W) --> zmienna(W),!.
wyrazenie_atomowe(W) --> wywolanie_funkcji(W),!.
wyrazenie_atomowe(W) --> literal_calkowitoliczbowy(W),!.
wyrazenie_atomowe(W) --> pusty_wektor(W),!.
wyrazenie_atomowe(W) --> pojedynczy_bit(W),!.

zmienna(W) --> identyfikator(W).

wywolanie_funkcji(W) --> identyfikator(Name),
    [tokLBracket], wyrazenie(E), [tokRBracket],
    {W =.. [call, no, Name, E]}.

pusty_wektor(empty(no)) --> [tokLBracket], [tokRBracket].

pojedynczy_bit(bit(no, E)) --> [tokLBracket], wyrazenie(E), [tokRBracket].

identyfikator(var(no,Id)) --> [tokVar(Id)].

literal_calkowitoliczbowy(N) --> [tokNumber(N)].
