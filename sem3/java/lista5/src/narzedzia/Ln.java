package narzedzia;

/**
 * Klasa obliczająca funkcję ln() - logarytm naturalny
 */
public class Ln extends Funkcja1Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Ln() {
        super();
    }

    /**
     * Oblicza wartość funkcji ln
     *
     * @return ln(arg)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        if (arg1.obliczWartosc() > 0) {
            return Math.log(arg1.obliczWartosc());
        } else {
            throw new ONP_BledneWyrazenie("Nie można obliczyć logarytmu naturalnego z liczby ujemnej.");
        }
    }
}
