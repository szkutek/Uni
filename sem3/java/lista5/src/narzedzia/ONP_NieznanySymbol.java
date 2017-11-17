package narzedzia;

public class ONP_NieznanySymbol extends WyjatekONP {
    /**
     * Konstruktor tworzy wyjątek z domyślną wiadomością
     */
    public ONP_NieznanySymbol() {
        super("Podałeś nieznany symbol.");
    }

    /**
     * Konstruktor tworzy wyjątek z podaną wiadomością
     *
     * @param message
     */
    public ONP_NieznanySymbol(String message) {
        super(message);
    }

    /**
     * Konstruuje wyjątek z obiektem typu Throwable
     *
     * @param cause obiekt typu Throwable
     */
    public ONP_NieznanySymbol(Throwable cause) {
        super("Podałeś nieznany symbol.", cause);
    }

    /**
     * Konstruuje wyjątek z podaną wiadomością i obiektem typu throwable
     *
     * @param message wiadomość
     * @param cause   obiekt typu throwable
     */
    public ONP_NieznanySymbol(String message, Throwable cause) {
        super(message, cause);
    }
}
