package narzedzia;

/**
 * Klasa obliczająca funkcję max() - maksimum
 */
public class Max extends Funkcja2Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Max() {
        super();
    }

    /**
     * Oblicza wartość funkcji max
     *
     * @return max(arg1, arg2)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.max(arg1.obliczWartosc(), arg2.obliczWartosc());
    }
}
