import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        Lista<Integer> l = new Lista<>(5);
        l.PushFront(3);
        l.PushBack(7);
        l.PushBack(8);

        l.Wypisz();

        try {
            ReadAndWriteToFile.writeToFile("lista.txt", l);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Lista<Integer> l2 = new Lista<>();
        try {
            l2 = (Lista) ReadAndWriteToFile.readFromFile("lista.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        l2.Wypisz();

    }
}
