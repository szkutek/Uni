package wyrazenia;

/**
 * Klasa reprezentująca wyrażenie przeciwne do podanego wyrażenia
 */
public class Przeciwienstwo extends Operator1Arg {
    /**
     * Konstruktor odziedziczony po klasie nadrzędnej
     */
    public Przeciwienstwo(Wyrazenie l) {
        super(l);
    }

    /**
     * metoda oblicza wartość wyrażenia
     *
     * @return double wartość funkcji
     */
    @Override
    public double oblicz() {
        return -lewy.oblicz();
    }

    /**
     * @return metoda zwraca Wyrazenie w postaci do wypisania na ekran
     */
    @Override
    public String toString() {
        return " (-" + lewy + ")";
    }

    /**
     * metoda porównuje wartości dwóch wyrażeń
     *
     * @param o obiekt, z którym chcemy porównać wyrażenie
     * @return true, jeśli wartości wyrażeń są takie same, lub false, jeśli wartości wyrażeń są różne
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
