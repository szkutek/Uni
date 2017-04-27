% Definiujemy moduł zawierający testy.
% Należy zmienić nazwę modułu na {imie}_{nazwisko}_tests gdzie za
% {imie} i {nazwisko} należy podstawić odpowiednio swoje imię
% i nazwisko bez wielkich liter oraz znaków diakrytycznych
:- module(agnieszka_szkutek_tests, [tests/3]).

% Zbiór faktów definiujących testy
% Należy zdefiniować swoje testy
tests(empty_program, input(""), program([])).
tests(invalid, input("def main()"), no).
tests(srcpos, input("def main(_) = 1"),
  program([def(main, wildcard(file(test, 1, 10, 9, 1)), num(no, 1)) ])).

% def
tests(definition_with_no_body, input("def main(A)"), no).
tests(definition_with_no_body_2, input("def main(A) = "), no).
tests(definition_without_def, input("main(A) = A"), no).
tests(definition_wildcard_in_body, input("main(A) = _"), no).
tests(definition_missing_name, input("def (A) = A"), no).

% no def
tests(no_definition__var, input("A"), no).
tests(no_definition__wildcard, input("_"), no).
tests(no_definition__if_statement, input("if A then B else C"), no).

% if
tests(if__simple,
    input("def main(E1) =
        if E1 then E2 else E3"),
    program([ def(main, var(no, 'E1'),
        if(no, var(no, 'E1'), var(no, 'E2'), var(no, 'E3') )) ])).
tests(if__missing_if_keyword,
    input("def main(E1) =
        E1 then E2 else E3"), no).
tests(if__missing_if_argument,
    input("def main(E1) =
        if then E2 else E3"), no).
tests(if__missing_then_keyword,
    input("def main(E1) =
        if E1 E2 else E3"), no).
tests(if__missing_then_argument,
    input("def main(E1) =
        if E1 then else E3"), no).
tests(if__missing_else_keyword,
    input("def main(E1) =
        if E1 then E2 E3"), no).
tests(if__missing_else_argument,
    input("def main(E1) =
        if E1 then E2 else"), no).

% let
tests(let__simple,
    input("def main(P) =
        let P = E1 in E2"),
    program([ def(main, var(no, 'P'),
        let(no, var(no, 'P'), var(no, 'E1'), var(no, 'E2') )) ])).
tests(let__missing_let_keyword,
    input("def main(P) =
        P = E1 in E2"), no).
tests(let__missing_equal_part,
    input("def main(P) =
        let P in E2"), no).
tests(let__missing_second_part,
    input("def main(P) =
        let P = E1"), no).

% op >, <, ...
% associative properties
tests(op_assoc__non_assoc,
    input("def main(X,Y,Z) =
        X > Y > Z"), no).
tests(op_assoc__left_assoc,
    input("def main(X,Y,Z) =
        X % Y % Z"),
    program([ def(main,
        pair(no, var(no, 'X'), pair(no, var(no, 'Y'), var(no, 'Z')) ),
        op(no, '%', op(no, '%', var(no,'X'),  var(no, 'Y') ),
            var(no, 'Z'))) ])).
tests(op_assoc__right_assoc,
    input("def main(X,Y,Z) =
        X @ Y @ Z"),
    program([ def(main,
        pair(no, var(no, 'X'), pair(no, var(no, 'Y'), var(no, 'Z')) ),
        op(no, '@', var(no, 'X'),
            op(no, '@', var(no,'Y'),  var(no, 'Z') ))) ])).
tests(op__double_operator,
    input("def main(X,Y) =
        X >> Y"), no).
tests(op__double_operator_2,
    input("def main(X,Y) =
        X == Y"), no).



% bitsel
tests(bitsel__simple1,
    input("def main(X,I) =
        X[I]"),
    program([ def(main,
        pair(no, var(no, 'X'), var(no, 'I')),
        bitsel(no, var(no, 'X'), var(no, 'I') )) ])).
tests(bitsel__simple2,
    input("def main(X,I) =
        X[I .. J]"),
    program([ def(main, pair(no, var(no, 'X'), var(no, 'I')),
        bitsel(no, var(no, 'X'), var(no, 'I'), var(no, 'J') )) ])).
tests(bitsel__missing_dots,
    input("def main(X,I) =
        X[I J]"), no).
tests(bitsel__missing_one_dot,
    input("def main(X,I) =
        X[I . J]"), no).
tests(bitsel__comma_instead_of_dots,
    input("def main(X,I) =
        X[I,J]"), no).
tests(bitsel__missing_right_parenthesis,
    input("def main(X,I) =
        X[I"), no).
tests(bitsel__empty_parentheses,
    input("def main(X,I) =
        X[ ]"), no).
