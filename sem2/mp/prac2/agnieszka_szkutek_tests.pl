% Definiujemy moduł zawierający testy.
% Należy zmienić nazwę modułu na {imie}_{nazwisko}_tests gdzie za
% {imie} i {nazwisko} należy podstawić odpowiednio swoje imię
% i nazwisko bez wielkich liter oraz znaków diakrytycznych
:- module(agnieszka_szkutek_tests, [resolve_tests/5, prove_tests/4]).

% definiujemy operatory ~/1 oraz v/2
:- op(200, fx, ~).
:- op(500, xfy, v).

% Zbiór faktów definiujących testy dla predykatu resolve
% Należy zdefiniować swoje testy

resolve_tests(jedna_zmienna, p, p,~p,[]).
resolve_tests(zmienna_na_poczatku_klauzuli, p, p v q,~p v ~q, q v ~q).
resolve_tests(zmienna_na_koncu_klauzuli, q, p v q,~p v ~q, p v ~p).

resolve_tests(podwojona_zmienna_w_wyniku, p, p v q,~p v q, q).
resolve_tests(podwojona_zmienna2, p, r v p,~p v r,r).

resolve_tests(rozne_zmienne, q, p v q, ~q v r, p v r).
resolve_tests(rozne_zmienne_kolejnosc, q, b v q, ~q v a, b v a).


% Zbiór faktów definiujących testy dla predykatu prove
% Należy zdefiniować swoje testy
prove_tests(example, validity, [p v q v ~r, ~p v q, r v q, ~q, p], unsat).
prove_tests(excluded_middle, validity, [p v ~p], sat).

prove_tests(pusta_klauzula, validity, [[]], unsat).
prove_tests(pusta_klauzula_plus_zmienna, validity, [p, []], unsat).
prove_tests(proste_sprzeczne_jedna_zmienna, validity, [p, ~p], unsat).
prove_tests(sprzeczne_z_whitebooka, validity, [p v r, ~r v ~s, q v s, q v r, ~p v ~q, s v p], unsat).

prove_tests(proste_spelnialne_dwie_zmienne, validity, [p,~q], sat).
prove_tests(proste_spelnialne_dwie_zmienne_2, validity, [p v ~q, ~p v q], sat).
prove_tests(tautologie_4_zmienne, validity, [q v r v ~q, ~p v ~r v p v s, s v ~s], sat).
prove_tests(tautologia2, validity, [~q v p v q], sat).

prove_tests(duzo_klauzul_prosta_sprzecznosc_q, performance, [r v a,g v b,c v s,d v e,e,p v r, q, ~r v ~s, q v s, ~q v r, ~p v ~q, s v p, ~q], unsat).
prove_tests(proste_spelnialne_wiele_zmiennych, performance, [~a,b,~c,d,e,f,g,~h,i,j,k,l,~m,n,~o,p,~r,s,t,~u], sat).
