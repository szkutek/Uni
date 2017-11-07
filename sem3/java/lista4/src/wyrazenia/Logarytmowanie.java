package wyrazenia;

/**
 * Klasa reprezentująca funkcję logarytmowania
 * pierwsze wyrażenie jest podstawą logarytmu
 * drugie wyrażenie jest wyrażeniem logarytmowanym
 */
public class Logarytmowanie extends Operator2Arg {
    /**
     * Konstruktor odziedziczony po klasie nadrzędnej
     */
    public Logarytmowanie(Wyrazenie l, Wyrazenie p) {
        super(l, p);
    }

    /**
     * metoda oblicza wartość wyrażenia
     *
     * @return double wartość funkcji
     */
    @Override
    public double oblicz() throws IllegalArgumentException {
        double a = lewy.oblicz();
        double b = prawy.oblicz();
        if (a > 0 && a != 1 && b > 0) {
            return Math.log(b) / Math.log(a);
        } else {
            throw new IllegalArgumentException("Podstawa log musi być różna od 1 i większa od 0, " +
                    "a liczba logarytmowana musi być większa od 0");
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
        return "log_" + lewy + "_" + prawy;
    }
}
