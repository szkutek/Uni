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
    public String toString() {
        return "Pomnoz{lewy = " + lewy + ", prawy = " + prawy + "}";
    }
}
