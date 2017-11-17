package narzedzia;

/**
 * Klasa obliczająca funkcję exp() - exponenty
 */
public class Exp extends Funkcja1Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Exp() {
        super();
    }

    /**
     * Oblicza wartość funkcji exp
     *
     * @return exp(arg)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.exp(arg1.obliczWartosc());
    }
}
