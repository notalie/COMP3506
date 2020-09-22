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

        private int occurences;

        private Node(T value, int occurences) {
            this.value = value;
            this.occurences = occurences;
        }

        private void remove(int amountToRemove) {
            occurences -= amountToRemove;
        }

        private void add(int amountToAdd) {
            occurences += amountToAdd;
        }
    }

    // TODO: implement question 4 in this file
    private int initialCapacity;

    private Object[] setArray;

    public LinkedMultiHashSet(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        setArray =  new Object[initialCapacity];
    }

    private int getHash(T element) {
        int hash = element.hashCode() % internalCapacity();
        while(true) {
            Node value = (Node) this.setArray[hash];
            // Need to fix to account for null pointers
            if (this.setArray[hash] != null && value.value == element) {
                return hash;
            } else if (this.setArray[hash] == null) {
                return hash;
            } else {
                hash = (hash + 1) % internalCapacity();
            }
        }
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
        System.out.println(getHash(element));
        this.setArray[getHash(element)] = new Node(element, 1);
        System.out.println(getHash(element));
        System.out.println(this.setArray);
    }

    @Override
    public void add(T element, int count) {
        
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public int count(T element) {
        return 0;
    }

    @Override
    public void remove(T element) throws NoSuchElementException {

    }

    @Override
    public void remove(T element, int count) throws NoSuchElementException {

    }

    @Override
    public int size() {
        return -1;
    }

    @Override
    public int internalCapacity() {
        return this.initialCapacity;
    }

    @Override
    public int distinctCount() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}