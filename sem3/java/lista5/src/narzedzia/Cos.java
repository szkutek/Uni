package narzedzia;

/**
 * Klasa obliczająca funkcję cos() - cosinus
 */
public class Cos extends Funkcja1Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Cos() {
        super();
    }

    /**
     * Oblicza wartość funkcji cos
     *
     * @return cos(arg)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        double radians = Math.toRadians(arg1.obliczWartosc());
        return Math.cos(radians);
    }
}
