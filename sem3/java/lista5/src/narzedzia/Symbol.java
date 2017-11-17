package narzedzia;

/**
 * Abstrakcyjna klasa modelująca symbol
 */
public abstract class Symbol implements Obliczalny {

    /**
     * Oblicza wartość symbolu
     *
     * @return wartość wyrażenia
     * @throws WyjatekONP
     */
    @Override
    public abstract double obliczWartosc() throws WyjatekONP;
}
