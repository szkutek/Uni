
public class StringPrinterConsumer extends AbstractConsumer<String> {
    public volatile boolean working = true;

    protected StringPrinterConsumer(Buffer<String> buff) {
        super(buff);
    }

    @Override
    public void consume() {
        try {
            String pop = this.buffer.pop();
//            System.out.println(pop);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        while (working) {
            consume();
        }
    }
}
