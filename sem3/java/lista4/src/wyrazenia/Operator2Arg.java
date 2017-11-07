package wyrazenia;

/**
 * Klasa reprezentująca operator dwuargumentowy
 * Dziedziczy po Operator1Arg
 */
abstract class Operator2Arg extends Operator1Arg {
    protected Wyrazenie prawy;

    /**
     * Konstruktor przyjmuje Wyrazenie
     */
    public Operator2Arg(Wyrazenie l, Wyrazenie p) {
        super(l);
        this.prawy = p;
    }

    /**
     * metoda oblicza wartość wyrażenia
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
        if (o == null || !(o instanceof Operator2Arg)) {
            return false;
        }

        Operator2Arg operator = (Operator2Arg) o;

        if (lewy != null && operator.lewy != null) {
            if (lewy.oblicz() != operator.lewy.oblicz()) {
                return false;
            }
        }

        return prawy != null && operator.prawy != null && prawy.oblicz() == operator.prawy.oblicz();
    }
}
