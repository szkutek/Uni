package geometria;

public class Odcinek {
    private Punkt a, b;

    public Odcinek(Punkt a, Punkt b) {
        if (!(a.equals(b))) {
            this.a = a;
            this.b = b;
        }
    }

    public void przesun(Wektor v) {
        a.przesun(v);
        b.przesun(v);
    }

//    public void obroc(Punkt p, double kat) {
//
//    }
}
