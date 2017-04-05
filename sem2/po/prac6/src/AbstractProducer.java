
public abstract class AbstractProducer<T> implements Producer<T> {

    protected Buffer<T> buffer;
    public volatile boolean working;

    protected AbstractProducer(Buffer<T> buff) {
        buffer = buff;
    }

    @Override
    public abstract void produce();
}
