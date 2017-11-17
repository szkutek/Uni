package narzedzia;

/**
 * Klasa obliczająca funkcję sin() - sinus
 */
public class Sin extends Funkcja1Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Sin() {
        super();
    }

    /**
     * Oblicza wartość funkcji sin
     *
     * @return sin(arg)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        double radians = Math.toRadians(arg1.obliczWartosc());
        return Math.sin(radians);
    }
}
