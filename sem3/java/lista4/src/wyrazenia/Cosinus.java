package wyrazenia;

/**
 * Klasa reprezentująca funkcję cosinus
 */
public class Cosinus extends Operator1Arg {
    /**
     * Konstruktor odziedziczony po klasie nadrzędnej
     */
    public Cosinus(Wyrazenie l) {
        super(l);
    }

    /**
     * metoda wraca wartość funkcji cosinus z wyrażenia
     *
     * @return double wartość funkcji
     */
    @Override
    public double oblicz() {
        return Math.cos(lewy.oblicz());
    }

    /**
     * @return metoda zwraca Wyrazenie w postaci do wypisania na ekran
     */
    @Override
    public String toString() {
        return " cos(" + lewy + ")";
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
