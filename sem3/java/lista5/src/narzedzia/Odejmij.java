package narzedzia;

/**
 * Klasa obliczająca odejmowanie
 */
public class Odejmij extends Funkcja2Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Odejmij() {
        super();
    }

    /**
     * Oblicza odejmowanie
     *
     * @return arg1 - arg2
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return arg1.obliczWartosc() - arg2.obliczWartosc();
    }
}
