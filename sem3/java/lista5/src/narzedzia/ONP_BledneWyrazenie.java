package narzedzia;

/**
 * Klasa modelująca wyjątek błędne wyrażenie
 */
public class ONP_BledneWyrazenie extends WyjatekONP {
    public ONP_BledneWyrazenie() {
        super("Podałeś błędne wyrażenie.");
    }

    /**
     * Konstruktor tworzy wyjąek z podaną wiadomością
     *
     * @param message podana wiadomość
     */
    public ONP_BledneWyrazenie(String message) {
        super(message);
    }

    /**
     * Konstruuje wyjątek z obiektem typu Throwable
     *
     * @param cause obiekt typu Throwable
     */
    public ONP_BledneWyrazenie(Throwable cause) {
        super("Podałeś błędne wyrażenie.", cause);
    }

    /**
     * Konstruuje wyjątek z podaną wiadomością i obiektem typu throwable
     *
     * @param message wiadomość
     * @param cause   obiekt typu throwable
     */
    public ONP_BledneWyrazenie(String message, Throwable cause) {
        super(message, cause);
    }
}
