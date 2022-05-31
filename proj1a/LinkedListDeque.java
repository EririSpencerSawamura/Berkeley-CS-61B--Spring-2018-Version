/** A generic deque class implemented with linked lists.
  * Uses the circular sentinel topology. */
public class LinkedListDeque<T> {
    /** The Node class. */
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T t) {
            item = t;
        }
    }

    private final Node sentinel;  // The first node is sentinel.next, and the last node is sentinel.prev.
    private int size;

    /** The default constructor, which creates an empty linked list deque.
      * The size is 0 here. */
    public LinkedListDeque() {
        sentinel = new Node(null);
        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T t) {
        Node oldFirst = sentinel.next;
        Node newFirst = new Node(t);
        sentinel.next = newFirst;
        newFirst.next = oldFirst;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T t) {
        Node oldLast = sentinel.prev;
        Node newLast = new Node(t);
        sentinel.prev = newLast;
        newLast.prev = oldLast;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node curr = sentinel.next;
        while (curr.next != null) {
            System.out.print(curr.item);
            System.out.print(" ");
        }
    }

    /** Removes and returns the item at the front of the deque.
      * If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node removed = sentinel.next;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removed.item;
    }

    /** Removes and returns the item at the back of the deque.
      * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node removed = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return removed.item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
      * If no such item exists, returns null. Does not alter the deque. */
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        Node curr = sentinel.next;
        int i = 0;
        while (i != index) {
            curr = curr.next;
            i++;
        }
        return curr.item;
    }
}
