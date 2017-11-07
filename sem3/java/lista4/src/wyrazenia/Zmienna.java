package wyrazenia;

/**
 * Klasa reprezentująca zmienną
 */
public class Zmienna extends Wyrazenie {
    /**
     * Zbiór zawiera pary {nazwa zmiennej, wartość zmiennej}
     */
    public static final Zbior zbior;
    public String nazwa;

    static {
        zbior = new Zbior();
    }

    /**
     * konstruktor, który ustala wartość zmiennej
     *
     * @param nazwa   nazwa zmiennej
     * @param wartosc wartosc zmiennej
     */
    public Zmienna(String nazwa, double wartosc) {
        this.nazwa = nazwa;
        zbior.ustal(new Para(nazwa, wartosc));
    }

    /**
     * konstruktor, który nie ustala wartości zmiennej
     *
     * @param nazwa nazwa zmiennej
     */
    public Zmienna(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * metoda zwraca wartość zmiennej (korzystając ze Zbioru)
     *
     * @return wartość zmiennej
     */
    @Override
    public double oblicz() {
        return zbior.czytaj(nazwa);
    }

    /**
     * metoda porównuje wartości dwóch zmiennych
     *
     * @param obj obiekt, z którym chcemy porównać wartość zmiennej
     * @return true, jeśli wartości wyrażeń są takie same, lub false, jeśli wartości wyrażeń są różne
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Zmienna)) {
            return false;
        }

        Zmienna zmienna = (Zmienna) obj;

        return zbior.czytaj(zmienna.nazwa) == zbior.czytaj(this.nazwa);
    }


    /**
     * metoda zwraca nazwę zmiennej
     *
     * @return nazwa zmiennej
     */
    @Override
    public String toString() {
        return nazwa;
    }

}
