package wyrazenia;

/**
 * Klasa reprezentująca funkcję mnożenia dwóch wyrażeń
 */
public class Mnozenie extends Operator2Arg {
    /**
     * Konstruktor odziedziczony po klasie nadrzędnej
     */
    public Mnozenie(Wyrazenie l, Wyrazenie p) {
        super(l, p);
    }

    /**
     * metoda oblicza wartość wyrażenia
     *
     * @return double wartość funkcji
     */
    @Override
    public double oblicz() {
        return lewy.oblicz() * prawy.oblicz();
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
        return lewy + "*" + prawy;
    }
}
