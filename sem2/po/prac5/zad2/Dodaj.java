package zad2;

public class Dodaj extends Wezel {

    public Dodaj(Wyrazenie l, Wyrazenie p) {
        super(l, p);
    }

    @Override
    public int oblicz() {
        return lewy.oblicz() + prawy.oblicz();
    }

    @Override
    public String toString() {
        return "Dodaj{lewy = " + lewy + ", prawy = " + prawy + "}";
    }

    @Override
    public Wyrazenie pochodna() {
        Wyrazenie lewaPochodna = lewy.pochodna();
        Wyrazenie prawaPochodna = prawy.pochodna();
        Wyrazenie zero = new Stala(0);
        Wyrazenie jeden = new Stala(1);

        if (lewaPochodna == zero && prawaPochodna == zero) {
            return zero;
        } else if (lewaPochodna == zero) {
            return prawaPochodna;
        } else if (prawaPochodna == zero) {
            return lewaPochodna;
        } else {
            return new Dodaj(lewaPochodna, prawaPochodna);
        }
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
