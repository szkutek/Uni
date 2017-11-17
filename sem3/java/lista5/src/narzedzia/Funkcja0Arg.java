package narzedzia;

public abstract class Funkcja0Arg extends Funkcja {
    /**
     * Zwraca ilość argumentów potrzebnych do obliczenia funkcji
     *
     * @return ilość argumentów potrzebnych do obliczenia funkcji
     */
    @Override
    public int arnosc() {
        return 0;
    }

    /**
     * Zwraca ilość brakujących argumentów
     *
     * @return ilość brakujących argumentów
     */
    @Override
    public int brakujaceArgumenty() {
        return 0;
    }

    /**
     * Dodaje argument do funkcji
     *
     * @param a argument
     * @throws WyjatekONP jeśli dodanych zostanie zbyt wiele argumentów
     */
    @Override
    public void dodajArgument(double a) throws WyjatekONP {
        throw new WyjatekONP("Podałeś za dużo argumentów.");
    }

    /**
     * Oblicza wartość
     *
     * @return wartość wyrażenia
     * @throws WyjatekONP jeśli dodanych zostanie zbyt wiele argumentów
     */
    @Override
    public abstract double obliczWartosc() throws WyjatekONP;
}
