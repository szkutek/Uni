#include "kolekcja.h"

void zbior(Kolekcja **k) {
    *k = malloc(sizeof(Kolekcja));
    (*k)->t = Zbior;
}

void torba(Kolekcja **k) {
    *k = malloc(sizeof(Kolekcja));
    (*k)->t = Torba;
}

int rozmiar(Kolekcja **k) {
    int size = 0;
    Elem *e = (*k)->e;

    while (e != NULL) {
        size++;
        e = e->nast;
    }
    return size;
}

int szukaj(Kolekcja *k, Elem e) {
    int size = 0;
    Elem *tmp = k->e;

    while (tmp != NULL) {
        if (tmp->w == e.w) {
            size++;
            if (k->t == Zbior) return size;
        }
        tmp = tmp->nast;
    }
    return size;
}

void wstaw(Kolekcja **k, Elem e) {
    Elem *tmp = (*k)->e;

    if (tmp == NULL) {
        tmp = (Elem *) malloc(sizeof(Elem));
        tmp->w = e.w;
        tmp->nast = NULL;
        (*k)->e = tmp;
        return;
    }


    while (tmp->nast != NULL) {
        if ((*k)->t == Zbior && tmp->w == e.w) {
            return;
        }
        tmp = tmp->nast;

    }
    tmp->nast = (Elem *) malloc(sizeof(Elem));
    tmp->nast->w = e.w;
    tmp->nast->nast = NULL;
}