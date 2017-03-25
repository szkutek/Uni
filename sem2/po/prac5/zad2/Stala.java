package zad2;

public class Stala extends Lisc {

    public Stala(int wart) {
        super(wart);
    }

    @Override
    public int oblicz() {
        return wartosc;
    }

    @Override
    public String toString() {
        return "Stala{wartosc = " + wartosc + "}";
    }
}
