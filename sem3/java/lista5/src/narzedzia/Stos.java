package narzedzia;

/**
 * Klasa modelująca strukturę danych: stos
 */
public class Stos {

    /**
     * Referencje do pierwszego i ostatniego elementu
     */
    private Element pierwszy, ostatni;
    /**
     * Rozmiar stosu
     */
    private int rozmiar;

    /**
     * Wewnętrzna klasa zarządzająca pojedyńczym węzłem stosu
     */
    protected class Element {

        /**
         * Wartość elementu
         */
        private double wartosc;
        /**
         * Referencja do poprzednika i następnika
         */
        private Element poprzedni, nastepny;

        /**
         * Konstruuje element z podanymi parametrami
         *
         * @param x     wartość
         * @param poprz referencja do poprzednika
         * @param nast  referencja do następnika
         */
        public Element(double x, Element poprz, Element nast) {
            wartosc = x;
            poprzedni = poprz;
            nastepny = nast;
        }
    }

    /**
     * Konstruuje pusty stos
     */
    public Stos() {
        pierwszy = ostatni = null;
        rozmiar = 0;
    }

    /**
     * Konstruuje stos zawierający jeden element
     *
     * @param x wartość elementu
     */
    public Stos(double x) {
        pierwszy = new Element(x, null, null);
        ostatni = pierwszy.nastepny;
        rozmiar = 1;
        //stos = new double[rozmiar];
    }

    /**
     * Dodaje element na szczyt stosu
     *
     * @param x wartość dodawanego elementu
     */
    public void push(double x) {
        pierwszy = new Element(x, null, pierwszy);
        ++rozmiar;
    }

    /**
     * Usuwa element ze szczytu stosu
     */
    public void pop() {
        pierwszy = pierwszy.nastepny;
        --rozmiar;
    }

    /**
     * Sprawdza czy stos jest pusty
     *
     * @return true jeśli stos jest pusty, false w przeciwnym przypadku
     */
    public boolean empty() {
        return rozmiar == 0;
    }

    /**
     * Usuwa wszystkie elementy ze stosu
     */
    public void wyczysc() {
        pierwszy = null;
    }

    /**
     * Zwraca rozmiar stosu
     *
     * @return rozmiar stosu
     */
    public int size() {
        return rozmiar;
    }

    /**
     * Zwraca wartość pierwszego elementu stosu
     *
     * @return wartość elementu
     */
    public double value() {
        return pierwszy.wartosc;
    }

    /**
     * Tworzy napis zawierający wszystkie elementy stosu
     *
     * @return napis z elementami kolejki
     */
    @Override
    public String toString() {
        String wynik = "" + pierwszy.wartosc;
        for (Element i = pierwszy.nastepny; i != null; i = i.nastepny) {
            wynik += "\n" + i.wartosc;
        }
        return wynik;
    }
}
