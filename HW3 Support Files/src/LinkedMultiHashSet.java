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


    public class Node {

        public T value;

        public Node next;

        private int occurences;

        private Node(T value, int occurences) {
            this.value = value;
            this.occurences = occurences;
            this.next = null;
        }
    }

    private int initialCapacity;

    private Object[] setArray;

    private int size;

    public Node head;

    private int distinctCount;


    public LinkedMultiHashSet(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.setArray = new Object[initialCapacity];
        this.head = null;
        this.size = 0;
        this.distinctCount = 0;
    }

    private int hash(T element) {
        int hash = element.hashCode() % this.initialCapacity;
        while(true) {
            Node value = (Node) this.setArray[hash];
            if (this.setArray[hash] != null && value.value == element) {
                return hash;
            } else if (this.setArray[hash] == null) {
                return hash;
            } else {
                hash = (hash + 1) % this.initialCapacity;
            }
        }
    }

    private void checkAndResize() {
        if (this.initialCapacity < distinctCount() + 1) {
            initialCapacity *= 2;
            this.setArray = new Object[initialCapacity];
            Node current = this.head;
            while (current != null) {
                this.setArray[hash(current.value)] = current;
                current = current.next;
            }
        }
    }

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

    @Override
    public boolean contains(T element) {
        if (this.setArray[hash(element)] != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count(T element) {
        Node elem = (Node) this.setArray[hash(element)];
        if (elem != null) {
            return elem.occurences;
        } else {
            return 0;
        }
    }

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

            while(temp != null && !temp.value.equals(element)) {
                prev = temp;
                temp = temp.next;
            }

            if (elem.occurences == 0) {
                prev.next = temp.next;
                distinctCount--;
                this.setArray[hash(element)] = null;
            }

            // Need to fully remove item here I think
            this.size -= count;

        } else {
            throw new NoSuchElementException();
        }
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

    @Override
    public void add(T element) {
        add(element, 1);
    }

    @Override
    public void remove(T element) throws NoSuchElementException {
        remove(element, 1);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int internalCapacity() {
        return this.initialCapacity;
    }

    @Override
    public int distinctCount() {
        return this.distinctCount;
    }
}