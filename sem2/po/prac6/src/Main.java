
public class Main {

    public static void main(String[] args) {

        Buffer<String> storage = new Buffer<>(5);
        Thread p1 = new Thread(new StringProducer(storage, "cukier"));
        Thread c1 = new Thread(new StringPrinterConsumer(storage));

        p1.start();
        c1.start();
//        new Thread(p1).start();
//        new Thread(c1).start();

    }

}
