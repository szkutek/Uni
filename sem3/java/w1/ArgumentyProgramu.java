/*
  Copyright (c) October 2010 by Paweł Rzechonek
  Program wypisuje na standardowym wyjściu wszystkie argumenty z jakimi został
  wywołany.
  Istotne elementy w programie:
    * wykorzystanie argumentu metody main(String[]);
    * operacja konkatenacji łańcuców znakowych z innymi typami danych;
    * użycie pola length z tablicy napisów.
*/

public class ArgumentyProgramu
{
    public static void main (String[] args)
    {
        for (int i=0; i<args.length; i++)
            System.out.println("args["+i+"] = "+args[i]); // składanie napisu za pomocą konkatenacji
    }
}

