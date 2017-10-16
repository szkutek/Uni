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
        long[] czynniki = new long[100];
        int i = 0;
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

//    private static void wypisz


    public static void main(String[] args) {
        /*
        System.out.println("-1 = " + Arrays.toString(naCzynnikiPierwsze(-1)));
        System.out.println("0 =  " + Arrays.toString(naCzynnikiPierwsze(0)));
        System.out.println("1 = " + Arrays.toString(naCzynnikiPierwsze(1)));
        System.out.println("2: " + czyPierwsza(10));
        System.out.println("2.5: " + czyPierwsza(-10));
        System.out.println("3: " + czyPierwsza(13));
        System.out.println("4: " + czyPierwsza(-103));
        System.out.println("5: " + czyPierwsza(-10342341));
        System.out.println("6: " + czyPierwsza(-23456789));

        System.out.println("7: " + czyPierwsza(-9223372036854775782L));
        System.out.println("8: " + czyPierwsza(9223372036854775783L));
        System.out.println("9: " + czyPierwsza(-9223372036854775808L));

        System.out.println("");
        System.out.println("1: " + Arrays.toString(naCzynnikiPierwsze(-3)));
        System.out.println("2: " + Arrays.toString(naCzynnikiPierwsze(10)));
        System.out.println("2.5: " + Arrays.toString(naCzynnikiPierwsze(-10)));
        System.out.println("3: " + Arrays.toString(naCzynnikiPierwsze(13)));
        System.out.println("4: " + Arrays.toString(naCzynnikiPierwsze(-103)));
        System.out.println("5: " + Arrays.toString(naCzynnikiPierwsze(10342341)));
        System.out.println("5: " + Arrays.toString(naCzynnikiPierwsze(-10342341)));
        System.out.println("6: " + Arrays.toString(naCzynnikiPierwsze(-23456789)));

        System.out.println("7: " + Arrays.toString(naCzynnikiPierwsze(-9223372036854775782L)));
        System.out.println("8: " + Arrays.toString(naCzynnikiPierwsze(9223372036854775783L)));*/
//        System.out.println("9: " + Arrays.toString(naCzynnikiPierwsze(-9223372036854775808L)));
//        */


    }

}