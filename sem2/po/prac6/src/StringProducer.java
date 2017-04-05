
public class StringProducer extends AbstractProducer<String> {
    private String str;
    public volatile boolean working = true;

    public StringProducer(Buffer<String> buff, String s) {
        super(buff);
        str = s;
    }

    @Override
    public void produce() {
        try {
            String s = str;
            buffer.put(s);
//            System.out.println("Produced: " + str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        while (working) {
            produce();
        }

    }
}
