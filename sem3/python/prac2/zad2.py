class Operacje:
    def __init__(self, lewy, prawy):
        self.lewy = lewy
        self.prawy = prawy


class Neg(Operacje):
    def __init__(self, lewy):
        super().__init__(lewy, False)

    def oblicz(self):
        return not self.lewy.oblicz()

    def __str__(self) -> str:
        return '~' + self.lewy.__str__()


class And(Operacje):
    def __init__(self, lewy, prawy):
        super().__init__(lewy, prawy)

    def oblicz(self):
        return self.lewy.oblicz() and self.prawy.oblicz()

    def __str__(self) -> str:
        return '(' + self.lewy.__str__() + ' ^ ' + self.prawy.__str__() + ')'


class Or(Operacje):
    def __init__(self, lewy, prawy):
        super().__init__(lewy, prawy)

    def oblicz(self):
        return self.lewy.oblicz() or self.prawy.oblicz()

    def __str__(self) -> str:
        return '(' + self.lewy.__str__() + ' v ' + self.prawy.__str__() + ')'


class Impl(Operacje):
    def __init__(self, lewy, prawy):
        super().__init__(lewy, prawy)

    def oblicz(self):
        return not self.lewy.oblicz() or self.prawy.oblicz()

    def __str__(self) -> str:
        return '(' + self.lewy.__str__() + ' => ' + self.prawy.__str__() + ')'


class Iff(Operacje):
    def __init__(self, lewy, prawy):
        super().__init__(lewy, prawy)

    def oblicz(self):
        return self.lewy.oblicz() == self.prawy.oblicz()

    def __str__(self) -> str:
        return '(' + self.lewy.__str__() + ' <=> ' + self.prawy.__str__() + ')'


class Zmienne:
    zmienne = {'true': True, 'false': False}

    def __init__(self):
        pass

    @staticmethod
    def dodaj_zmienna(nazwa, wartosc):
        Zmienne.zmienne[nazwa] = wartosc

    @staticmethod
    def usun_zmienna(nazwa):
        Zmienne.zmienne.pop(nazwa)


class Zmienna:
    def __init__(self, nazwa):
        self.nazwa = nazwa

    def oblicz(self):
        return Zmienne.zmienne[self.nazwa]

    def __str__(self) -> str:
        return self.nazwa


class Prawda(Zmienna):
    def __init__(self):
        super().__init__('true')


class Falsz(Zmienna):
    def __init__(self):
        super().__init__('false')


if __name__ == '__main__':
    print(Zmienna('t'))

    Zmienne.dodaj_zmienna('t', Prawda())
    Zmienne.dodaj_zmienna('f', Falsz())

    print(Zmienna('t').oblicz())

    print(Or(And(Zmienna('t'), Zmienna('t')), And(Zmienna('t'), Zmienna('f'))))
    print(And(Zmienna('t'), Zmienna('f')).oblicz())

    print()

    Zmienne.dodaj_zmienna('x', Prawda())
    Zmienne.dodaj_zmienna('y', Falsz())

    print(str(Impl(Zmienna('x'), And(Zmienna('y'), Prawda()))) + ' = ' + str(
        Impl(Zmienna("x"), And(Zmienna("y"), Prawda())).oblicz()))
    print(str(Neg(Iff(Zmienna('x'), Zmienna('x')))) + ' = ' + str(Neg(Iff(Zmienna('x'), Zmienna('x'))).oblicz()))
    print(str(And(Zmienna('true'), Zmienna('true'))) + ' = ' + str(
        And(Zmienna('true'), Zmienna('true')).oblicz()))

    print(str(And(Zmienna('true'), Falsz())) + ' = ' + str(
        And(Zmienna('true'), Falsz()).oblicz()))
