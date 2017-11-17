package narzedzia;

/**
 * Klasa reprezentująca funkcję zwracającą wartość bezwzględną
 */
public class Abs extends Funkcja1Arg {
    /**
     * Tworzy funkcję bez argumentów
     */
    public Abs() {
        super();
    }

    /**
     * Oblicza wartość funkcji
     *
     * @return wartość funkcji
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.abs(arg1.obliczWartosc());
    }
}
