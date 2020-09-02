import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedDeque<T> implements SimpleDeque<T> {

    private int size = 0;

    private int capacity;

    private DequeNode head;
    private DequeNode tail;


    private class DequeNode {
        private DequeNode next;
        private DequeNode prev;
        private T data;
        private DequeNode(DequeNode nextElement, DequeNode previousElement, T data) {
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
        this.capacity = -1; // No cap on the capacity max
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
        Iterator<?> otherDequeIterator = otherDeque.iterator();
        this.capacity = -1;
        while(otherDequeIterator.hasNext()) {
            T elem = (T)otherDequeIterator.next();
            if (elem != null) {
                pushLeft(elem);
            }
        }
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
        this.capacity = capacity;
        Iterator<?> otherDequeIterator = otherDeque.iterator();
        while(otherDequeIterator.hasNext()) {
            T elem = (T)otherDequeIterator.next();
            if (elem != null) {
                pushLeft(elem);
            }
        }
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


    // go through tail way/back way
    @Override
    public void pushLeft(T e) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException();
        }

        DequeNode tailVal = this.tail;
        DequeNode newNode = new DequeNode(null, tailVal, e);
        this.tail = newNode;

        if (this.head == null) {
            this.head = newNode;
        }

        this.size++;
    }

    @Override
    public void pushRight(T e) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException();
        }
        DequeNode headVal = this.head;
        DequeNode newNode = new DequeNode(headVal, null, e);
        this.head = newNode;

        if (this.tail == null) {
            this.tail = newNode;
        }
        this.size++;
        // go through head way/front way
    }

    @Override
    public T peekLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.tail.data;
    }

    @Override
    public T peekRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.head.data;
    }

    @Override
    public T popLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        DequeNode tailVal = this.tail;
        this.tail = tailVal.next;

        this.size--;
        return tailVal.data;
    }

    @Override
    public T popRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        DequeNode headVal = this.head;


        this.head = headVal.next;
        this.size--;
        return headVal.data;
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
