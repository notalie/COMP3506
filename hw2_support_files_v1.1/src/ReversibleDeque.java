import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ReversibleDeque<T> implements SimpleDeque<T> {
    private SimpleDeque<T> data;
    /**
     * Constructs a new reversible deque, using the given data deque to store
     * elements.
     * The data deque must not be used externally once this ReversibleDeque
     * is created.
     * @param data a deque to store elements in.
     */
    public ReversibleDeque(SimpleDeque<T> data) {
        this.data = data;
    }

    public void reverse() {

    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public boolean isFull() {
        return this.data.isFull();
    }

    @Override
    public void pushLeft(T e) throws RuntimeException {
        this.data.pushLeft(e);
    }

    @Override
    public void pushRight(T e) throws RuntimeException {
        this.data.pushRight(e);
    }

    @Override
    public T peekLeft() throws NoSuchElementException {
        return this.data.peekLeft();
    }

    @Override
    public T peekRight() throws NoSuchElementException {
        return this.data.peekRight();
    }

    @Override
    public T popLeft() throws NoSuchElementException {
        return this.data.popLeft();
    }

    @Override
    public T popRight() throws NoSuchElementException {
        return this.data.popRight();
    }

    @Override
    public Iterator<T> iterator() {
        return this.data.iterator();
    }

    @Override
    public Iterator<T> reverseIterator() {
        return this.data.reverseIterator();
    }
}
