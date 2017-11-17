package narzedzia;

/**
 * Klasa modeluje funkcję jednoargumentową
 */
public abstract class Funkcja1Arg extends Funkcja {
    /**
     * Argument funkcji
     */
    protected Operand arg1;

    /**
     * Ilość podanych argumentów
     */
    protected int ilosc_arg = 0;

    /**
     * Konstruktor tworzy funkcję bez arguemntów
     */
    public Funkcja1Arg() {
    }

    /**
     * Zwraca ilość argumentów potrzebnych do obliczenia funkcji
     *
     * @return 1
     */
    @Override
    public int arnosc() {
        return 1;
    }

    /**
     * Zwraca ilość brakujących argumentów
     *
     * @return ilość brakujących argumentów
     */
    @Override
    public int brakujaceArgumenty() {
        return arnosc() - ilosc_arg;
    }

    /**
     * Dodaje argument do funkcji
     *
     * @param a argument
     * @throws WyjatekONP jeśli dodanych zostanie zbyt wiele argumentów
     */
    @Override
    public void dodajArgument(double a) throws WyjatekONP {
        if (brakujaceArgumenty() == 1) {
            arg1 = new Liczba(a);
            ilosc_arg += 1;
        } else {
            throw new WyjatekONP("Podałeś za dużo argumentów do funkcji.");
        }
    }

    /**
     * Oblicza wartość funkcji
     *
     * @return wartość funkcji
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public abstract double obliczWartosc() throws WyjatekONP;
}
