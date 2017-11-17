package narzedzia;

/**
 * Klasa obliczająca mnożenie
 */
public class Mnoz extends Funkcja2Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Mnoz() {
        super();
    }

    /**
     * Oblicza mnożenie
     *
     * @return arg1 * arg2
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return arg1.obliczWartosc() * arg2.obliczWartosc();
    }
}
