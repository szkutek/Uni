package zad2;

public class Pomnoz extends Wezel {

    public Pomnoz(Wyrazenie l, Wyrazenie p) {
        super(l, p);
    }

    @Override
    public int oblicz() {
        return lewy.oblicz() * prawy.oblicz();
    }

    @Override
    public Wyrazenie pochodna() {
        Wyrazenie lewaPochodna = lewy.pochodna();
        Wyrazenie prawaPochodna = prawy.pochodna();
        Wyrazenie zero = new Stala(0);
        Wyrazenie jeden = new Stala(1);

        if (lewaPochodna.equals(zero) && prawaPochodna.equals(zero)) {
            return zero;
        } else if (lewaPochodna.equals(zero) && prawaPochodna.equals(jeden)) {
            return lewy;
        } else if (lewaPochodna.equals(jeden) && prawaPochodna.equals(zero)) {
            return prawy;
        } else if (lewaPochodna.equals(jeden) && prawaPochodna.equals(jeden)) {
            return new Dodaj(lewy, prawy);
        } else {
            return new Dodaj(new Pomnoz(lewy.pochodna(), prawy), new Pomnoz(lewy, prawy.pochodna()));
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
//        return "Pomnoz{lewy = " + lewy + ", prawy = " + prawy + "}";
        return "( " + lewy + " * " + prawy + " )";
    }
}
