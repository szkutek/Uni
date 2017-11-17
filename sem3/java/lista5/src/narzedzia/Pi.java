package narzedzia;

/**
 * Klasa reprezentująca stałą PI
 */
public class Pi extends Funkcja0Arg {

    /**
     * Zwraca wartość stałej Pi
     *
     * @return wartość stałej Pi
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.PI;
    }
}
