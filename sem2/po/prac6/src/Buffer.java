public class Buffer<T> {

    private volatile T[] buff;
    private int capacity;

    public Buffer(int buffLength) {
        if (buffLength < 0) {
            throw new IllegalArgumentException();
        }
        buff = (T[]) (new Object[buffLength]);
        capacity = 0;
    }

    public synchronized T pop() throws InterruptedException {
        if (isEmpty()) {
            wait();
        }
        T popped = buff[0];
        capacity--;
        for (int i = 0; i < capacity; i++) {
            buff[i] = buff[i + 1];
        }
        System.out.println(popped);
        notifyAll();
        return popped;
    }

    public synchronized void put(T elem) throws InterruptedException {
        if (isFull()) {
            wait();
        }
//        if (capacity < buff.length) {
        buff[capacity] = elem;
        capacity++;
        System.out.println("Produced: " + elem);
//        }
        notifyAll();
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return capacity == 0;
    }

    public boolean isFull() {
        return capacity == buff.length;
    }
}
