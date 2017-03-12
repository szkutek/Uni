//
// Created by szkutek on 28.02.17.
//

#ifndef ZAD1_KOLEKCJA_H
#define ZAD1_KOLEKCJA_H


#include <stdio.h>
#include <stdlib.h>


enum typ {
    Zbior, Torba
};

typedef struct elem {
    int w;
    struct elem *nast;
} Elem;

typedef struct {
    Elem *e;
    enum typ t;
} Kolekcja;


void wstaw(Kolekcja **k, Elem e);

int rozmiar(Kolekcja **k);

int szukaj(Kolekcja *k, Elem e);

void zbior(Kolekcja **k);

void torba(Kolekcja **k);



#endif //ZAD1_KOLEKCJA_H
