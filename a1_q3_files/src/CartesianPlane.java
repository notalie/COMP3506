/**
 * A 2D cartesian plane (i.e. a grid) data structure that holds items in a
 * position. Each (x,y) coordinate can hold a single item of type <T>.
 *
 * x and y can potentially be negative
 *
 * @param <T> The type of element held in the data structure
 */
public interface CartesianPlane<T> {

    /**
     * Add an element at a fixed position, overriding any existing element
     * there.
     *
     * @param x The x-coordinate of the element's position
     * @param y The y-coordinate of the element's position
     * @param element The element to be added at the indicated
     *         position
     * @throws IllegalArgumentException If the x or y value is out of
     *         the grid's minimum/maximum bounds
     */
    public void add(int x, int y, T element) throws IllegalArgumentException;

    /**
     * Returns the element at the indicated position.
     *
     * @param x The x-coordinate of the element to retrieve
     * @param y The y-coordinate of the element to retrieve
     * @return The element at this position, or null is no elements exist
     * @throws IndexOutOfBoundsException If the x or y value is out of
     *         the grid's minimum/maximum bounds
     */
    public T get(int x, int y) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the indicated position.
     *
     * @param x The x-coordinate of the element to remove
     * @param y The y-coordinate of the element to remove
     * @return true if an element was successfully removed, false if no element
     *         exists at (x, y)
     * @throws IndexOutOfBoundsException If the x or y value is out of
     *         the grid's minimum/maximum bounds
     */
    public boolean remove(int x, int y) throws IndexOutOfBoundsException;

    /**
     * Removes all elements stored in the grid.
     */
    public void clear();

    /**
     * Changes the size of the grid. Existing elements should remain at the
     * same (x, y) coordinate If a resizing operation has invalid dimensions or
     * causes an element to be lost, the grid should remain unmodified and an
     * IllegalArgumentException thrown
     *
     * @param newMinimumX A new minimum bound for the x values of
     *         elements.
     * @param newMaximumX A new maximum bound for the x values of
     *         elements.
     * @param newMinimumY A new minimum bound for the y values of
     *         elements.
     * @param newMaximumY A new maximum bound for the y values of
     *         elements.
     * @throws IllegalArgumentException if the x minimum is greater
     *         than the x maximum (and resp. with y min/max) or if an element
     *         would be lost after this resizing operation
     */
    public void resize(int newMinimumX, int newMaximumX, int newMinimumY,
            int newMaximumY) throws IllegalArgumentException;

}