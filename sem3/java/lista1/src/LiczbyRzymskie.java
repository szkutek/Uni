public class LiczbyRzymskie {

    // tablica z wybranymi liczbami rzymskimi
    private static String[] rzymskie = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };
    // tablica z wybranymi liczbami arabskimi
    private static int[] arabskie = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };

    public static void main(String[] args) throws Exception {
        for (String arg : args) {
            int x = Integer.valueOf(arg);
            if (x <= 0 || x >= 5000) {
                throw new IllegalArgumentException("liczba " + x + " spoza zakresu 1-4999");
            }
            String wynik = konwertuj(x);
            System.out.println(x + " " + wynik);
        }

    }

    private static String konwertuj(int x) {
        String wynik = "";
        int i = 0;
        while (x > 0) {
            if (x >= arabskie[i]) {
                wynik += rzymskie[i];
                x -= arabskie[i];
            } else {
                i++;
            }

        }
        return wynik;
    }
}
