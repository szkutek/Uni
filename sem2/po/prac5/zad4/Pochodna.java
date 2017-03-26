package zad4;

import zad2.Wezel;
import zad2.Wyrazenie;

public class Pochodna extends Wezel {

    public Pochodna(Wyrazenie l) {
        super(l, null);
    }

    @Override
    public int oblicz() {
        return 0;
    }

    public Wyrazenie pochodna() {
        return lewy.pochodna();
    }

    @Override
    public String toString() {
        return "Pochodna{}";
    }
}
