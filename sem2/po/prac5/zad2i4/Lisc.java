package zad2;

import java.util.Hashtable;

public abstract class Lisc implements Wyrazenie {
    protected static Hashtable<String, Integer> zmienne;
    protected int wartosc;

    static {
        zmienne = new Hashtable<String, Integer>();
    }

    protected Lisc() {
    }

    protected Lisc(int wart) {
        wartosc = wart;
    }

    @Override
    public abstract int oblicz();

    @Override
    public abstract Wyrazenie pochodna();

    public static void dodajZmienna(String nazwa, int wartosc) {
        zmienne.put(nazwa, wartosc);
    }

    public static void usunZmienna(String nazwa) {
        zmienne.remove(nazwa);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Lisc)) {
            return false;
        }

        Lisc castObj = (Lisc) obj;

        return castObj.wartosc == this.wartosc;
    }


}
