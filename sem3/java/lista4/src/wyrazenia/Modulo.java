package wyrazenia;

/**
 * Klasa reprezentująca funkcję reszty z dzielenia
 */
public class Modulo extends Operator2Arg {
    /**
     * Konstruktor odziedziczony po klasie nadrzędnej
     */
    public Modulo(Wyrazenie l, Wyrazenie p) {
        super(l, p);
    }

    /**
     * metoda oblicza wartość wyrażenia
     *
     * @return double wartość funkcji
     */
    @Override
    public double oblicz() throws IllegalArgumentException {
        double res = prawy.oblicz();
        if (res != 0) {
            return lewy.oblicz() % res;
        } else {
            throw new IllegalArgumentException("Mod 0 jest niezdefiniowane.");
        }
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

    /**
     * @return metoda zwraca Wyrazenie w postaci do wypisania na ekran
     */
    @Override
    public String toString() {
        return lewy + " mod " + prawy;
    }
}
