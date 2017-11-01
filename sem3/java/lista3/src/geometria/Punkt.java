package geometria;

public class Punkt {
    private double x, y;

    public Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void przesun(Wektor v) {
        x += v.dx;
        y += v.dy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Punkt)) {
            return false;
        }
        Punkt punkt = (Punkt) obj;

        return this.x == punkt.x && this.y == punkt.y;
    }


}
