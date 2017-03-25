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
}
