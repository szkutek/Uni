#include "kolekcja.h"

int main() {

    Kolekcja *z;
    Kolekcja *t;
    Kolekcja *pusta;


    zbior(&z);
    torba(&t);
    torba(&pusta);

    Elem e = {1, NULL};
    Elem e2 = {2, NULL};
    Elem e3 = {1, NULL};

    wstaw(&z, e);
    wstaw(&z, e2);
    wstaw(&z, e3);

    wstaw(&t, e);
    wstaw(&t, e2);
    wstaw(&t, e3);


    int size = 0;
    int znalezione = 0;

    size = rozmiar(&z);
    znalezione = szukaj(z, e);

    printf("dla elementow %d, %d, %d:\n", e.w, e2.w, e3.w);

    printf("rozmiar zbioru = %d\n", size);
    printf("znalezione w zbiorze (elem. rowne %d) = %d\n", e.w, znalezione);


    size = rozmiar(&t);
    znalezione = szukaj(t, e);

    printf("rozmiar torby = %d\n", size);
    printf("znalezione w torbie (elem. rowne %d) = %d\n", e.w, znalezione);


    size = rozmiar(&pusta);
    znalezione = szukaj(pusta, e);

    printf("rozmiar torby = %d\n", size);
    printf("znalezione w torbie (elem. rowne %d) = %d\n", e.w, znalezione);


    free(z);
    free(t);
    free(pusta);
    return 0;
}

