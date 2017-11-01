package geometria;

public class Wektor {
    public final double dx, dy;

    public Wektor(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Wektor dodajWektor(Wektor w1, Wektor w2) {
        return new Wektor(w1.dx + w2.dx, w1.dy + w2.dy);
    }
}
