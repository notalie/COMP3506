import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedDeque<T> implements SimpleDeque<T> {

    /* The size of the linked deque. Defaults to 0 and -1 if there is no maximum capacity */
    private int size = 0;

    /* The maximum capacity of the linked deque */
    private int capacity;

    /* The head element to start from */
    private DequeNode head;

    /* The tail node to start from */
    private DequeNode tail;

    private class DequeNode {
        /* The next node */
        private DequeNode next;

        /* The previous node */
        private DequeNode prev;

        /* The data that the node contains */
        private T data;

        /**
         * A representation of a node within the linked list
         * Memory Complexity: O(1) the size does not increase with the changing of a node
         * @param nextElement - the next element for the deque node to point to
         * @param previousElement - the previous element for the deque node to point to
         * @param data - the data fot the node to contain
         */
        private DequeNode(DequeNode nextElement, DequeNode previousElement, T data) {
            this.data = data;
            this.next = nextElement;
            this.prev = previousElement;
        }
    }

    /**
     * Constructs a new linked list based deque with unlimited capacity.
     * Memory Complexity: O(n) - where n is the size of the linked deque.
     * It will keep increasing though as there is no limit on the capacity.
     */
    public SimpleLinkedDeque() {
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.capacity = -1; // No cap on the capacity max
    }

    /**
     * Constructs a new linked list based deque with limited capacity.
     * Memory Complexity: O(n) where n is the max capacity of the linked deque
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0
     */
    public SimpleLinkedDeque(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
    }

    /**
     * Constructs a new linked list based deque with unlimited capacity, and initially 
     * populates the deque with the elements of another SimpleDeque.
     * Memory Complexity: O(n) where n is the size of the other deque
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
     * Memory Complexity: O(n) where n is the given capacity
     * @param otherDeque the other deque to copy elements from. otherDeque should be left intact.
     * @param capacity the capacity
     * @throws IllegalArgumentException if capacity <= 0 or size of otherDeque is > capacity
     */
    public SimpleLinkedDeque(int capacity, SimpleDeque<? extends T> otherDeque) 
            throws IllegalArgumentException {
        if (capacity <= 0 || otherDeque.size() > capacity) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        Iterator<?> otherDequeIterator = otherDeque.iterator();
        while(otherDequeIterator.hasNext()) {
            T elem = (T)otherDequeIterator.next();
            if (elem != null) {
                pushRight(elem);
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
        return this.capacity == this.size;
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
     * Push an element through the tail way of the linked deque
     * @param e Element to push to the right
     * @throws RuntimeException - if the linked deque is full
     * Big-O: O(1) to create a single node and assign it to the linked deque
     * Memory Complexity: O(1) to create a single node
     */
    @Override
    public void pushRight(T e) throws RuntimeException {
        if (isFull()) {
            throw new RuntimeException();
        }

        DequeNode newNode = new DequeNode(null, this.tail, e);

        if (this.tail == null) {
            this.head = newNode;
        } else {
            this.tail.next = newNode;
        }
        this.tail = newNode;
        this.size++;
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
        if (isFull()) {
            throw new RuntimeException();
        }
        DequeNode newNode = new DequeNode(this.head, null, e);

        if (this.head == null) {
            this.tail = newNode;
        } else {
            this.head.prev = newNode;
        }
        this.head = newNode;
        this.size++;
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.tail.data;
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.head.data;
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        DequeNode tailVal = this.tail;
        this.tail = tailVal.prev;
        this.size--;
        return tailVal.data;
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        DequeNode headVal = this.head;
        this.head = headVal.next;

        this.size--;
        return headVal.data;
    }

    /**
     * Creates an iterator that can traverse through the linked deque without modifying the data
     *  Big-O: O(n) with n representing the size of the linked deque
     *  Memory Complexity: The size of the iterator which can be created in O(1) time
     * @return - an iterator that goes from the head side to the tail side
     */
    @Override
    public Iterator<T> iterator() {
        DequeNode leftNode = this.head;

        return new Iterator<T>() {
            DequeNode currentNode = leftNode;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T elem = currentNode.data;
                    currentNode = currentNode.next;
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
     * Creates an iterator that can traverse through the linked deque without modifying the data
     *  Big-O: O(n) with n representing the size of the linked deque
     *  Memory Complexity: The size of the iterator which can be created in O(1) time
     * @return - an iterator that goes from the tail side to the head side
     */
    @Override
    public Iterator<T> reverseIterator() {
        DequeNode rightNode = this.tail;

        return new Iterator<T>() {
            DequeNode currentNode = rightNode;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T elem = currentNode.data;
                    currentNode = currentNode.prev;
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
