package zad2;

public class Podziel extends Wezel {

    public Podziel(Wyrazenie l, Wyrazenie p) {
        super(l, p);
    }

    @Override
    public int oblicz() {
        int res = prawy.oblicz();
        if (res == 0) {
            throw new IllegalArgumentException("Nie mozna dzielic przez 0.");
        }
        return lewy.oblicz() / prawy.oblicz();
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
            return new Podziel(new Odejmij(zero, lewy), new Pomnoz(prawy, prawy));
        } else if (lewaPochodna == jeden && prawaPochodna == zero) {
            return new Podziel(jeden, prawy);
        } else if (lewaPochodna == jeden && prawaPochodna == jeden) {
            return new Podziel(new Odejmij(prawy, lewy), new Pomnoz(prawy, prawy));
        } else {
            return new Podziel(new Dodaj(new Pomnoz(lewy.pochodna(), prawy), new Pomnoz(lewy, prawy.pochodna())), new Pomnoz(prawy, prawy));
        }
    }

    @Override
    public String toString() {
        return "Podziel{lewy = " + lewy + ", prawy = " + prawy + "}";
    }
}
