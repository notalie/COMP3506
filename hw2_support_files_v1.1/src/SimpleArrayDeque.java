import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayDeque<T> implements SimpleDeque<T> {

    private T[] dequeArray;

    private int size;

    private int capacity;

    private int frontIndex;

    private int backIndex;

    /**
     * Constructs a new array based deque with limited capacity.
     * 
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0
     */
    public SimpleArrayDeque(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.frontIndex = this.capacity; // Indicator for start of array deque
        this.backIndex = 0;

        this.dequeArray = (T[])new Object[this.capacity];
    }

    //TODO : take out
    public T[] getArr() {
        return this.dequeArray;
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
        if (otherDeque.size() > capacity || capacity <= 0) {
            throw new IllegalArgumentException();
        }

        this.capacity = capacity;
        this.frontIndex = this.capacity;
        this.backIndex = 0;

        this.dequeArray = (T[])new Object[this.capacity];
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
        return this.size == this.capacity;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void pushLeft(T e) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException();
        }
        if (this.frontIndex == this.capacity) { // First initialisation
            this.frontIndex = 0;
            this.backIndex = 0;
        } else {
            this.backIndex = (this.backIndex + 1) % this.capacity;
        }

        this.size++;

        this.dequeArray[backIndex] = e;
    }

    @Override
    public void pushRight(T e) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException();
        }
        this.size++;

        if (this.frontIndex == 0) {
            this.frontIndex = this.capacity - 1;
        } else {
            this.frontIndex--;
        }
        this.dequeArray[this.frontIndex] = e;
    }

    @Override
    public T peekLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.dequeArray[this.backIndex];
    }

    @Override
    public T peekRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.dequeArray[this.frontIndex];
    }

    @Override
    public T popLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T elem = this.dequeArray[this.backIndex];
        this.dequeArray[this.backIndex--] = null;
        this.size--;
        return elem;
    }

    @Override
    public T popRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T elem = this.dequeArray[this.frontIndex];
        this.dequeArray[this.frontIndex++] = null;
        this.size--;
        return elem;
    }
    
    @Override
    public Iterator<T> iterator() {
        T[] iteratingArray = this.dequeArray.clone();
        int frontIndex = this.frontIndex;
        return new Iterator<T>() {
            int index = iteratingArray.length - 1;

            @Override
            public boolean hasNext() {
                return index >= frontIndex;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T elem = iteratingArray[index--];
                    return elem;
                }
                throw new NoSuchElementException();
            }
            @Override
            public void remove() {
                // Does nothing
            }
        };
    }

    @Override
    public Iterator<T> reverseIterator() {
        T[] iteratingArray = this.dequeArray.clone();
        int backIndex = this.backIndex;
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index <= backIndex;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T elem = iteratingArray[index++];
                    return elem;
                }
                throw new NoSuchElementException();
            }
            @Override
            public void remove() {
                // Does nothing
            }
        };
    }
}
