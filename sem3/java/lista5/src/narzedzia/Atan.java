package narzedzia;

/**
 * Klasa obliczająca funkcję atan() - arcus tangens
 */
public class Atan extends Funkcja1Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Atan() {
        super();
    }

    /**
     * Oblicza wartość funkcji atan
     *
     * @return atan(arg)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.atan(arg1.obliczWartosc());
    }
}
