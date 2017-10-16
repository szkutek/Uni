import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

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

        int sqrtX = (int) Math.sqrt(x);
        for (int i = 2; i <= sqrtX && i < SITO.length; i++) {
            if (SITO[i] == i && x % i == 0) {
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
            if (x == Long.MIN_VALUE) {
                czynniki[i] = 2;
                i++;
                x /= 2;
            }
            x = -x;
        }

        if (czyPierwsza(x)) {
            czynniki[i] = x;
            return czynniki;
        }

        for (int k = 2; k < SITO.length && x >= SITO.length; k++) {
            if (SITO[k] == k) {
                while (x % k == 0) {
                    czynniki[i] = (long) k;
                    i++;
                    x /= k;
                }
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
        System.out.println("");

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
        wypiszCzynnikiPierwsze(-9223372036854775782L);
        wypiszCzynnikiPierwsze(9223372036854775783L);
        wypiszCzynnikiPierwsze(-9223372036854775808L);
*/
       /* TODO "Jeśli program wywołano bez żadnego parametru, to należy wypisać na standardowym strumieniu
              dla błędów System.err instrukcję obsługi programu."*/
        for (String arg : args) {
            long x = Integer.valueOf(arg);
            wypiszCzynnikiPierwsze(x);
        }

    }

}