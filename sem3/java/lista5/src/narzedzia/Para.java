package narzedzia;

/**
 * Klasa modelująca parę klucz-wartość
 */
public class Para implements Comparable<Para>, Cloneable {

    /**
     * Klucz pary
     */
    public final String klucz;
    /**
     * Wartośc pary
     */
    private double wartosc;

    /**
     * Konstruuje parę z pustym kluczem i wartością 0.0
     */
    public Para() {
        klucz = "";
        wartosc = 0.0;
    }

    /**
     * Konstruuje parę z podanym kluczem i wartością
     *
     * @param klucz   klucz pary
     * @param wartosc wartość pary
     */
    public Para(String klucz, double wartosc) {
        this.klucz = klucz;
        this.wartosc = wartosc;
    }

    /**
     * Zwraca wartość pary
     *
     * @return wartość pary
     */
    public double czytaj() {
        return wartosc;
    }

    /**
     * Nadpisuje wartość pary
     *
     * @param wart wartość którą chcemy wstawić
     */
    public void zapisz(double wart) {
        wartosc = wart;
    }

    /**
     * Porównuje dwie pary według klucza
     *
     * @param o Druga para
     * @return -1,0,1 w zależności od relacji między parami
     */
    @Override
    public int compareTo(Para o) {
        return Double.compare(wartosc, o.wartosc);
    }

    /**
     * Klonuje parę
     *
     * @return kopia pary
     */
    @Override
    public Para clone() {
        return new Para(klucz, wartosc);
    }

    /**
     * Testuje równość par
     *
     * @param o druga para do porównania
     * @return true jeśli pary są równe, false w przeciwnym przypadku
     */
    @Override
    public boolean equals(Object o) {
        Para p = (Para) o;
        return klucz.equals(p.klucz);
    }

    /**
     * Zwraca napis z parą ('klucz', wartość)
     *
     * @return
     */
    @Override
    public String toString() {
        return "('" + klucz + "'," + wartosc + ")";
    }
}

