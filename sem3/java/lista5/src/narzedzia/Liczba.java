package narzedzia;

/**
 * Klasa reprezentująca liczbę rzeczywistą
 */
public class Liczba extends Operand {
    /**
     * Liczba rzeczywista
     */
    private double wartosc;

    /**
     * Konstruktor tworzy liczbę o podanej wartości
     *
     * @param wartosc wartość liczby
     */
    public Liczba(double wartosc) {
        this.wartosc = wartosc;
    }

    /**
     * Zwraca wartość liczby
     *
     * @return wartość liczby
     * @throws WyjatekONP
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return wartosc;
    }
}
