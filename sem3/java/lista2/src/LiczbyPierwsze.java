public final class LiczbyPierwsze {
    private final static int POTEGA2 = 21;
    private final static int[] SITO = new int[1 << POTEGA2]; // 2097152

    //  blok inicjalizacyjny dla sita
    static {
        int sqrtSITO = (int) Math.sqrt(SITO.length);
        SITO[0] = 0;
        SITO[1] = 1;
        for (int i = 2; i < sqrtSITO; i++) {
            if (SITO[i] == 0) {
                SITO[i] = i;
                for (int j = i * i; j < SITO.length; j += i) {
                    if (SITO[j] == 0) {
                        SITO[j] = i;
                    }
                }
            }
        }
        for (int i = sqrtSITO; i < SITO.length; i++) {
            if (SITO[i] == 0) {
                SITO[i] = i;
            }
        }
    }


    private LiczbyPierwsze() {
    }

    public static boolean czyPierwsza(long x) {
        if (x == -1 || x == 0 || x == 1 || x == Long.MIN_VALUE) {
            return false;
        }
        if (x < 0) {
            x = -x;
        }

        if (x < SITO.length) {
            return SITO[(int) x] == x;
        }

        if (x % 2 == 0) {
            return false;
        }
        long sqrtX = (long) Math.sqrt(x);
        for (long i = 3; i <= sqrtX; i += 2) {
            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static long[] naCzynnikiPierwsze(long x) {
        long[] czynniki = new long[64];
        int i = 0;

        if (x == 1) {
            czynniki[i] = 1;
            return czynniki;
        }

        if (x < 0) {
            czynniki[i] = (long) -1;
            i++;
            if (x % 2 == 0) {
                czynniki[i] = 2;
                i++;
                x /= 2;
            }
            x = -x;
        }
//        if (czyPierwsza(x)) {
//            czynniki[i] = x;
//            return czynniki;
//        }

        long sqrtX = (long) (Math.sqrt(x) + 1);
        for (long k = 2; k < sqrtX && x >= SITO.length; k++) {
            while (x % k == 0) {
                czynniki[i] = k;
                i++;
                x /= k;
            }
        }

        if (x >= SITO.length) {
            czynniki[i] = x;
            return czynniki;
        }

        while (x > 1) {
            czynniki[i] = (long) SITO[(int) x];
            i++;
            x /= SITO[(int) x];
        }
        return czynniki;

    }

    private static void wypiszCzynnikiPierwsze(long x) {
        long[] czynniki = naCzynnikiPierwsze(x);

        System.out.print(x + " = " + czynniki[0]);

        for (int i = 1; i < czynniki.length; i++) {
            if (czynniki[i] == 0) {
                break;
            }
            System.out.print("*");
            System.out.print(czynniki[i]);
        }
        System.out.println();

    }


    public static void main(String[] args) {
/*
        wypiszCzynnikiPierwsze(-1);
        wypiszCzynnikiPierwsze(0);
        wypiszCzynnikiPierwsze(1);
        wypiszCzynnikiPierwsze(10);
        wypiszCzynnikiPierwsze(-10);
        wypiszCzynnikiPierwsze(13);
        wypiszCzynnikiPierwsze(-103);
        wypiszCzynnikiPierwsze(-10342341);
        wypiszCzynnikiPierwsze(-23456789);
        wypiszCzynnikiPierwsze(4611686014132420609L);
        wypiszCzynnikiPierwsze(-9223372036854775782L);
        wypiszCzynnikiPierwsze(9223372036854775783L);
        wypiszCzynnikiPierwsze(-9223372036854775808L);
//        -1 0 1 10 -10 13 -103 10342341 -23456789 4611686014132420609L 9223372036854775782L 9223372036854775783L -9223372036854775808L
*/
        if (args.length < 1) {
            System.out.println("Podaj jedną lub więcej liczb oddzielonych spacjami. " +
                    "Liczby muszą być całkowite, nieprzekraczające typu long.");
            System.exit(0);
        }

        for (String arg : args) {
            long x = Long.valueOf(arg);
            wypiszCzynnikiPierwsze(x);
        }
    }

}
