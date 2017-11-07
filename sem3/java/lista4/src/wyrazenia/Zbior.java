package wyrazenia;

/**
 * Klasa reprezentująca zbiór par {nazwa zmiennej, wartość zmiennej}
 */
public class Zbior {
    private Para[] zbiorPar;
    private int iloscPar;
    private int dlugoscZbioru;


    public Zbior() {
        this.dlugoscZbioru = 20;
        this.iloscPar = 0;
        this.zbiorPar = new Para[dlugoscZbioru];
    }

    /**
     * Konstruktor tworzy zbiór o podanej długości
     *
     * @param dlugoscZbioru długość zbioru
     */
    public Zbior(int dlugoscZbioru) {
        this.dlugoscZbioru = dlugoscZbioru;
        this.iloscPar = 0;
        this.zbiorPar = new Para[dlugoscZbioru];
    }

    /**
     * metoda szuka pary z określonym kluczem
     *
     * @param kl klucz
     * @return para o podanym kluczu lub null
     */
    public Para szukaj(String kl) {
        Para p;
        for (int i = 0; i < this.iloscPar; i++) {
            p = zbiorPar[i];
            if (kl.equals(p.klucz)) {
                return p;
            }
        }
        return null;
    }

    /**
     * metoda wstawia do zbioru nową parę
     *
     * @param p para
     */
    public void wstaw(Para p) throws IllegalArgumentException {
        if (this.dlugoscZbioru > this.iloscPar) {
            if (this.szukaj(p.klucz) == null) {
                this.zbiorPar[this.iloscPar] = p;
                this.iloscPar++;
            }
        }

    }

    /**
     * metoda odszukuje parę i zwraca wartość związaną z kluczem
     *
     * @param kl klucz
     */
    public double czytaj(String kl) throws IllegalArgumentException {
        for (int i = 0; i < this.iloscPar; i++) {
            if (kl.equals(zbiorPar[i].klucz)) {
                return zbiorPar[i].getWartosc();
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * metoda wstawia do zbioru nową albo aktualizuje istniejącą parę
     *
     * @param p para, którą wstawiamy do zbioru
     */
    public void ustal(Para p) throws IllegalArgumentException {
        Para r;
        for (int i = 0; i < this.iloscPar; i++) {
            r = zbiorPar[i];
            if (p.klucz.equals(r.klucz)) {
                zbiorPar[i] = p;
                return;
            }
        }
        this.wstaw(p);
    }

    /**
     * metoda podaje ile par jest przechowywanych w zbiorze
     *
     * @return ilość par w zbiorze
     */
    public int ile() {
        return this.iloscPar;
    }

    /**
     * metoda usuwa wszystkie pary ze zbioru
     */
    public void czysc() {
        this.zbiorPar = new Para[dlugoscZbioru];
        this.iloscPar = 0;
    }

}
