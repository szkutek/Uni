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
    public String toString() {
        return "Odejmij{lewy = " + lewy + ", prawy = " + prawy + "}";
    }
}
