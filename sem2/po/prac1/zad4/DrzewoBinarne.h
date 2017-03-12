//
// Created by szkutek on 28.02.17.
//

#ifndef ZAD4_DRZEWOBINARNE_H
#define ZAD4_DRZEWOBINARNE_H

#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#include <string.h>

struct wezel {
    char *s;
    int l;
    struct wezel *lewy, *prawy;
} Wezel;


struct wezel *wstaw(struct wezel *wez, char *str);

void wypiszBST(struct wezel *wez);

int szukaj(char *str, struct wezel *wez);


#endif //ZAD4_DRZEWOBINARNE_H
