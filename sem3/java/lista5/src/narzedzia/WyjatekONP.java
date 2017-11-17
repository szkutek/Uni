package narzedzia;

/**
 * Klasa modelująca wyjątki ONP
 */
public class WyjatekONP extends Exception {
    /**
     * Konstruktor tworzy wyjątek z podaną wiadomością
     */
    public WyjatekONP(String message) {
    }

    /**
     * Konstruuje wyjątek z obiektem typu Throwable
     *
     * @param cause obiekt typu Throwable
     */
    public WyjatekONP(Throwable cause) {
        super(cause);
    }

    /**
     * Konstruuje wyjątek z podaną wiadomością i obiektem typu throwable
     *
     * @param message wiadomość
     * @param cause   obiekt typu throwable
     */
    public WyjatekONP(String message, Throwable cause) {
        super(message, cause);
    }
}
