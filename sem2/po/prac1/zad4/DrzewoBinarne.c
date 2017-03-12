//
// Created by szkutek on 28.02.17.
//

#include "DrzewoBinarne.h"


struct wezel *wstaw(struct wezel *wez, char *str) {

    if (wez == NULL) {
        struct wezel *tmp;
        tmp = (struct wezel *) malloc(sizeof(struct wezel));
        strcpy(tmp->s, str);
        tmp->lewy = NULL;
        tmp->prawy = NULL;
        return tmp;
    }
    int wartosc = strcmp(wez->s, str);
    if (wartosc >= 0) {
        wez->prawy = wstaw(wez->prawy, str);
    } else if (wartosc < 0) {
        wez->lewy = wstaw(wez->lewy, str);
    }

    return wez;
}

void wypiszBST(struct wezel *wez) {
    if (wez != NULL) {
        wypiszBST(wez->lewy);
        printf("%s \n", wez->s);
        wypiszBST(wez->prawy);
    }
}

int szukaj(char *str, struct wezel *wez) {

    while (wez != NULL) {
        int a = strcmp(wez->s, str);

        if (a == 0) return 1;
        else if (a > 0) {
            wez = wez->prawy;
        } else {
            wez = wez->lewy;
        }
    }

    return -1;
}

int rozmiar(struct wezel *wez) {
    int i = 0;


    if (wez == NULL)
        return 0;
    else {
        i += rozmiar(wez->lewy);
        i += rozmiar(wez->prawy);

        return i + 1;
    }
}