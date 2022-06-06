/** A generic deque class implemented with linked lists.
 *  Uses the circular sentinel topology. */
public class LinkedListDeque<T> implements Deque<T> {
    /** The Node class. */
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        /** The constructor for the Node class. */
        public Node(T t, Node p, Node n) {
            item = t;
            prev = p;
            next = n;
        }
    }

    // The first node is sentinel.next, and the last node is sentinel.prev.
    private Node sentinel;
    private int size;

    /** Initiates a sentinel node for the deque.
     *  The prev and next pointers of the sentinel node both points to itself. */
    private Node makeSentinel() {
        Node stn = new Node(null, null, null);
        stn.prev = stn;
        stn.next = stn;

        return stn;
    }

    /** The default constructor, which creates an empty linked list deque.
     *  The size is 0 here. */
    public LinkedListDeque() {
        sentinel = makeSentinel();
        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(T t) {
        Node newFirst = new Node(t, sentinel, sentinel.next);
        sentinel.next.prev = newFirst;
        sentinel.next = newFirst;
        size++;
    }

    /** Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T t) {
        Node newLast = new Node(t, sentinel.prev, sentinel);
        sentinel.prev.next = newLast;
        sentinel.prev = newLast;
        size++;
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node removed = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;

        return removed.item;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node removed = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;

        return removed.item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *  If no such item exists, returns null.
     *  Does not alter the deque. Uses iteration. */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node curr = sentinel.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    private T getRecursiveHelper(Node curr, int index) {
        if (index >= size) {
            return null;
        } else if (index == 0) {
            return curr.item;
        } else {
            return getRecursiveHelper(curr.next, index - 1);
        }
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }
}
