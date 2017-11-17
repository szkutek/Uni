import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import narzedzia.*;

/**
 * @author notechus
 */
public class Main {

    public static void main(String[] args) {
        boolean running = true;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        Lista zmienne = new Lista();
        while (running) {
            try {
                command = input.readLine();
                assert command.startsWith("calc") || command.startsWith("clear") || command.startsWith("exit") :
                        "Podałeś/aś nieprawidłową komendę. Dopuszczalne polecenia to: clear (zm), calc "
                                + "wyrazenie (zm=) i exit";
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            if (command.startsWith("calc")) {
                boolean zmienna = false;
                String temp = command.substring(4).trim();
                String nazwa_zm = null;
                if (temp.endsWith("=")) {
                    zmienna = true;
                    int pos = temp.lastIndexOf(" ");
                    nazwa_zm = temp.substring(pos + 1, temp.length() - 1);
                    temp = temp.substring(0, pos);
                }
                Wyrazenie w = null;
                try {
                    w = new Wyrazenie(temp, zmienne);
                    System.out.println(w.wynik());

                } catch (ONP_BledneWyrazenie ex) {
                    Wyrazenie.loger.severe(ex.getMessage());
                    System.out.println(ex.getMessage());
                    continue;
                } catch (ONP_DzieleniePrzez0 ex) {
                    Wyrazenie.loger.severe(ex.getMessage());
                    System.out.println(ex.getMessage());
                    continue;
                } catch (ONP_NieznanySymbol ex) {
                    Wyrazenie.loger.severe(ex.getMessage());
                    System.out.println(ex.getMessage());
                    continue;
                } catch (ONP_PustyStos ex) {
                    Wyrazenie.loger.severe(ex.getMessage());
                    System.out.println(ex.getMessage());
                    continue;
                } catch (Exception ex) {
                    Wyrazenie.loger.severe(ex.getMessage());
                    System.out.println(ex.getMessage());
                    continue;
                }
                if (zmienna) {
                    zmienne.wstaw(new Para(nazwa_zm, w.wynik()));
                    Wyrazenie.loger.info("Zapisano zmienną " + nazwa_zm + " = " + w.wynik());
                }
            } else if (command.startsWith("clear")) {
                String temp = command.substring(5).trim();
                if (temp.equals("")) {
                    zmienne.wyczysc();
                    Wyrazenie.loger.info("Usunięto wszystkie zmienne.");
                } else {
                    try {
                        zmienne.usun(new Para(temp, 0.0));
                        Wyrazenie.loger.info("Usunięto zmienną " + temp);
                    } catch (ONP_NieznanySymbol ex) {
                        System.out.println(ex.getMessage());
                        Wyrazenie.loger.severe(ex.getMessage());
                    }
                }
            } else if (command.startsWith("exit")) {
                running = false;
                Wyrazenie.loger.info("Zamknięto aplikację KalkulatorONP.");
                try {
                    input.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
