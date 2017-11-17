package narzedzia;

public class Dodaj extends Funkcja2Arg {
    /**
     * Oblicza wartość
     *
     * @return wartość wyrażenia
     * @throws WyjatekONP jeśli dodanych zostanie zbyt wiele argumentów
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return arg1.obliczWartosc() + arg2.obliczWartosc();
    }
}
