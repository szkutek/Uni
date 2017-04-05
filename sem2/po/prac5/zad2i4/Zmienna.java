package zad2;

public class Zmienna extends Lisc {
    String nazwa;

    public Zmienna(String nazwa) {
        this.nazwa = nazwa;
//        super(zmienne.get(nazwa));
    }

    @Override
    public int oblicz() {
        return zmienne.get(nazwa);
    }

    @Override
    public Wyrazenie pochodna() {
        return new Stala(1);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    @Override
    public String toString() {
//        return "Zmienna{" + nazwa + "}";
        return " " + nazwa + " ";
    }
}

