import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple double-ended queue (deque).
 * 
 * @param <T> Element stored inside this deque.
 */
public interface SimpleDeque<T> {
    /**
     * Returns whether the deque is empty.
     * 
     * @return true if the deque is empty, otherwise false.
     */
    boolean isEmpty();

    /**
     * Returns whether the deque is full, i.e. it has a capacity and its size == capacity.
     * 
     * @return true if the deque has reached capacity (if it has one), otherwise false.
     */
    boolean isFull();

    /**
     * Returns the number of elements currently stored in the deque.
     * @return Number of elements.
     */
    int size();

    /**
     * Pushes an element to the left of the deque.
     * 
     * @param e Element to push
     * @throws RuntimeException if the deque is already full
     */
    void pushLeft(T e) throws RuntimeException;
    
    /**
     * Pushes an element to the right of the deque.
     * 
     * @param e Element to push
     * @throws RuntimeException if the deque is already full
     */
    void pushRight(T e) throws RuntimeException;
    
    /**
     * Returns the element at the left of the deque, but does not remove it.
     * 
     * @returns the leftmost element
     * @throws NoSuchElementException if the deque is empty
     */
    T peekLeft() throws NoSuchElementException;
    
    /**
     * Returns the element at the right of the deque, but does not remove it.
     * 
     * @returns the rightmost element
     * @throws NoSuchElementException if the deque is empty     
     */
    T peekRight() throws NoSuchElementException;
    
    /**
     * Removes and returns the element at the left of the deque.
     *
     * @returns the leftmost element
     * @throws NoSuchElementException if the deque is empty
     */
    T popLeft() throws NoSuchElementException;
    
    /**
     * Removes and returns the element at the right of the deque.
     * 
     * @returns the rightmost element
     * @throws NoSuchElementException if the deque is empty  
     */
    T popRight() throws NoSuchElementException;
    
    /**
     * Returns an iterator for the deque in left to right sequence.
     * 
     * The methods hasNext() and next() in the Iterator should run in O(1) time.
     * The remove() method in the iterator should not be implemented.
     *
     * You can assume that the elements in the deque will never change while the iterator is being used.
     * 
     * @returns an iterator over the elements in in order from leftmost to rightmost.
     */
    Iterator<T> iterator();

    /**
     * Returns an iterator for the deque in right to left sequence.
     *
     * The methods hasNext() and next() in the Iterator should run in O(1) time. 
     * The remove() method in the iterator should not be implemented.
     * 
     * You can assume that the elements in the deque will never change while the iterator is being used.
     *
     * @returns an iterator over the elements in in order from rightmost to leftmost.
     */
    Iterator<T> reverseIterator();
    

}
