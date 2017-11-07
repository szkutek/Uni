package wyrazenia;


public abstract class Wyrazenie {

    public abstract double oblicz();

    /**
     * metoda sumująca wyrażenia
     *
     * @param wyr lista wyrażeń do sumowania
     * @return suma wyrażeń
     */
    public static double sumuj(Wyrazenie... wyr) {
        double res = 0.0;
        for (Wyrazenie w : wyr) {
            res += w.oblicz();
        }
        return res;
    }

    /**
     * metoda mnożąca wyrażenia
     *
     * @param wyr lista wyrażeń do pomnożenia
     * @return iloczyn wyrażeń
     */
    public static double pomnoz(Wyrazenie... wyr) {
        double res = 0.0;
        for (Wyrazenie w : wyr) {
            res *= w.oblicz();
        }
        return res;
    }
}
