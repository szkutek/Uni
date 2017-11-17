package narzedzia;

/**
 * Klasa reprezentująca funckję minimum z dwóch liczb
 */
public class Min extends Funkcja2Arg {
    public Min() {
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
        return Math.min(arg1.obliczWartosc(), arg2.obliczWartosc());
    }
}