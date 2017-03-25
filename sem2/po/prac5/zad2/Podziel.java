package zad2;

public class Podziel extends Wezel {

    public Podziel(Wyrazenie l, Wyrazenie p) {
        super(l, p);
    }

    @Override
    public int oblicz() {
        int res = prawy.oblicz();
        if (res == 0)
            throw new IllegalArgumentException("Nie mozna dzielic przez 0.");
        return lewy.oblicz() / prawy.oblicz();
    }

    @Override
    public String toString() {
        return "Podziel{lewy = " + lewy + ", prawy = " + prawy + "}";
    }
}
