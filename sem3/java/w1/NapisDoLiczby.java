/*
  Copyright (c) October 2010 by Paweł Rzechonek
  Program przekształca wszystkie argumenty z jakimi został wywołany na liczby
  całkowite, sumuje je i wynik wypisuje na standardowe wyjście.
  Istotne elementy w programie:
    * wykorzystanie klasy opakowującej Integer do wykonania konwersji.
*/

public class NapisDoLiczby
{
    public static void main (String[] args) throws Exception
    {
        int suma = 0;
        for (int i=0; i<args.length; i++)
            suma += new Integer(args[i]); // obiekt Integer przechowuje liczbę typu int
                                          // można go zainicjować napisem zawierającym liczbę całkowitą
        System.out.println(suma);
    }
}

