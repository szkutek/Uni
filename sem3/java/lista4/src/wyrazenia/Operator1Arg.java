package wyrazenia;

/**
 * Klasa reprezentująca operator jednoargumentowy
 * Dziedziczy po abstrakcyjnej klasie Wyrazenie
 */
public abstract class Operator1Arg extends Wyrazenie {
    /**
     * Wyrazenie
     */
    protected Wyrazenie lewy;

    /**
     * @param l Wyrazenie
     */
    public Operator1Arg(Wyrazenie l) {
        this.lewy = l;
    }

    /**
     * metoda oblicza wartość wyrażenia
     *
     * @return wartość funkcji
     */
    @Override
    public abstract double oblicz();

    /**
     * metoda porównuje wartości dwóch wyrażeń
     *
     * @param o obiekt, z którym chcemy porównać wyrażenie
     * @return true, jeśli wartości wyrażeń są takie same, lub false, jeśli wartości wyrażeń są różne
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Operator1Arg)) {
            return false;
        }

        Operator1Arg operator = (Operator1Arg) o;

        if (lewy != null && operator.lewy != null) {
            if (lewy.oblicz() == operator.lewy.oblicz()) {
                return true;
            }
        }
        return false;
    }
}
