package narzedzia;

/**
 * Klasa obliczająca część ułamkową liczby
 *
 * @author notechus
 */
public class Frac extends Funkcja1Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Frac() {
        super();
    }

    /**
     * Oblicza wartość funkcji frac
     *
     * @return częśc ułamkowa argumentu
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        long i = (long) arg1.obliczWartosc();
        return (double) arg1.obliczWartosc() - i;
    }
}
