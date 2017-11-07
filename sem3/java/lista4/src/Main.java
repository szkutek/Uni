import wyrazenia.*;
/*
* W konsoli:
* javadoc -encoding utf8 -docencoding utf8 -charset utf8  wyrazenia
* javac wyrazenia/*.java
* javac Main.java
* java Main
* */

public class Main {
    public static void main(String[] args) {
        Zmienna x = new Zmienna("x", -1.618);

        // 3+5
        Wyrazenie w = new Dodawanie(new Stala(3), new Stala(5));
        System.out.println(w + " = " + w.oblicz());

        // 2+x*7
        w = new Dodawanie(
                new Stala(2),
                new Mnozenie(new Zmienna("x"), new Stala(5)));
        System.out.println(w + " = " + w.oblicz());

        // (3*11-1)/(7+5)
        w = new Dzielenie(
                new Odejmowanie(
                        new Mnozenie(new Stala(3), new Stala(11)),
                        new Stala(1)),
                new Dodawanie(new Stala(7), new Stala(5)));
        System.out.println(w + " = " + w.oblicz());

        // arctan(((x+13)*x)/2)
        w = new Arctan(
                new Dzielenie(
                        new Mnozenie(
                                new Dodawanie(new Zmienna("x"), new Stala(13)),
                                new Zmienna("x")),
                        new Stala(2)));
        System.out.println(w + " = " + w.oblicz());

        // pow(2,5)+x*log(2,y)
        w = new Dodawanie(
                new Potegowanie(new Stala(2), new Stala(5)),
                new Mnozenie(new Zmienna("x"),
                        new Logarytmowanie(new Stala(2), new Zmienna("y", 2))));
        System.out.println(w + " = " + w.oblicz());

    }
}
