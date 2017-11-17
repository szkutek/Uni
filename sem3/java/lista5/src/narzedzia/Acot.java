package narzedzia;

/**
 * Klasa obliczająca funkcję acot() - arcus cotangens
 */
public class Acot extends Funkcja1Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Acot() {
        super();
    }

    /**
     * Oblicza wartość funkcji acot
     *
     * @return acot(arg)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        double rads = Math.toRadians(arg1.obliczWartosc());
        double temp = Math.atan(1 / Math.tan(rads));
        return Math.toDegrees(temp);
    }
}
