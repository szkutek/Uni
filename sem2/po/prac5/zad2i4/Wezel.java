package zad2;

public abstract class Wezel implements Wyrazenie {
    protected Wyrazenie lewy;
    protected Wyrazenie prawy;

    public Wezel(Wyrazenie l, Wyrazenie p) {
        lewy = l;
        prawy = p;
    }

    @Override
    public abstract int oblicz();

    @Override
    public abstract Wyrazenie pochodna();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Wezel wezel = (Wezel) o;

        if (lewy != null ? !lewy.equals(wezel.lewy) : wezel.lewy != null) {
            return false;
        }
        return prawy != null ? prawy.equals(wezel.prawy) : wezel.prawy == null;
    }

    @Override
    public int hashCode() {
        int result = lewy != null ? lewy.hashCode() : 0;
        result = 31 * result + (prawy != null ? prawy.hashCode() : 0);
        return result;
    }

}
