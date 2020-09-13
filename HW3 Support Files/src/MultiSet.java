import java.util.NoSuchElementException;

/**
 * A collection that behaves like a set, but that can contain duplicate elements.
 * Elements in the multiset have an associated 'occurrence' or 'count' that describes
 * how many duplicates (as decided by .equals()) of the same element  are in the collection.
 *
 * A multiset is sometimes called a "bag".
 * 
 * @param <T> the type of elements in the collection.
 */
public interface MultiSet<T> {

    /**
     * Adds the element to the set. If an equal element is already in the set,
     * increases its occurrence count by 1.
     *
     * @param element to add
     * @require element != null
     */
    void add(T element);

    /**
     * Adds count to the number of occurrences of the element in set.
     *
     * @param element to add
     * @require element != null && count >= 0
     */
    void add(T element, int count);

    /**
     * Checks if the element is in the set (at least once).
     *
     * @param element to check
     * @return true if the element is in the set, else false.
     */
    boolean contains(T element);

    /**
     * Returns the count of how many occurrences of the given elements there
     * are currently in the set.
     *
     * @param element to check
     * @return the count of occurrences of element
     */
    int count(T element);

    /**
     * Removes a single occurrence of element from the set.
     *
     * @param element to remove
     * @throws NoSuchElementException if the set doesn't currently
     *         contain the given element
     * @require element != null
     */
    void remove(T element) throws NoSuchElementException;

    /**
     * Removes several occurrences of the element from the set.
     *
     * @param element to remove
     * @param count the number of occurrences of element to remove
     * @throws NoSuchElementException if the set contains less than
     *         count occurrences of the given element
     * @require element != null && count >= 0
     */
    void remove(T element, int count) throws NoSuchElementException;

    /**
     * Returns the total count of all elements in the multiset.
     *
     * Note that duplicates of an element all contribute to the count here.
     *
     * @return total count of elements in the collection
     */
    int size();

    /**
     * Returns the number of distinct elements currently stored in the set.
     *
     * @return count of distinct elements in the set
     */
    int distinctCount();

    /**
     * Returns the maximum number of *distinct* elements the internal data
     * structure can contain before resizing.
     *
     * @return capacity of internal array
     */
    int internalCapacity();
}

