package zad2;

public class Main {

    public static void main(String[] args) {
        Wyrazenie w = new Pomnoz(new Dodaj(new Stala(4), new Stala(3)), new Odejmij(new Stala(3), new Stala(2)));
        System.out.println(w);
        System.out.println(w.oblicz());

        Stala b = new Stala(2);
        Stala c = new Stala(3);
        Stala d = new Stala(4);

        Zmienna x = new Zmienna("x");
        Lisc.dodajZmienna("x", 2);
        Wyrazenie w2 = new Podziel(new Pomnoz(x, d), b);
        System.out.println(w2);
        System.out.println(w2.oblicz());

        Lisc.dodajZmienna("x", 3);
        System.out.println(w2);
        System.out.println(w2.oblicz());

        Wyrazenie w3 = new Dodaj(new Dodaj(d, x), new Odejmij(c, b));
        System.out.println("4+x:");
        System.out.println(w3);
        System.out.println(w3.pochodna());

        w3 = new Dodaj(new Dodaj(x, x), new Pomnoz(x, new Stala(2)));
        System.out.println(w3);
        System.out.println(w3.pochodna());

        w3 = new Podziel(new Pomnoz(x, x), new Pomnoz(x, x));
        System.out.println(w3);
        System.out.println(w3.pochodna());
        System.out.println(w3.pochodna().oblicz());

    }
}
