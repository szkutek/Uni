import java.io.*;

public class ReadAndWriteToFile {

    public static void writeToFile(String path, Serializable o) throws FileNotFoundException {
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(o);
            out.close();
            file.close();
            System.out.println("Successfully saved to file " + path);
        } catch (java.io.IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Serializable readFromFile(String path) throws FileNotFoundException, ClassNotFoundException {
//        Serializable o = null;
        try {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(file);
            Serializable o = (Serializable) in.readObject();

            in.close();
            file.close();
            System.out.println("Successfully read from file " + path);
            return o;
        } catch (java.io.IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}


