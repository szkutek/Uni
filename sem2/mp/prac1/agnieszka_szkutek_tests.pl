% Definiujemy moduł zawierający testy.
% Należy zmienić nazwę modułu na {imie}_{nazwisko}_tests gdzie za
% {imie} i {nazwisko} należy podstawić odpowiednio swoje imię
% i nazwisko bez znaków diakrytycznych
:- module(agnieszka_szkutek_tests, [tests/5]).

% definiujemy operatory ~/1 oraz v/2
:- op(200, fx, ~).
:- op(500, xfy, v).

% Zbiór faktów definiujących testy

%tautologie
tests(prawo_wyl_srodka, validity, [p v ~p], 5000, solution([(p,f)])).
tests(tautologie_4_zmienne, validity, [q v r v ~q, ~p v ~r v p v s, s v ~s], 5000, count(16)).
tests(prawo_symplifikacji, validity, [~q v p v q], 5000, count(4)).

%sprzeczne
tests(proste_sprzeczne,validity,[p, ~p],5000,count(0)).
tests(proste_sprzeczne_z_wieksza_iloscia_zmiennych,validity,[p v q, p, r v r, q v s, ~p],5000,count(0)).
tests(trudniejsze_sprzeczne,validity,[p v q v r, ~p v ~q v ~r, ~q],5000,count(0)).
tests(z_whitebooka_sprzeczne,validity,[p v r, ~r v ~s, q v s, q v r, ~p v ~q, s v p],5000,count(0)).
tests(prawo_symplifikacji_sprzeczne, validity, [~q, p, q], 5000, count(0)).

%spełnialne
tests(z_whitebooka_trzy_zmienne,validity,[p v q v r, ~r v ~q v ~p, ~q v r, ~r v p],5000,solution([(p,t),(q,f),(r,f)])).
tests(proste_wiele_zmiennych, validity, [~a,b,~c,d,e,~h,g,p v ~p], 5000, solution([(a,f),(b,t),(c,f),(d,t),(e,t),(h,f),(g,t),(p,f)])).
tests(spelnione_dla_prawdziwych, validity, [p,q,r], 5000, solution([(p,t),(q,t),(r,t)])).
tests(spelnione_dla_falszywych, validity, [~p,~q,~r], 5000, solution([(p,f),(q,f),(r,f)])).
tests(powtorzona_zmienna_w_klauzuli, validity, [p v q, r v r, p v ~r], 5000, solution([(p,t),(q,f),(r,t)])).
tests(powtorzona_zmienna, validity, [p v q,p,r,p,r], 5000, solution([(p,t),(q,f),(r,t)])).

%performance
tests(duzo_zmiennych_tylko_prawo_wyl_srodka, performance, [a v ~a, b v ~b, c v ~c, d v ~d, e v ~e, g v ~g, h v ~h], 1000, count(128)).
tests(duzo_klauzul_prosta_sprzecznosc, performance, [p v r, q, ~r v ~s, q v s, ~q v r, ~p v ~q, s v p, ~q], 1000, count(0)).
