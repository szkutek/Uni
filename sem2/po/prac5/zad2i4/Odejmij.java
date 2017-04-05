package zad2;

public class Odejmij extends Wezel {

    public Odejmij(Wyrazenie l, Wyrazenie p) {
        super(l, p);
    }

    @Override
    public int oblicz() {
        return lewy.oblicz() - prawy.oblicz();
    }

    @Override
    public Wyrazenie pochodna() {
        Wyrazenie lewaPochodna = lewy.pochodna();
        Wyrazenie prawaPochodna = prawy.pochodna();
        Wyrazenie zero = new Stala(0);
        Wyrazenie jeden = new Stala(1);

        if (lewaPochodna.equals(zero) && prawaPochodna.equals(zero)) {
            return zero;
        } else if (lewaPochodna.equals(zero)) {
            return new Odejmij(zero, prawaPochodna);
        } else if (prawaPochodna.equals(zero)) {
            return lewaPochodna;
        } else {
            return new Odejmij(lewaPochodna, prawaPochodna);
        }
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
//        return "Odejmij{lewy = " + lewy + ", prawy = " + prawy + "}";
        return "( " + lewy + " - " + prawy + ")";
    }
}
