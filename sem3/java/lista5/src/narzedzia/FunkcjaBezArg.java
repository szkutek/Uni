package narzedzia;

/**
 * Abstrakcyjna klasa modelująca funkcję bezargumentową
 *
 * @author notechus
 */
public abstract class FunkcjaBezArg extends Funkcja
{

    /**
     * Oblicza wartość funkcji
     *
     * @return wartość funkcji
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public abstract double obliczWartosc() throws WyjatekONP;

    /**
     * Zwraca ilość argumentów potrzebnych do obliczenia funkcji
     *
     * @return 0
     */
    @Override
    public int arnosc()
    {
        return 0;
    }

    /**
     * Zwraca ilość brakujących argumentów
     *
     * @return ilość brakujących argumentów
     */
    @Override
    public int brakujaceArgumenty()
    {
        return 0;
    }

    /**
     * Dodaje argument do funkcji
     *
     * @param x argument
     * @throws WyjatekONP jeśli dodanych zostanie zbyt wiele argumentów
     */
    @Override
    public void dodajArgument(double x) throws WyjatekONP
    {
        throw new WyjatekONP("Zostalo podanych za duzo argumentow.");
    }
}
