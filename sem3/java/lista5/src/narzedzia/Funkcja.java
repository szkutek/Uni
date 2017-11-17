package narzedzia;

/**
 * Abstrakcyjna klasa reprezentująca funkcję
 */
public abstract class Funkcja extends Symbol implements Funkcyjny {

    /**
     * Zwraca ilość argumentów potrzebnych do obliczenia funkcji
     *
     * @return ilość argumentów potrzebnych do obliczenia funkcji
     */
    @Override
    public abstract int arnosc();

    /**
     * Zwraca ilość brakujących argumentów
     *
     * @return ilość brakujących argumentów
     */
    @Override
    public abstract int brakujaceArgumenty();

    /**
     * Dodaje argument do funkcji
     *
     * @param a argument
     * @throws WyjatekONP jeśli dodanych zostanie zbyt wiele argumentów
     */
    @Override
    public abstract void dodajArgument(double a) throws WyjatekONP;

    /**
     * Oblicza wartość
     *
     * @return wartość wyrażenia
     * @throws WyjatekONP jeśli dodanych zostanie zbyt wiele argumentów
     */
    @Override
    public abstract double obliczWartosc() throws WyjatekONP;
}
