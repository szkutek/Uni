//
// Created by szkutek on 28.02.17.
//

#ifndef ZAD2_FIGURY_H
#define ZAD2_FIGURY_H

#include <stdio.h>
#include <stdlib.h>

enum typ {
    Punkt, Kolo, Kwadrat
};

typedef struct {
    float x;
    float y;
} Pkt;

typedef struct figura {
    enum typ typfig;
    Pkt P1;
    Pkt P2;
    float R;
} Figura;

void narysuj(Figura *f);

void przesun(Figura *f, float x, float y);

int zawiera(Figura *f, float x, float y);


Figura *punkt(Figura *f, Pkt P);

Figura *kolo(Figura *f, Pkt P, float R);

Figura *kwadrat(Figura *f, Pkt P1, Pkt P2);


#endif //ZAD2_FIGURY_H
