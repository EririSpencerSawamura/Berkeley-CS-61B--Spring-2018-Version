/** A generic deque class implemented with arrays.
 *  We regard the base array in the deque as "circular". */
public class ArrayDeque<T> {
    private T[] items;
    private int currSize;
    private int maxSize;
    private int first;
    private int last;

    /** The default constructor, which creates an empty linked list deque.
     *  The starting size of the array is 8. */
    public ArrayDeque() {
        maxSize = 8;
        items = (T[]) new Object[maxSize];
        currSize = 0;
        first = 3;
        last = 4;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return currSize == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return currSize;
    }

    /** print the entire deque from front to end. */
    public void printDeque() {
        int ptr = first;
        while (ptr != last) {
            System.out.print(items[ptr] + " ");
            ptr = moveRight(ptr);
        }
    }

    /** Moves the pointer to the left item. If the pointer points
     * to position 0, then move it to the last position of the array. */
    private int moveLeft(int currPtr) {
        if (currPtr == 0) {
            return maxSize - 1;
        } else {
            return currPtr - 1;
        }
    }

    /** Moves the pointer to the right item. If the pointer points
     * to the last position, then move it to position 0 of the array. */
    private int moveRight(int currPtr) {
        if (currPtr == maxSize - 1) {
            return 0;
        } else {
            return currPtr + 1;
        }
    }

    /** Resize the array to double its size, and copy the items to the new array.
     *  Keep the resized array "circular". */
    private void resizeDouble() {
        T[] itemsDouble = (T[]) new Object[maxSize * 2];
        if (first >= last) {
            System.arraycopy(items, 0, itemsDouble, 0, last + 1);
            System.arraycopy(items, first, itemsDouble, first + maxSize, maxSize - 1 - first);
            first += maxSize;
        } else {
            System.arraycopy(items, 0, itemsDouble, 0, maxSize);
        }
        items = itemsDouble;
        maxSize *= 2;
    }

    /** Resize the array to half its size, and copy the items to the new array.
     *  Keep the resized array "circular". */
    private void resizeHalf() {
        T[] itemsHalf = (T[]) new Object[maxSize / 2];
        if (first >= last) {
            System.arraycopy(items, 0, itemsHalf, 0, last + 1);
            System.arraycopy(items, first, itemsHalf, first - maxSize, maxSize - 1 - first);
            first -= maxSize;
        } else {
            System.arraycopy(items, 0, itemsHalf, 0, maxSize);
        }
        items = itemsHalf;
        maxSize /= 2;
    }

    /** Adds an item of type T to the front of the deque,
     *  and moves the first pointer accordingly. */
    public void addFirst(T t) {
        if (currSize == maxSize - 1) {
            resizeDouble();
        }
        first = moveLeft(first);
        items[first] = t;
        currSize++;
    }

    /** Adds an item of type T to the end of the deque,
     *  and moves the last pointer accordingly. */
    public void addLast(T t) {
        if (currSize == maxSize - 1) {
            resizeDouble();
        }
        last = moveRight(last);
        items[last] = t;
        currSize++;
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    public T removeFirst() {
        if (maxSize > 8 && maxSize / currSize > 4) {
            resizeHalf();
        }
        if (currSize == 0) {
            return null;
        }
        T removed = items[first];
        first = moveRight(first);
        currSize--;

        return removed;
    }

    /** Removes and returns the item at the end of the deque.
     *  If no such item exists, returns null. */
    public T removeLast() {
        if (maxSize > 8 && maxSize / currSize > 4) {
            resizeHalf();
        }
        if (currSize == 0) {
            return null;
        }
        T removed = items[last];
        last = moveRight(last);
        currSize--;

        return removed;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null. */
    public T get(int index) {
        if (index >= currSize) {
            return null;
        }
        int ptr = first;
        for (int i = 0; i < index; i++) {
            ptr = moveRight(ptr);
        }
        return items[ptr];
    }
}
