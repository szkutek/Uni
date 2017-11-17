package narzedzia;

/**
 * Klasa obliczająca funkcję ceil() - sufit liczby
 */
public class Ceil extends Funkcja1Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Ceil() {
        super();
    }

    /**
     * Oblicza wartość funkcji ceil
     *
     * @return ceil(arg)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.ceil(arg1.obliczWartosc());
    }
}
