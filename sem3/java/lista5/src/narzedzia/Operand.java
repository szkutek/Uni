package narzedzia;

/**
 * Klasa reprezentująca operand
 */
public abstract class Operand extends Symbol {
    /**
     * Zwraca wartość
     *
     * @return wartość
     * @throws WyjatekONP
     */
    @Override
    public abstract double obliczWartosc() throws WyjatekONP;
}
