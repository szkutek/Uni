package narzedzia;

/**
 * Klasa modelująca strukturę danych: lista
 */
public class Lista implements Priorytetowy, Cloneable {

    /**
     *
     */
    protected class Element implements Comparable<Element>, Cloneable {

        /**
         * Wartość elementu
         */
        protected Para p;

        /**
         * Referencja do poprzednika i następnika
         */
        protected Element nastepny, poprzedni;

        /**
         * Konstruuje element z zadanymi parametrami
         *
         * @param p         para
         * @param nastepny  referencja do nastepnika
         * @param poprzedni referencja do poprzednika
         */
        public Element(Para p, Element nastepny, Element poprzedni) {
            this.p = p;
            this.nastepny = nastepny;
            this.poprzedni = poprzedni;
        }

        /**
         * Zwraca wynik porównania dwóch elementów typu Element
         *
         * @param o para z którą porównujemy obiekt
         * @return 1 jeśli o jest mniejszy od obiektu, -1 jeśli jest większy, 0
         * gdy są równe
         */
        @Override
        public int compareTo(Element o) {
            return p.compareTo(o.p);
        }

        /**
         * Klonuje element
         *
         * @return kopia elementu
         */
        @Override
        public Element clone() {
            try {
                Element temp = (Element) super.clone();
                if (nastepny != null) {
                    temp.nastepny = nastepny.clone();
                }
                return temp;
            } catch (CloneNotSupportedException ex) {
                return null;
            }
        }

        /**
         * Wstawia parę do listy
         *
         * @param p para do wstawienia
         */
        public void wstaw(Para p) {
            if (poczatek == null) {
                poczatek = new Element(p, null, null);
            } else if (nastepny == null) {
                nastepny = new Element(p, null, poczatek);
            } else {
                nastepny.wstaw(p);
            }
        }

        /**
         * Usuwa dany element
         *
         * @param p element do usunięcia
         * @throws ONP_NieznanySymbol jeśli p nie ma w liście
         */
        public void usun(Para p) throws ONP_NieznanySymbol {
            boolean znaleziono = false;
            if (this.p.equals(p)) {
                znaleziono = true;
                this.p = this.nastepny.p;
                this.nastepny = this.nastepny.nastepny;
            } else {
                this.nastepny.usun(p);
            }
            if (!znaleziono) {
                throw new ONP_NieznanySymbol();
            }
        }

        /**
         * Tworzy napis zawierający wszystkie elementy listy
         *
         * @return napis z elementami kolejki
         */
        @Override
        public String toString() {
            String wynik = "[";
            Element temp = this;
            wynik += temp.p;
            temp = temp.nastepny;
            while (temp != null) {
                wynik += ", " + temp.p;
                temp = temp.nastepny;
            }
            return wynik + "]";
        }
    }

    /**
     * Referencja do początkowego elementu listy
     */
    protected Element poczatek;

    /**
     * Rozmiar listy
     */
    protected int rozmiar;

    /**
     * Konstruuje pustą listę
     */
    public Lista() {
        poczatek = null;
        rozmiar = 0;
    }

    /**
     * Konstruuje listę zawierającą jeden element
     *
     * @param p element, który znajdzie się w liście
     */
    public Lista(Para p) {
        this.poczatek = new Element(p, null, null);
        rozmiar = 1;
    }

    /**
     * Wstawia parę do listy
     *
     * @param p para do wstawienia
     */
    @Override
    public void wstaw(Para p) {
        if (poczatek == null) {
            poczatek = new Element(p, null, null);
        } else {
            poczatek.wstaw(p);
        }
        rozmiar++;
    }

    /**
     * Usuwa element z listy
     *
     * @param p para do usunięcia
     * @throws ONP_NieznanySymbol jeśli nie ma takiego elementu w liście
     */
    public void usun(Para p) throws ONP_NieznanySymbol {
        if (poczatek == null) {
        } else if (poczatek.nastepny == null) {
            poczatek = null;
        } else {
            poczatek.usun(p);
        }
    }

    /**
     * Usuwa wszystkie elementy listy
     */
    public void wyczysc() {
        poczatek = null;
        rozmiar = 0;
    }

    /**
     * Wyszukuje parę o podanym kluczu w liście
     *
     * @param klucz klucz, którego poszukujemy
     * @return wartość pary o zadanym kluczu
     * @throws ONP_NieznanySymbol jeśli nie istnieje element o podanym kluczu
     */
    double szukaj(String klucz) throws ONP_NieznanySymbol {
        double wynik = 0.0;
        Element temp = poczatek;
        int pos = 0;
        while (temp != null) {
            if (temp.p.klucz.equals(klucz)) {
                wynik = temp.p.czytaj();
            }
            pos++;
            temp = temp.nastepny;
        }
        if (pos == 0 || pos > rozmiar) {
            throw new ONP_NieznanySymbol("Nie istnieje zmienna: " + klucz);
        }
        return wynik;
    }

    /**
     * Zwraca rozmiar listy
     *
     * @return rozmiar listy
     */
    @Override
    public int rozmiar() {
        return rozmiar;
    }

    /**
     * Tworzy napis zawierający wszystkie elementy listy
     *
     * @return napis z elementami kolejki
     */
    @Override
    public String toString() {
        return poczatek.toString();
    }

    @Override
    public Lista clone() {
        try {
            Lista temp = (Lista) super.clone();
            if (poczatek != null) {
                temp.poczatek = poczatek.clone();
            }
            return temp;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
