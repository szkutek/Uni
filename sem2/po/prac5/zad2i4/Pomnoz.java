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

        if (lewaPochodna == zero && prawaPochodna == zero) {
            return new Stala(0);
        } else if (lewaPochodna == zero && prawaPochodna == jeden) {
            return lewy;
        } else if (lewaPochodna == jeden && prawaPochodna == zero) {
            return prawy;
        } else if (lewaPochodna == jeden && prawaPochodna == jeden) {
            return new Dodaj(lewy, prawy);
        } else {
            return new Dodaj(new Pomnoz(lewy.pochodna(), prawy), new Pomnoz(lewy, prawy.pochodna()));
        }
    }

    @Override
    public String toString() {
        return "Pomnoz{lewy = " + lewy + ", prawy = " + prawy + "}";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
