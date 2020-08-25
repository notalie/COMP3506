import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReversibleDeque<T> implements SimpleDeque<T> {

    /**
     * Constructs a new reversible deque, using the given data deque to store
     * elements.
     * The data deque must not be used externally once this ReversibleDeque
     * is created.
     * @param data a deque to store elements in.
     */
    public ReversibleDeque(SimpleDeque<T> data) {

    }

    public void reverse() {

    }

    @Override
    public int size() {
        return 0;
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
