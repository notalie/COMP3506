import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedDeque<T> implements SimpleDeque<T> {

    /**
     * Constructs a new linked list based deque with unlimited capacity.
     */
    public SimpleLinkedDeque() {

    }

    /**
     * Constructs a new linked list based deque with limited capacity.
     *
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0
     */
    public SimpleLinkedDeque(int capacity) throws IllegalArgumentException {

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
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void pushLeft(T e) throws RuntimeException {

    }

    @Override
    public void pushRight(T e) throws RuntimeException {

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
