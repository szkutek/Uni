package narzedzia;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Klasa modelująca wyrażenie ONP
 */
public class Wyrazenie {

    /**
     * Kolejka symboli wyrażenia ONP (elementy typu Symbol).
     */
    private Kolejka kolejka;
    /**
     * Stos z wynikami pośrednimi obliczeń (elementy typu double).
     */
    private Stos stos;
    /**
     * Lista zmiennych czyli pary klucz-wartość (String-double).
     */
    private Lista zmienne;

    /**
     * Obsługuje logowanie aktywności do pliku.
     */
    public static final Logger loger = Logger.getLogger(Wyrazenie.class.getName());
    private static FileHandler fh;

    static {
        try {
            fh = new FileHandler("calc.log", false);
            loger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            loger.setUseParentHandlers(false);
            loger.info("Uruchomiono KalkulatorONP");
        } catch (IOException | SecurityException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Konstruuje wyrażenie z podanym napisem zawierającym ONP oraz listą
     * zmiennych.
     *
     * @param onp napis zawierający wyrażenie ONP
     * @param zm  lista zmiennych ( pary klucz-wartość)
     * @throws WyjatekONP
     */
    public Wyrazenie(String onp, Lista zm) throws WyjatekONP {
        kolejka = new Kolejka();
        stos = new Stos();
        zmienne = zm;
        String[] tokeny = onp.split("\\s+");
        loger.info("Zainicjalizowano wyrażenie: " + onp);
        for (int i = 0; i < tokeny.length; ++i) {
            if (tokeny[i].matches("\\d+") || tokeny[i].matches("-\\d+")) {
                stos.push(Double.parseDouble(tokeny[i]));
            } else if (tokeny[i].matches("\\p{Alpha}\\p{Alnum}*") && !zawiera(tokeny[i])) {
                stos.push(zmienne.szukaj(tokeny[i]));
            } else if (tokeny[i].toLowerCase().equals("pi") || tokeny[i].toLowerCase().equals("e")) {
                if (tokeny[i].toLowerCase().equals("pi")) {
                    stos.push(new Pi().obliczWartosc());
                } else {
                    stos.push(new E().obliczWartosc());
                }
            } else {
                Funkcja f = zamien(tokeny[i]);
                kolejka.dodajPierwszy(f);
                while (f.brakujaceArgumenty() > 0) {
                    if (!stos.empty()) {
                        double argument = stos.value();
                        f.dodajArgument(argument);
                        stos.pop();
                    } else {
                        throw new ONP_PustyStos();
                    }
                }
                stos.push(f.obliczWartosc());
            }
        }
        assert stos.size() == 1 : "więcej niż 1";
        loger.info("Obliczono wyrażenie " + onp + "  = " + wynik());
    }

    /**
     * Zwraca wynik obliczonego wyrażenia ONP.
     *
     * @return wynik skonstruowanego wyrażenia
     */
    public double wynik() {
        return stos.value();
    }

    /**
     * Funkcja sprawdzająca, czy podany napis należy do zbioru operatorów.
     *
     * @param s napis do sprawdzenia
     * @return <code>true</code> jeśli napis jest jednym z operatorów,
     * <code>false</code> w przeciwnym wypadku
     */
    private boolean zawiera(String s) {
        boolean zawiera = false;

        String[] oper =
                {
                        "+", "-", "*", "/",
                        "abs", "acot", "atan", "ceil", "cos", "e", "exp", "floor",
                        "frac", "ln", "log", "max", "min", "pi", "pow", "sgn", "sin"
                };
        for (int i = 0; i < 21; i++) {
            if (s.equals(oper[i])) {
                zawiera = true;
                break;
            }
        }
        return zawiera;
    }

    /**
     * Zwraca Funkcję odpowiadającą (nazwą i typem) podanemu napisowi.
     *
     * @param s nazwa funkcji
     * @return Obiekt typu Funkcja odpowiadający napisowi
     * @throws ONP_NieznanySymbol jeśli nie istnieje funkcja odpowiadająca
     *                            podanemu napisowi.
     */
    private Funkcja zamien(String s) throws ONP_NieznanySymbol {
        s = s.toLowerCase();
        switch (s) {
            case "+":
                return new Dodaj();
            case "-":
                return new Odejmij();
            case "*":
                return new Mnoz();
            case "/":
                return new Dziel();
            case "abs":
                return new Abs();
            case "acot":
                return new Acot();
            case "atan":
                return new Atan();
            case "ceil":
                return new Ceil();
            case "cos":
                return new Cos();
            case "exp":
                return new Exp();
            case "floor":
                return new Floor();
            case "frac":
                return new Frac();
            case "ln":
                return new Ln();
            case "log":
                return new Lg();
            case "max":
                return new Max();
            case "min":
                return new Min();
            case "pow":
                return new Pow();
            case "sgn":
                return new Sgn();
            case "sin":
                return new Sin();
            default:
                throw new ONP_NieznanySymbol(s);
        }
    }
}
