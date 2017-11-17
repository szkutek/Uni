package narzedzia;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa modelująca zmienną - para klucz-wartość
 */
public class Zmienna extends Operand {
    /**
     * Identyfikator zmiennej
     */
    private String id;

    /**
     * Wartość zmiennej
     */
    private double wartosc;

    /**
     * Wzorzec identyfikatora
     */
    private final String wzorzec = "\\p{Alpha}\\p{Alnum}*";

    /**
     * Konstruuje zmienną o podanym identyfikatorze i wartości
     */
    public Zmienna(String id, double wartosc) throws IllegalArgumentException {
        Pattern p = Pattern.compile("\\p{Alpha}\\p{Alnum}*");
        Matcher m = p.matcher(id);
        boolean b = m.matches();
        if (b) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Identyfikator nie pasuje do wzorca: " + wzorzec);
        }
        this.wartosc = wartosc;
    }

    /**
     * Zwraca wartość
     *
     * @return wartość
     * @throws WyjatekONP
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return wartosc;
    }
}