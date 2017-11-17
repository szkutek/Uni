package narzedzia;

public class ONP_PustyStos extends WyjatekONP {
    /**
     * Konstruktor tworzy wyjątek z domyślną wiadomością
     */
    public ONP_PustyStos() {
        super("Stos z wynikami pośrednimi jest pusty.");
    }

    /**
     * Konstruktor tworzy wyjątek z podaną wiadomością
     *
     * @param message
     */
    public ONP_PustyStos(String message) {
        super(message);
    }

    /**
     * Konstruuje wyjątek z obiektem typu Throwable
     *
     * @param cause obiekt typu Throwable
     */
    public ONP_PustyStos(Throwable cause) {
        super("Stos z wynikami pośrednimi jest pusty.", cause);
    }

    /**
     * Konstruuje wyjątek z podaną wiadomością i obiektem typu throwable
     *
     * @param message wiadomość
     * @param cause   obiekt typu throwable
     */
    public ONP_PustyStos(String message, Throwable cause) {
        super(message, cause);
    }
}
