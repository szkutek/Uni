package narzedzia;

/**
 * Klasa obliczająca funkcję log() - logarytmu o zadanej podstawie
 */
public class Lg extends Funkcja2Arg {

    /**
     * Tworzy funkcję bez argumentów
     */
    public Lg() {
        super();
    }

    /**
     * Oblicza wartość funkcji log
     *
     * @return log_arg1(arg2)
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        if (arg1.obliczWartosc() > 0 && arg2.obliczWartosc() > 0 && arg2.obliczWartosc() != 1) {
            return Math.log(arg2.obliczWartosc()) / Math.log(arg1.obliczWartosc());
        } else if (arg1.obliczWartosc() <= 0) {
            throw new ONP_BledneWyrazenie("Nie można obliczyć logarytmu o niedodatniej podstawie.");
        } else if (arg2.obliczWartosc() <= 0) {
            throw new ONP_BledneWyrazenie("Nie można obliczyć logarytmu z liczby niedodatniej.");
        } else if (arg2.obliczWartosc() == 1) {
            throw new ONP_BledneWyrazenie("Nie można obliczyć logarytmu z 1.");
        } else if (arg1.obliczWartosc() <= 0 && arg2.obliczWartosc() <= 0) {
            throw new ONP_BledneWyrazenie("Nie można obliczyć logarytmu o niedodatniej podstawie z liczby niedodatniej.");
        } else {
            throw new ONP_BledneWyrazenie("Nie można obliczyć logarytmu dla podanych argów.");
        }
    }
}
