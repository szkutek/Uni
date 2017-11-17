package narzedzia;

public abstract class Funkcja2Arg extends Funkcja1Arg {
    /**
     * Drugi argument funkcji
     */
    protected Operand arg2;

    /**
     * Konstruktor tworzący funkcję bez argumentów
     */
    public Funkcja2Arg() {
        super();
    }

    /**
     * Zwraca ilość argumentów potrzebnych do obliczenia funkcji
     *
     * @return 1
     */
    @Override
    public int arnosc() {
        return 2;
    }

    /**
     * Zwraca ilość brakujących argumentów
     *
     * @return ilość brakujących argumentów
     */
    @Override
    public int brakujaceArgumenty() {
        return arnosc() - ilosc_arg;
    }

    /**
     * Dodaje argument do funkcji
     *
     * @param a argument
     * @throws WyjatekONP jeśli dodanych zostanie zbyt wiele argumentów
     */
    @Override
    public void dodajArgument(double a) throws WyjatekONP {
        if (brakujaceArgumenty() == 2) {
            arg2 = new Liczba(a);
            ilosc_arg += 1;
        } else if (brakujaceArgumenty() == 1) {
            arg1 = new Liczba(a);
            ilosc_arg += 1;
        } else if (brakujaceArgumenty() == 0) {
            throw new WyjatekONP("Podałeś za dużo argumentów do funkcji.");
        }
    }

    /**
     * Oblicza wartość funkcji
     *
     * @return wartość funkcji
     * @throws WyjatekONP jeśli podane zostały złe argumenty
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return 0;
    }
}
