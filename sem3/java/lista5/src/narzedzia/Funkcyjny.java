package narzedzia;

/**
 * Interfejs dla funkcji
 */
public interface Funkcyjny extends Obliczalny {
    /**
     * Zwraca ilość argumentów potrzebnych do obliczenia funkcji
     *
     * @return ilość argumentów potrzebnych do obliczenia funkcji
     */
    int arnosc();

    /**
     * Zwraca ilość brakujących argumentów
     *
     * @return ilość brakujących argumentów
     */
    int brakujaceArgumenty();

    /**
     * Dodaje argument do funkcji
     *
     * @param a argument
     * @throws WyjatekONP jeśli dodanych zostanie zbyt wiele argumentów
     */
    void dodajArgument(double a) throws WyjatekONP;
}