package wyrazenia;

/**
 * Klasa reprezentująca funkcję arcus tangens
 */
public class Arctan extends Operator1Arg {
    /**
     * Konstruktor odziedziczony po klasie nadrzędnej
     *
     * @param l Wyrazenie
     */
    public Arctan(Wyrazenie l) {
        super(l);
    }

    /**
     * metoda wraca wartość funkcji arcus tangens z wyrażenia
     *
     * @return double wartość funkcji
     */
    @Override
    public double oblicz() {
        return Math.atan(lewy.oblicz());
    }

    /**
     * metoda zwraca Wyrazenie w postaci do wypisania na ekran
     *
     * @return wyrażenie w postaci do wypisania na ekran
     */
    @Override
    public String toString() {
        return "arctan(" + lewy + ")";
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
