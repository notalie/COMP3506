import java.util.Arrays;
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


    private class Node {

        private T value;

        private Node next;

        private int occurences;

        private Node(T value, int occurences) {
            this.value = value;
            this.occurences = occurences;
        }
    }

    private int initialCapacity;

    private Object[] setArray;

    private int size;

    private int distinctCount;

    private Node head;


    public LinkedMultiHashSet(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.setArray =  new Object[initialCapacity];
        this.size = 0;
        this.distinctCount = 0;
        this.head = null;
    }

    private int getHash(T element) {
        int hash = element.hashCode() % internalCapacity();
        while(true) {
            Node value = (Node) this.setArray[hash];
            if (this.setArray[hash] != null && value.value == element) {
                return hash;
            } else if (this.setArray[hash] == null) {
                return hash;
            } else {
                hash = (hash + 1) % internalCapacity();
            }
        }
    }

    private void checkAndResize() {
        // Would Cause Overflow
        if (this.initialCapacity < this.distinctCount + 1) {
            Object[] newArray = new Object[initialCapacity * 2];
            for (int i = 0; i < this.initialCapacity; i++) {
                newArray[i] = this.setArray[i];
            }
            initialCapacity *= 2;
            this.setArray = newArray;
        }
    }

    private void modifyNode(T element, int amount) {
        Node existingElem = (Node) this.setArray[getHash(element)];
        existingElem.occurences += amount;

        if (existingElem.occurences == 0) {
            this.setArray[getHash(element)] = null;
            distinctCount--;
        }

        this.size += amount;
    }

    /**
     * Adds the element to the set. If an equal element is already in the set,
     * increases its occurrence count by 1.
     *
     * @param element to add
     * @require element != null
     */
    @Override
    public void add(T element) {
        if (contains(element)) {
            modifyNode(element, 1);
        } else {
            Node temp = this.head;
            if (temp != null) {
                while(temp.next != null) {
                    temp = temp.next;
                }
                temp.next = new Node(element, 1);
            } else { // head hasn't been initalised
                this.head = new Node(element, 1);
            }

            this.setArray[getHash(element)] = new Node(element, 1);
            distinctCount++;
            this.size++;
        }
        checkAndResize();
    }

    /**
     * Adds count to the number of occurrences of the element in set.
     *
     * @param element to add
     * @require element != null && count >= 0
     */
    @Override
    public void add(T element, int count) {
        if (contains(element)) {
            modifyNode(element, count);
        } else {
            Node temp = this.head;
            if (this.head != null) {
                while(temp.next != null) {
                    temp = temp.next;
                }
                temp.next = new Node(element, count);
            } else { // head hasn't been initalised
                this.head.next = new Node(element, count);
            }

            this.setArray[getHash(element)] = new Node(element, count);
            distinctCount++;
            this.size += count;
        }
        checkAndResize();
    }

    /**
     * Checks if the element is in the set (at least once).
     *
     * @param element to check
     * @return true if the element is in the set, else false.
     */
    @Override
    public boolean contains(T element) {
        if (this.setArray[getHash(element)] != null) {
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
     */
    @Override
    public int count(T element) {
        if (contains(element)) {
            Node node = (Node) this.setArray[getHash(element)];
            return node.occurences;
        } else {
            return 0;
        }
    }

    /**
     * Removes a single occurrence of element from the set.
     *
     * @param element to remove
     * @throws NoSuchElementException if the set doesn't currently
     *         contain the given element
     * @require element != null
     */
    @Override
    public void remove(T element) throws NoSuchElementException {
        if (contains(element)) {
            modifyNode(element, -1);
            Node temp = this.head;
            Node prev = temp;

            if (temp != null) {
                while(!temp.value.equals(element)) {

                    System.out.println(temp);
                    prev = temp;
                    temp = temp.next;
                }
                prev.next = temp.next;
            }
        } else {
            throw new NoSuchElementException();
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
     */
    @Override
    public void remove(T element, int count) throws NoSuchElementException {
        if (contains(element)) {
            Node existingElem = (Node)this.setArray[getHash(element)];
            if ( existingElem.occurences < count) {
                throw new NoSuchElementException();
            }
            modifyNode(element, -1 * count);
            Node temp = this.head;
            Node prev = null;

            if (this.head != null) {
                while(temp.next.value.equals(element)) {
                    prev = temp;
                    temp = temp.next;
                }
                prev.next = temp.next;
            }

        } else {
            throw new NoSuchElementException();
        }

    }

    /**
     * Returns the total count of all elements in the multiset.
     *
     * Note that duplicates of an element all contribute to the count here.
     *
     * @return total count of elements in the collection
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
     */
    @Override
    public int internalCapacity() {
        return this.initialCapacity;
    }

    /**
     * Returns the number of distinct elements currently stored in the set.
     *
     * @return count of distinct elements in the set
     */
    @Override
    public int distinctCount() {
        return this.distinctCount;
    }

    @Override
    public Iterator<T> iterator() {
        /*DequeNode rightNode = this.tail;

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
        };*/
        return null;
    }
}