import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayDeque<T> implements SimpleDeque<T> {
    /* Deque Array for storing elements */
    private T[] dequeArray;

    /* The size of the deque array */
    private int size;

    /* The maximum capacity for the */
    private int capacity;

    private int rightIndex;

    private int leftIndex;

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
        this.rightIndex = this.capacity; // Indicator for start of array deque
        this.leftIndex = 0;

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
        this.rightIndex = this.capacity;
        this.leftIndex = 0;

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
        if (this.rightIndex == this.capacity) { // First initialisation
            this.rightIndex = 0;
            this.leftIndex = 0;
        } else {
            this.leftIndex = (this.leftIndex + 1) % this.capacity;
        }

        this.size++;

        this.dequeArray[leftIndex] = e;
    }

    @Override
    public void pushRight(T e) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException();
        }
        this.size++;

        if (this.rightIndex == 0) {
            this.rightIndex = this.capacity - 1;
        } else {
            this.rightIndex--;
        }
        this.dequeArray[this.rightIndex] = e;
    }

    @Override
    public T peekLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.dequeArray[this.leftIndex];
    }

    @Override
    public T peekRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.dequeArray[this.rightIndex];
    }

    @Override
    public T popLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T elem = this.dequeArray[this.leftIndex];
        if (leftIndex > 0) {
            leftIndex--;
        }
        this.size--;
        return elem;
    }

    @Override
    public T popRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T elem = this.dequeArray[this.rightIndex];
        if (rightIndex < this.capacity - 1) {
            this.rightIndex++;
        }
        this.size--;
        return elem;
    }
    
    @Override
    public Iterator<T> iterator() {
        T[] iteratingArray = this.dequeArray.clone();
        int rightIndex = this.rightIndex;
        return new Iterator<T>() {
            int index = iteratingArray.length - 1;

            @Override
            public boolean hasNext() {
                return index >= rightIndex;
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
        int leftIndex = this.leftIndex;
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index <= leftIndex;
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
