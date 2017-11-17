package narzedzia;

/**
 * Klasa obliczająca dzielenie
 */
public class Dziel extends Funkcja2Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Dziel() {
        super();
    }

    /**
     * Oblicza wartość dzielenia
     *
     * @return arg1 / arg2
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        if (arg2.obliczWartosc() != 0.0) {
            return arg1.obliczWartosc() / arg2.obliczWartosc();
        } else {
            throw new ONP_DzieleniePrzez0();
        }
    }
}
