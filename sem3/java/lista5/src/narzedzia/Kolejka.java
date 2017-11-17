package narzedzia;

/**
 * Klasa modelująca strukturę danych: kolejka
 */
public class Kolejka {

    protected Elem pierwszy, ostatni;
    protected int rozmiar;

    protected class Elem {
        /**
         * Wartość elementu
         */
        private Symbol wartosc;

        /**
         * Referencja do poprzednika i następnika
         */
        private Elem poprz, nast;

        /**
         * Konstruuje element z podanymi parametrami
         */
        public Elem(Symbol x, Elem poprz, Elem nast) {
            this.wartosc = x;
            this.poprz = poprz;
            this.nast = nast;
        }
    }

    /**
     * Konstruktor tworzy pustą kolejkę
     */
    public Kolejka() {
        pierwszy = ostatni = null;
        rozmiar = 0;
    }

    /**
     * Konstruktor tworzy kolejkę zawierającą jeden element
     */
    public Kolejka(Symbol x) {
        this.pierwszy = new Elem(x, null, null);
        ostatni = pierwszy.nast;
        rozmiar = 1;
    }

    /**
     * Dodaje element do kolejki
     *
     * @param x element, który ma zostać dodany
     */
    public void dodajPierwszy(Symbol x) {
        if (pierwszy == null) {
            pierwszy = new Elem(x, null, null);
            ostatni = pierwszy;
        } else {
            ostatni.nast = new Elem(x, ostatni, null);
            ostatni = ostatni.nast;
        }
        rozmiar += 1;
    }

    /**
     * Usuwa pierwszy element z kolejki
     */
    public void usunPierwszy() {
        pierwszy = pierwszy.nast;
        rozmiar -= 1;
    }

    /**
     * Zwraca pierwszy element kolejki
     *
     * @return wartość pierwszego elementu kolejki
     */
    public Symbol getPierwszy() {
        return pierwszy.wartosc;
    }

    /**
     * Zwraca rozmiar kolejki
     *
     * @return rozmiar kolejki
     */
    public int getRozmiar() {
        return rozmiar;
    }

    /**
     * Usuwa wszystkie elementy z kolejki
     */
    public void wyczysc() {
        pierwszy = null;
    }

    /**
     * Tworzy napis zawierający wszystkie elementy kolejki
     *
     * @return napis z elementami kolejki
     */
    public String toString() {
        String wynik = "" + pierwszy.wartosc;

        for (Elem i = pierwszy.nast; i != null; i = i.nast) {
            wynik += " <- " + i.wartosc;
        }
        return wynik;
    }
}
