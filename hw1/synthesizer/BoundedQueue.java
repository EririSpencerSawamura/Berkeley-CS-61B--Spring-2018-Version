package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    /* Returns the size of the buffer. */
    int capacity();
    /* Returns the number of items in the buffer. */
    int fillCount();
    /* Adds item x to the end of the queue. */
    void enqueue(T x);
    /* Deletes and returns the item on the front of the queue. */
    T dequeue();
    /* Returns (but does not delete) the item on the front of the queue. */
    T peek();

    /* Returns an iterator of the queue. */
    Iterator<T> iterator();

    /** Tells whether the bounded queue is empty. */
    default boolean isEmpty() {
        return fillCount() == 0;
    }
    /** Tells whether the bounded queue is full. */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
