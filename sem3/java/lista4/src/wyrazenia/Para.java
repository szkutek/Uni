package wyrazenia;

/**
 * klasa reprezentująca parę {klucz, wartość}
 */
public class Para {
    public final String klucz;
    private double wartosc;

    /**
     * @param klucz   klucz pary
     * @param wartosc wartość pary
     */
    public Para(String klucz, double wartosc) {
        this.klucz = klucz;
        this.wartosc = wartosc;
    }

    /**
     * metoda zwraca wartość pary
     *
     * @return wartość pary
     */
    public double getWartosc() {
        return wartosc;
    }

    /**
     * metoda ustawia wartość pary
     *
     * @param wartosc ustaw daną wartość jako wartość pary
     */
    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    /**
     * metoda zwraca parę w postaci do wypisania na ekran
     *
     * @return para w postaci do wypisania na ekran
     */
    @Override
    public String toString() {
        return klucz + " = " + wartosc;
    }

    /**
     * metoda porównuje parę z podaną parą
     *
     * @param obj para, z którą się porównujemy
     * @return true, jeśli klucze par są takie same, lub false, jeśli klucze są różne
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Para)) {
            return false;
        }
        Para p = (Para) obj;

//        return this.wartosc == p.getWartosc();
        return this.klucz.equals(p.klucz);
    }
}
