package narzedzia;

/**
 * Klasa obliczająca funkcję sgn() - znak liczby
 */
public class Sgn extends Funkcja1Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Sgn() {
        super();
    }

    /**
     * Oblicza wartość funkcji
     *
     * @return -1 jeśli arg1 jest ujemny, 0 jeśli arg1=0 lub 1 jeśli arg1 jest
     * dodatni
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.signum(arg1.obliczWartosc());
    }
}
