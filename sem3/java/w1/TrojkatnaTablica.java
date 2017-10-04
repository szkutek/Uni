/*
  Copyright (c) October 2010 by Paweł Rzechonek
  Program tworzy tablicę trójkątną int[][], wypełnia ją losowymi liczbami
  całkowitymi i wypisuje na standardowe wyjście.
  Istotne elementy w programie:
    * utworzenie tablicy trójkątnej;
    * losowanie liczby rzeczywistej z przedziału [0,1) metodą Math.random().
*/

public class TrojkatnaTablica
{
    public static void main (String[] args)
    {
        // deklaracja tablicy 2D
        int tab[][];
        // utworzenie tablicy głównej o losowej długości
        tab = new int[(int)(Math.random()*10)][];
        // utworzenie podtablic różnych rozmiarów
        for (int i=0; i<tab.length; i++)
            tab[i] = new int[i];
        // wypełnienie tablicy 2D losowymi wartościami
        for (int i=0; i<tab.length; i++)
            for (int j=0; j<tab[i].length; j++)
                tab[i][j] = (int)(Math.random()*90+10);
        // wypisanie tablicy 2D
        for (int i=0; i<tab.length; i++)
        {
            for (int j=0; j<tab[i].length; j++)
                System.out.print(" "+tab[i][j]);
            System.out.println();
        }
    }
}

