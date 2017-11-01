package geometria;

public class Prosta {
    public final double a, b, c; // Ax + By + C = 0
    // final

    public Prosta(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Prosta(Punkt p1, Punkt p2) { // p1 i p2 są różne!
//        if (!(p1.equals(p2))) {
        this.a = p1.getY() - p2.getY();
        this.b = p2.getX() - p1.getX();
        this.c = p1.getX() * p2.getY() - p2.getX() * p1.getY();

    }

    public boolean czyProstaZawieraPunkt(Punkt p) {
        return a * p.getX() + b * p.getY() + c == 0;
    }

    public static Prosta przesunOWektor(Prosta p, Wektor w) {
        double c = p.c;
        c += w.dy - p.a * w.dx;
        return new Prosta(p.a, p.b, c);
    }

    public static boolean czyRownolegle(Prosta p1, Prosta p2) {
        return p1.a * p2.b == p2.a * p1.b;
    }

    public static boolean czyProstopadle(Prosta p1, Prosta p2) {
        return p1.a * p2.a == -p1.b * p2.b;
    }

    public static Punkt punktPrzeciecia(Prosta p1, Prosta p2) throws Exception {
        if (czyRownolegle(p1, p2)) {
            throw new Exception("Proste równoległe się nie przecinają!");
        }
        double ww = p1.a * p2.b - p2.a * p1.b;
        double wx = p1.b * p2.c - p2.b * p1.c;
        double wy = p1.c * p2.a - p2.c * p1.a;
        return new Punkt(wx / ww, wy / ww);
    }

    public static double odlPktOdProstej(Punkt q, Prosta p) {
        return Math.abs(p.a * q.getX() + p.b * q.getY() + p.c) / Math.sqrt(p.a * p.a + p.b * p.b);
    }

}

