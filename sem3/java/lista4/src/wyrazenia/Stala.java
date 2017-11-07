package wyrazenia;

/**
 * Klasa reprezentująca stałą
 */
public class Stala extends Wyrazenie {
    public double wartosc;

    /**
     * @param wartosc wartość stałej
     */
    public Stala(double wartosc) {
        this.wartosc = wartosc;
    }

    /**
     * metoda zwraca wartość stałej
     */
    @Override
    public double oblicz() {
        return wartosc;
    }

    /**
     * @return metoda zwraca wartość stałej w postaci do wypisania
     */
    @Override
    public String toString() {
        return "" + wartosc;
    }

    /**
     * metoda porównuje wartości dwóch wyrażeń
     *
     * @param obj obiekt, z którym chcemy porównać wartość stałej
     * @return true, jeśli wartości wyrażeń są takie same, lub false, jeśli wartości wyrażeń są różne
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Stala)) {
            return false;
        }

        Stala stala = (Stala) obj;

        return stala.wartosc == wartosc;
    }
}