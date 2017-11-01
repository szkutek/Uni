import geometria.*;

public class Main {
    public static void main(String[] args) {

        Punkt p1 = new Punkt(1.0, 1.0);
        Punkt p2 = new Punkt(3.0, 3.0);
        Punkt p3 = new Punkt(1.0, 0.0);
        Odcinek odc1 = new Odcinek(p1, p2);
        Trojkat t1 = new Trojkat(p1, p2, p3);


        Wektor v = new Wektor(1.0, -1.0);
        Wektor w = new Wektor(2.0, 2.0);
        Prosta pr1 = new Prosta(2.0, 1.0, 1.0);
        Prosta pr2 = new Prosta(-0.5, 1.0, 0.0);
        Prosta pr3 = new Prosta(1.0, 1.0, 1.0);
        Prosta pr4 = new Prosta(2.0, 1.0, -10.0);

        System.out.println("Równoległość prostych. Poprawne odpowiedzi: false, true.");
        System.out.println(Prosta.czyRownolegle(pr1, pr2));
        System.out.println(Prosta.czyRownolegle(pr1, pr4));
        System.out.println("Prostopadłość prostych. Poprawne odpowiedzi: true, false.");
        System.out.println(Prosta.czyProstopadle(pr1, pr2));
        System.out.println(Prosta.czyProstopadle(pr1, pr3));


        System.out.println("Punkt przecięcia prostych. Poprawny wynik: -0.4, 0.2");
        Punkt punktPrzeciecia = null;
        try {
            punktPrzeciecia = Prosta.punktPrzeciecia(pr1, pr2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(punktPrzeciecia.getX() + ", " + punktPrzeciecia.getY());

        System.out.println("Punkt przecięcia prostych równoległych.");
        try {
            Punkt prz = Prosta.punktPrzeciecia(pr1, pr4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Składanie wektorów. Poprawny wynik: [3.0, 1.0].");
        Wektor vw = Wektor.dodajWektor(v, w);
        System.out.println("[" + vw.dx + ", " + vw.dy + "]");

        System.out.println("Przesunięcie prostej o wektor. Poprawny wynik: 1.0, 1.0, 3.0");
        Wektor wekPrzes = new Wektor(1.0, 3.0);
        Prosta przesunieta = Prosta.przesunOWektor(pr3, wekPrzes);
        System.out.println(przesunieta.a + " " + przesunieta.b + " " + przesunieta.c);

        System.out.println("Przesunięcie trójkąta. Poprawny wynik: 2.0 0.0, 4.0 2.0, 2.0 -1.0.");
        t1.przesun(v);
        System.out.println(t1.getA().getX() + " " + t1.getA().getY() + ", " + t1.getB().getX() + " " + t1.getB().getY() + ", " + t1.getC().getX() + " " + t1.getC().getY());

    }
}
