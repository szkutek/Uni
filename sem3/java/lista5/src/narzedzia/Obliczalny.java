package narzedzia;

/**
 * Interfejs obliczalny
 */
public interface Obliczalny {
    /**
     * Oblicza wartość
     *
     * @return wartość wyrażenia
     * @throws WyjatekONP
     */
    double obliczWartosc() throws WyjatekONP;
}
