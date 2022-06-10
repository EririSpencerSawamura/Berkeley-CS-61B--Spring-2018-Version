package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    /** Returns the capacity. */
    @Override
    public int capacity() {
        return capacity;
    }
    /** Returns the fillCount. */
    @Override
    public int fillCount() {
        return fillCount;
    }
}
