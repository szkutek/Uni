//
// Created by szkutek on 28.02.17.
//

#include "figury.h"


void narysuj(Figura *f) {

    switch (f->typfig) {
        case (Punkt):
            printf("Punkt o wspolrzednych (x,y)=(%g,%g)\n", f->P1.x, f->P1.y);
            break;
        case (Kolo):
            printf("Kolo o srodku w punkcie (x,y)=(%g,%g) i promieniu %g\n", f->P1.x, f->P1.y, f->R);
            break;
        case (Kwadrat):
            printf("Kwadrat o wierzcholkach w punktach (%g,%g),(%g,%g),(%g,%g),(%g,%g)\n",
                   f->P1.x, f->P1.y,
                   f->P2.x, f->P1.y,
                   f->P2.x, f->P2.y,
                   f->P1.x, f->P2.y);
            break;
    }
}

void przesun(Figura *f, float x, float y) {

    f->P1.x += x;
    f->P1.y += y;

    if (f->typfig == Kwadrat) {
        f->P2.x += x;
        f->P2.y += y;
    }
}

int zawiera(Figura *f, float x, float y) {

    float x1 = f->P1.x;
    float y1 = f->P1.y;

    float odl = (y - y1) * (y - y1) + (x - x1) * (x - x1);
    float x2 = f->P2.x;
    float y2 = f->P2.y;


    switch (f->typfig) {
        case (Punkt):
            if (x1 == x && y1 == y)
                return 1;
            break;
        case (Kolo):
            if (odl * odl <= f->R * f->R)
                return 1;
            break;
        case (Kwadrat):
            if (x1 <= x && x <= x2 && y1 <= y && y <= y2)
                return 1;
            break;
    }
    return 0;
}


Figura *punkt(Figura *f, Pkt P) {
    f = (Figura *) malloc(sizeof(Figura));
    f->typfig = Punkt;
    f->P1 = P;
    return f;
}

Figura *kolo(Figura *f, Pkt P, float R) {
    f = (Figura *) malloc(sizeof(Figura));
    f->typfig = Kolo;
    f->P1 = P;
    f->R = R;
    return f;
}

Figura *kwadrat(Figura *f, Pkt P1, Pkt P2) {
    f = (Figura *) malloc(sizeof(Figura));
    f->typfig = Kwadrat;
    f->P1 = P1;
    f->P2 = P2;
    return f;
}

