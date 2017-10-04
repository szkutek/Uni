import random as rnd


def rzut_kostka():
    return rnd.randint(1, 6)


def gra(n=3):
    tura = 1
    wynik = [0, 0]
    while tura <= n or wynik[0] == wynik[1]:
        wynik_czesciowy = [0, 0]
        for i in (0, 1):
            r1 = rzut_kostka()
            r2 = rzut_kostka()
            wynik_czesciowy[i] = r1 + r2
            print('Gracz ' + str(i + 1) + ': | '
                  + str(r1) + ' + ' + str(r2) + ' = ' + str(wynik_czesciowy[i]))

        if wynik_czesciowy[0] > wynik_czesciowy[1]:
            wynik[0] += 1
        elif wynik_czesciowy[0] < wynik_czesciowy[1]:
            wynik[1] += 1
        print('Wynik po turze ' + str(tura) + ': ')
        print('Gracz 1: ' + str(wynik[0]) +
              ', Gracz 2: ' + str(wynik[1]) + '\n')
        tura += 1


if __name__ == '__main__':
    gra(3)
