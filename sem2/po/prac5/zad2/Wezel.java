package zad2;

public abstract class Wezel extends Wyrazenie {
    protected Wyrazenie lewy;
    protected Wyrazenie prawy;

    public Wezel(Wyrazenie l, Wyrazenie p) {
        lewy = l;
        prawy = p;
    }

    @Override
    public abstract int oblicz();
}
