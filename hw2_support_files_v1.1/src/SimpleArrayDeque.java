import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayDeque<T> implements SimpleDeque<T> {


    private int size;

    private int capacity;

    private int topIndex;

    private int bottomIndex;

    /**
     * Constructs a new array based deque with limited capacity.
     * 
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0
     */
    public SimpleArrayDeque(int capacity) throws IllegalArgumentException {
        this.size = 0;
        this.capacity = capacity;
    }

    /**
     * Constructs a new array based deque with limited capacity, and initially populates the deque
     * with the elements of another SimpleDeque.
     *
     * @param otherDeque the other deque to copy elements from. otherDeque should be left intact.
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0 or size of otherDeque is > capacity
     */
    public SimpleArrayDeque(int capacity, SimpleDeque<? extends T> otherDeque) 
            throws IllegalArgumentException {
//        if (otherDeque.capacity > capacity || capacity <= 0) {
//            throw new IllegalArgumentException();
//        }

    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean isFull() {
        return this.size == this.capacity;
    }

    @Override
    public int size() {
        return this.size;
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
