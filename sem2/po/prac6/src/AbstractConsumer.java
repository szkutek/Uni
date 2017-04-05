
public abstract class AbstractConsumer<T> implements Consumer<T> {

    protected Buffer<T> buffer;
    public volatile boolean working;

    protected AbstractConsumer(Buffer<T> buff) {
        buffer = buff;
    }
}
