package narzedzia;

public class ONP_DzieleniePrzez0 extends WyjatekONP {

    /**
     * Konstruktor tworzy wyjątek z domyślną wiadomością
     */
    public ONP_DzieleniePrzez0() {
        super("Próbujesz dzielić przez 0.");
    }

    /**
     * Konstruktor tworzy wyjątek z podaną wiadomością
     *
     * @param message podana wiadomość
     */
    public ONP_DzieleniePrzez0(String message) {
        super(message);
    }

    /**
     * Konstruuje wyjątek z obiektem typu Throwable
     *
     * @param cause obiekt typu Throwable
     */
    public ONP_DzieleniePrzez0(Throwable cause) {
        super("Próbujesz dzielić przez 0.", cause);
    }

    /**
     * Konstruuje wyjątek z podaną wiadomością i obiektem typu throwable
     *
     * @param message wiadomość
     * @param cause   obiekt typu throwable
     */
    public ONP_DzieleniePrzez0(String message, Throwable cause) {
        super(message, cause);
    }
}
