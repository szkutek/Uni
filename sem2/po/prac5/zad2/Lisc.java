package zad2;

import java.util.Hashtable;

public abstract class Lisc extends Wyrazenie {
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

    public abstract int oblicz();

    public static void dodajZmienna(String nazwa, int wartosc) {
        zmienne.put(nazwa, wartosc);
    }

    public static void usunZmienna(String nazwa) {
        zmienne.remove(nazwa);
    }

}
