#include "figury.h"

int main() {

    Pkt o = {0, 0};
    Pkt p1 = {1, 1};
    float r = 1;

    Figura *pkt1;
    Figura *kolo1;
    Figura *kw1;

    pkt1 = punkt(pkt1, o);
    kolo1 = kolo(kolo1, o, r);
    kw1 = kwadrat(kw1, o, p1);

    narysuj(pkt1);
    narysuj(kolo1);
    narysuj(kw1);

    przesun(pkt1, 0.5, 0.5);
    narysuj(pkt1);

    przesun(kw1, 0.5, 0.5);
    narysuj(kw1);

    printf("figura zawiera dany punkt: %d", zawiera(kolo1, 0.5, 0.5));


    free(pkt1);
    free(kolo1);
    free(kw1);
    return 0;
}