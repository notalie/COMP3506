import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedDeque<T> implements SimpleDeque<T> {

    private int size = 0;

    private int capacity;

    private DequeElement head;
    private DequeElement tail;


    private class DequeElement {
        private DequeElement next;
        private DequeElement prev;
        private T data;
        private DequeElement(DequeElement nextElement, DequeElement previousElement, T data) {
            this.data = data;
            this.next = nextElement;
            this.prev = previousElement;
        }
    }

    /**
     * Constructs a new linked list based deque with unlimited capacity.
     */
    public SimpleLinkedDeque() {
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.capacity = 0;
    }

    /**
     * Constructs a new linked list based deque with limited capacity.
     *
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0
     */
    public SimpleLinkedDeque(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    /**
     * Constructs a new linked list based deque with unlimited capacity, and initially 
     * populates the deque with the elements of another SimpleDeque.
     *
     * @param otherDeque the other deque to copy elements from. otherDeque should be left intact.
     * @requires otherDeque != null
     */
    public SimpleLinkedDeque(SimpleDeque<? extends T> otherDeque) {
        
    }
    
    /**
     * Constructs a new linked list based deque with limited capacity, and initially 
     * populates the deque with the elements of another SimpleDeque.
     *
     * @param otherDeque the other deque to copy elements from. otherDeque should be left intact.
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0 or size of otherDeque is > capacity
     */
    public SimpleLinkedDeque(int capacity, SimpleDeque<? extends T> otherDeque) 
            throws IllegalArgumentException {

    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return this.capacity == this.size;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void pushLeft(T e) throws RuntimeException {
//        new DequeElement(null, null, null) // go through tail way
    }

    @Override
    public void pushRight(T e) throws RuntimeException {
            // go through head way
    }

    @Override
    public T peekLeft() throws NoSuchElementException {
        return null;
    }

    @Override
    public T peekRight() throws NoSuchElementException {
        return null;
    }

    @Override
    public T popLeft() throws NoSuchElementException {
        return null;
    }

    @Override
    public T popRight() throws NoSuchElementException {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Iterator<T> reverseIterator() {
        return null;
    }
}
