package wyrazenia;

/**
 * Klasa reprezentująca wyrażenie odwrotne do podanego wyrażenia
 */
public class Odwrotnosc extends Operator1Arg {
    /**
     * Konstruktor odziedziczony po klasie nadrzędnej
     */
    public Odwrotnosc(Wyrazenie l) {
        super(l);
    }

    /**
     * metoda oblicza wartość wyrażenia
     *
     * @return double wartość funkcji
     */
    @Override
    public double oblicz() throws IllegalArgumentException {
        double res = lewy.oblicz();
        if (res != 0) {
            return 1 / res;
        } else {
            throw new IllegalArgumentException("Koniec świata (Próbujesz dzielić przez 0)");
        }
    }

    /**
     * @return metoda zwraca Wyrazenie w postaci do wypisania na ekran
     */
    @Override
    public String toString() {
        return " 1/(" + lewy + ")";
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
