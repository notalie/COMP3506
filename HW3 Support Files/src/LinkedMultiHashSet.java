import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedMultiHashSet is an implementation of a (@see MultiSet), using a hashtable as the internal
 * data structure, and with predictable iteration order based on the insertion order
 * of elements.
 * 
 * Its iterator orders elements according to when the first occurrence of the element 
 * was added. When the multiset contains multiple instances of an element, those instances 
 * are consecutive in the iteration order. If all occurrences of an element are removed, 
 * after which that element is added to the multiset, the element will appear at the end of the 
 * iteration.
 * 
 * The internal hashtable array should be doubled in size after an add that would cause it to be
 * at full capacity. The internal capacity should never decrease.
 * 
 * Collision handling for elements with the same hashcode (i.e. with hashCode()) should be done
 * using linear probing, as described in lectures.
 * 
 * @param <T> type of elements in the set
 */
public class LinkedMultiHashSet<T> implements MultiSet<T>, Iterable<T> {


    /**
     * A node representing a value within the bag.
     * Each node is linked using the next property
     */
    private class Node {

        /* The value/element within the bag */
        private T value;

        /* The next value of the node */
        private Node next;

        /* The number of times that the value appears/occurs within the bag */
        private int occurences;

        /**
         * Constructor for a node
         * @param value - the value for the node
         * @param occurences - the number of times that the node occurs within the bag
         */
        private Node(T value, int occurences) {
            this.value = value;
            this.occurences = occurences;
            this.next = null;
        }
    }

    /* The initial/max capacity of the bag */
    private int initialCapacity;

    /* Array that tracks elements within the bag */
    private Object[] setArray;

    /* The size of the bag, counts each occurrence individually */
    private int size;

    /* The start of the linked node list */
    private Node head;

    /* The number of distinct elements */
    private int distinctCount;


    public LinkedMultiHashSet(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.setArray = new Object[initialCapacity];
        this.head = null;
        this.size = 0;
        this.distinctCount = 0;
    }

    /**
     * Finds a hash value for a corresponding element
     * @param element - the element to find a hash for
     * @return an int responding to a hash value for the element
     * Time Complexity: O(n) (worse case) - where n is the initial capacity
     * Space Complexity: O(1)
     */
    private int hash(T element) {
        int hash = element.hashCode() % this.initialCapacity;
        while(true) {
            Node value = (Node) this.setArray[hash];
            if (this.setArray[hash] != null && value.value.equals(element)) {
                return hash;
            } else if (this.setArray[hash] == null) {
                return hash;
            } else {
                hash = (hash + 1) % this.initialCapacity;
            }
        }
    }

    /**
     * Checks if the recently added item has has made the bag full
     * If it has, double the initial capacity size and rehash all the values in
     * Time Complexity: O(n) - where n the size of the new initial capacity
     * Space Complexity: O(n) - where n the size of the new initial capacity
     */
    private void checkAndResize() {
        // Bag is full
        if (this.initialCapacity == distinctCount()) {
            initialCapacity *= 2;
            this.setArray = new Object[initialCapacity];
            Node current = this.head;
            while (current != null) {
                this.setArray[hash(current.value)] = current;
                current = current.next;
            }
        }
    }


    /**
     * Adds count to the number of occurrences of the element in set.
     *
     * @param element to add
     * @require element != null && count >= 0
     * Time Complexity: O(n) - where n the size of initial capacity
     * Space Complexity: O(1)
     */
    @Override
    public void add(T element, int count) {
        if (contains(element)) {
            Node elem = (Node) this.setArray[hash(element)];
            elem.occurences += count;
        } else {
            Node toAdd = new Node(element, count);
            if (this.head == null) {
                this.head = toAdd;
            } else { // adding to the end of the linked list
                toAdd.next = null;

                Node last = this.head;
                while(last.next != null) {
                    last = last.next;
                }

                last.next = toAdd;
            }
            distinctCount++;
            this.setArray[hash(element)] = toAdd;
        }
        size += count;
        checkAndResize();
    }

    /**
     * Checks if the element is in the set (at least once).
     *
     * @param element to check
     * @return true if the element is in the set, else false.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public boolean contains(T element) {
        if (this.setArray[hash(element)] != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the count of how many occurrences of the given elements there
     * are currently in the set.
     *
     * @param element to check
     * @return the count of occurrences of element
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int count(T element) {
        Node elem = (Node) this.setArray[hash(element)];
        if (elem != null) {
            return elem.occurences;
        } else {
            return 0;
        }
    }

    /**
     * Removes several occurrences of the element from the set.
     *
     * @param element to remove
     * @param count the number of occurrences of element to remove
     * @throws NoSuchElementException if the set contains less than
     *         count occurrences of the given element
     * @require element != null && count >= 0
     * Time Complexity: O(n) - where n is the number of nodes
     * Space Complexity: O(1)
     */
    @Override
    public void remove(T element, int count) throws NoSuchElementException {
        Node elem = (Node) this.setArray[hash(element)];
        if (contains(element)) {
            if (count > elem.occurences) {
                throw new NoSuchElementException();
            }
            elem.occurences -= count;

            Node temp = head;
            Node prev = null;

            // Special case for if the value getting removed is the head
            if (temp != null && temp.value.equals(element) && elem.occurences == 0) {
                this.setArray[hash(element)] = null;
                this.size -= count;
                distinctCount--;
                if (temp.next != null) {
                    this.head = temp.next;
                } else {
                    this.head = null;
                }
                return;
            }

            // If the occurrences have hit 0, remove the item.
            if (elem.occurences == 0) {
                // Find value starting from the head until it is found
                while(temp != null && !temp.value.equals(element)) {
                    prev = temp;
                    temp = temp.next;
                }

                prev.next = temp.next;
                distinctCount--;
                this.setArray[hash(element)] = null;
            }
            this.size -= count;

        } else { // element is not in the bag/set
            throw new NoSuchElementException();
        }
    }

    /**
     * Returns an iterator that iterates through the multiset
     * @return - an iterator that starts from the start of the multiset
     */
    @Override
    public Iterator<T> iterator() {
        Node currentNode = this.head;
        return new Iterator<T>() {
            Node current = currentNode;
            int occurences = current.occurences;
            int counter = 0;

            /**
             * Determines if the iterator can continue iterating
             * @return - a boolean indicating if there is a next element to iterate over
             * Time Complexity: O(1)
             * Space Complexity: O(1)
             */
            @Override
            public boolean hasNext() {
                return counter < size();
            }

            /**
             * Returns the next element in the iterator
             * @return - the next element in the list
             * Time Complexity: O(1)
             * Space Complexity: O(1)
             */
            @Override
            public T next() {
                if (hasNext()) {
                    if  (occurences == 0) {
                        current = current.next;
                        occurences = current.occurences - 1;
                        counter++;
                        return current.value;
                    } else {
                        occurences--;
                        counter++;
                        return current.value;
                    }
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
     * Adds the element to the set. If an equal element is already in the set,
     * increases its occurrence count by 1.
     *
     * @param element to add
     * @require element != null
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public void add(T element) {
        add(element, 1);
    }

    /**
     * Removes a single occurrence of element from the set.
     *
     * @param element to remove
     * @throws NoSuchElementException if the set doesn't currently
     *         contain the given element
     * @require element != null
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public void remove(T element) throws NoSuchElementException {
        remove(element, 1);
    }

    /**
     * Returns the total count of all elements in the multiset.
     *
     * Note that duplicates of an element all contribute to the count here.
     *
     * @return total count of elements in the collection
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int size() {
        return this.size;
    }


    /**
     * Returns the maximum number of *distinct* elements the internal data
     * structure can contain before resizing.
     *
     * @return capacity of internal array
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int internalCapacity() {
        return this.initialCapacity;
    }

    /**
     * Returns the number of distinct elements currently stored in the set.
     *
     * @return count of distinct elements in the set
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    @Override
    public int distinctCount() {
        return this.distinctCount;
    }
}