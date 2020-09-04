import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReversibleDeque<T> implements SimpleDeque<T> {

    /* A simple deque that acts as a wrapper for the reversible deque */
    private SimpleDeque<T> data;

    /**
     * Constructs a new reversible deque, using the given data deque to store
     * elements.
     * The data deque must not be used externally once this ReversibleDeque
     * is created.
     * Memory Complexity: O(n) where n is the size of data
     * @param data a deque to store elements in.
     */
    public ReversibleDeque(SimpleDeque<T> data) {
        this.data = data;
    }

    /**
     * Reverse the elements
     * Big-O: O(n) to iterate through every element of the deque twice to pop and push them back in reverse order
     * Memory Complexity: O(n) to hold the elements in, n representing the size of the deque
     */
    public void reverse() {
        T[] elements = (T[]) new Object[size()];
        int i = 0;
        while (size() > 0) {
            elements[i++] = popRight();
        }

        while (i > 0) {
            pushLeft(elements[--i]);
        }
    }

    /**
     * @return the size of the deque array
     * Big-O: O(1) to return a single number
     * Memory Complexity: O(1) because nothing is created
     */
    @Override
    public int size() {
        return this.data.size();
    }

    /**
     * @return the size of the deque array
     * Big-O: O(1) to return a single number
     * Memory Complexity: O(1) because nothing is created
     */
    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    /**
     * @return a boolean indicating if the deque array is full or not
     * Big-O: O(1) to return a single number
     * Memory Complexity: O(1) because nothing is created
     */
    @Override
    public boolean isFull() {
        return this.data.isFull();
    }

    /**
     * Pushes the leftmost element into the linked deque/head way
     * @param e Element to push to the left
     * @throws RuntimeException - if the linked deque is full
     * Big-O: O(1) to create a single node and assign it to the linked deque
     * Memory Complexity: O(1) to create a single node
     */
    @Override
    public void pushLeft(T e) throws RuntimeException {
        this.data.pushLeft(e);
    }

    /**
     * Push an element through the tail way of the linked deque
     * @param e Element to push to the right
     * @throws RuntimeException - if the linked deque is full
     * Big-O: O(1) to create a single node and assign it to the linked deque
     * Memory Complexity: O(1) to create a single node
     */
    @Override
    public void pushRight(T e) throws RuntimeException {
        this.data.pushRight(e);
    }

    /**
     * Looks at the leftmost element but does not pop it
     * @return - the leftmost element (head element)
     * @throws NoSuchElementException - if the deque is empty
     * Big-O: O(1) to return a single element
     * Memory Complexity: O(1) because nothing is created
     */
    @Override
    public T peekLeft() throws NoSuchElementException {
        return this.data.peekLeft();
    }

    /**
     * Looks at the rightmost element but does not pop it
     * @return - the rightmost element (tail element)
     * @throws NoSuchElementException - if the deque is empty
     * Big-O: O(1) to return a single element
     * Memory Complexity: O(1) because nothing is created
     */
    @Override
    public T peekRight() throws NoSuchElementException {
        return this.data.peekRight();
    }

    /**
     * Pops the leftmost element
     * @return - the leftmost element (head element)
     * @throws NoSuchElementException - if the deque is empty
     * Big-O: O(1) to return a value
     * Memory Complexity: O(1) to create a new node to assign
     */
    @Override
    public T popLeft() throws NoSuchElementException {
        return this.data.popLeft();
    }

    /**
     * Pops the rightmost element
     * @return - the rightmost element (tail element)
     * @throws NoSuchElementException - if the deque is empty
     * Big-O: O(1) to return a value
     * Memory Complexity: O(1) to create a new node to assign
     */
    @Override
    public T popRight() throws NoSuchElementException {
        return this.data.popRight();
    }

    /**
     * Creates an iterator that can traverse through the deque without modifying the data
     *  Big-O: O(n) with n representing the size of the data deque
     *  Memory Complexity: The size of the iterator which can be created in O(1) time
     * @return - an iterator that goes from the left side to the right side of the deque
     */
    @Override
    public Iterator<T> iterator() {
        return this.data.iterator();
    }

    /**
     * Creates an iterator that can traverse through the deque without modifying the data
     *  Big-O: O(n) with n representing the size of the data deque
     *  Memory Complexity: The size of the iterator which can be created in O(1) time
     * @return - an iterator that goes from the right side to the left side of the deque
     */
    @Override
    public Iterator<T> reverseIterator() {
        return this.data.reverseIterator();
    }
}
