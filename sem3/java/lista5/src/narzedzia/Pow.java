package narzedzia;

/**
 * Klasa obliczająca funkcję pow() - potęga o zadanej podstawie
 */
public class Pow extends Funkcja2Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Pow() {
        super();
    }

    /**
     * Oblicza wartość funkcji pow
     *
     * @return arg1^arg2 (arg1 do potęgi arg2)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.pow(arg1.obliczWartosc(), arg2.obliczWartosc());
    }
}
