package narzedzia;

/**
 * Klasa obliczająca funkcję floor() - podłoga liczby
 *
 * @author notechus
 */
public class Floor extends Funkcja1Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Floor() {
        super();
    }

    /**
     * Oblicza wartość funkcji floor
     *
     * @return floor(arg)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.floor(arg1.obliczWartosc());
    }
}
