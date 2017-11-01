package geometria;

public class Trojkat {
    private Punkt a, b, c;

    public Trojkat(Punkt a, Punkt b, Punkt c) {
        if (!a.equals(b)) {
            Prosta prosta = new Prosta(a, b);
            if (!prosta.czyProstaZawieraPunkt(c)) {
                this.a = a;
                this.b = b;
                this.c = c;
            }
        }
    }

    public void przesun(Wektor v) {
        a.przesun(v);
        b.przesun(v);
        c.przesun(v);
    }

    public Punkt getA() {
        return a;
    }

    public Punkt getB() {
        return b;
    }

    public Punkt getC() {
        return c;
    }
}
