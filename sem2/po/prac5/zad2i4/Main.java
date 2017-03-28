package zad2;

import zad4.Pochodna;

public class Main {

    public static void main(String[] args) {
        Wyrazenie w = new Dodaj(new Dodaj(new Stala(4), new Stala(3)), new Odejmij(new Stala(3), new Stala(2)));
        System.out.println(w);
        System.out.println(w.oblicz());

        Stala a = new Stala(1);
        Stala b = new Stala(2);
        Stala c = new Stala(3);
        Stala d = new Stala(4);

        Zmienna x = new Zmienna("x");
        Lisc.dodajZmienna("x", 5);
        Wyrazenie w2 = new Podziel(new Pomnoz(x, d), b);
        System.out.println(w2);
        System.out.println(w2.oblicz());

        Wyrazenie w3 = new Dodaj(new Dodaj(new Stala(4), new Zmienna("x")), new Odejmij(new Stala(3), new Stala(2)));
        Wyrazenie p = new Pochodna(w3);
        System.out.println(w3.pochodna());

    }
}
