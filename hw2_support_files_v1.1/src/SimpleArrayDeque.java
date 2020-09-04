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

    /* The index for the right side of the arraydeque */
    private int rightIndex;

    /* The index for the left side of the arraydeque */
    private int leftIndex;

    /**
     * Constructs a new array based deque with limited capacity.
     * Memory Complexity: O(n) where n is the given capacity
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

    /**
     * Constructs a new array based deque with limited capacity, and initially populates the deque
     * with the elements of another SimpleDeque.
     * Memory Complexity: O(n) where n is the given capacity
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

    /**
     * @return the size of the deque array
     * Big-O: O(1) to return a single number
     * Memory Complexity: O(1) because nothing is created
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @return a boolean indicating if the deque array is full or not
     * Big-O: O(1) to return a single number
     * Memory Complexity: O(1) because nothing is created
     */
    @Override
    public boolean isFull() {
        return this.size == this.capacity;
    }

    /**
     * @return the size of the deque array
     * Big-O: O(1) to return a single number
     * Memory Complexity: O(1) because nothing is created
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @param e Element to push into the array
     * @throws RuntimeException - if the array deque is full
     * Big-O: O(1) to place the element in constant time
     * Memory Complexity: O(1) because nothing is created
     */
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

    /**
     * @param e Element to push into the array
     * @throws RuntimeException - if the array deque is full
     * Big-O: O(1) to place the element in constant time
     * Memory Complexity: O(1) because nothing is created
     */
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

    /**
     * @return - The Element on the Left Side
     * @throws NoSuchElementException - if the array deque is empty
     * Big-O: O(1) constant time to find the element and return it
     * Memory Complexity: O(1) because nothing is created
     */
    @Override
    public T peekLeft() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.dequeArray[this.leftIndex];
    }

    /**
     * @return - The Element on the Right Side
     * @throws NoSuchElementException - if the array deque is empty
     * Big-O: O(1) constant time to find the element and return it
     * Memory Complexity: O(1) because nothing is created
     */
    @Override
    public T peekRight() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.dequeArray[this.rightIndex];
    }

    /**
     * Pops the leftmost element
     * @return - The element that has been popped from the left side
     * @throws NoSuchElementException if the deque is empty
     * Big-O: O(1) constant time to find the element and return it
     * Memory Complexity: O(1) because only the element is created to return
     */
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

    /**
     * Pops the rightmost element
     * @return - The element that has been popped from the right side
     * @throws NoSuchElementException if the deque is empty
     * Big-O: O(1) constant time to find the element and return it
     * Memory Complexity: O(1) because only the element is created to return
     */
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

    /**
     * For some reason I had to reverse the way that the deque went.
     * This was because of how I had implemented my array and the tests were failing
     *  if I didn't change this. But it works :D
     *  Big-O: O(n) with n representing the size of the array deque
     *  Memory Complexity: The size of the iterator which can be created in O(1) time
     * @return - an iterator that goes from the left side to right side
     */
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

    /**
     * For some reason I had to reverse the way that the deque went.
     * This was because of how I had implemented my array and the tests were failing
     *  if I didn't change this. But it works :D
     *  Big-O: O(n) with n representing the size of the array deque
     *  Memory Complexity: The size of the iterator which can be created in O(1) time
     * @return - an iterator that goes from the
     */
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
