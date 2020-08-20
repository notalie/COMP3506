import java.math.*;
/**
 * A 2D cartesian plane implemented as with an array. Each (x,y) coordinate can
 * hold a single item of type <T>.
 *
 * @param <T> The type of element held in the data structure
 */
public class ArrayCartesianPlane<T> implements CartesianPlane<T> {

    /* A 2D array for all the items in the cartesian plane */
    private T planeCoordinates[][];

    /* The minimum x of the cartesian plane */
    private int minimumX;

    /* The minimum y of the cartesian plane */
    private int minimumY;

    /* The maximum x of the cartesian plane */
    private int maximumX;

    /* The maximum y of the cartesian plane */
    private int maximumY;

    /**
     * Constructs a new ArrayCartesianPlane object with given minimum and
     * maximum bounds.
     *
     * Note that these bounds are allowed to be negative.
     *
     * @param minimumX A new minimum bound for the x values of
     *         elements.
     * @param maximumX A new maximum bound for the x values of
     *         elements.
     * @param minimumY A new minimum bound for the y values of
     *         elements.
     * @param maximumY A new maximum bound for the y values of
     *         elements.
     * @throws IllegalArgumentException if the x minimum is greater
     *         than the x maximum (and resp. with y min/max)
     */

    public ArrayCartesianPlane(int minimumX, int maximumX, int minimumY,
            int maximumY) throws IllegalArgumentException {
        if (minimumX > maximumX || minimumY > maximumY) {
            throw new IllegalArgumentException();
        }
        this.minimumX = minimumX;
        this.minimumY = minimumY;
        this.maximumX = maximumX;
        this.maximumY = maximumY;
        this.planeCoordinates = (T[][])new Object[(maximumX - minimumX) + 1][(maximumY - minimumY) + 1];

    }

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
    public void add(int x, int y, T element) throws IllegalArgumentException {
        if (x > maximumX || x < minimumX || y > maximumY || y < minimumY) {
            throw new IllegalArgumentException();
        } else {
            this.planeCoordinates[ Math.abs(this.minimumX - x)][Math.abs(this.minimumY - y)] = element;
        }
    }

    /**
     * Retrieves the element from the coordinates set
     * @param x The x-coordinate of the element to retrieve
     * @param y The y-coordinate of the element to retrieve
     * @return The element from the coordinates specified
     * @throws IndexOutOfBoundsException when the index for either x or y is out of the set bounds
     */
    public T get(int x, int y) throws IndexOutOfBoundsException {
        if (x > maximumX || x < minimumX || y > maximumY || y < minimumY) {
            throw new IndexOutOfBoundsException();
        } else {
            return this.planeCoordinates[ Math.abs(this.minimumX - x)][Math.abs(this.minimumY - y)];
        }
    }

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
    public boolean remove(int x, int y) throws IndexOutOfBoundsException {
        if (x > maximumX || x < minimumX || y > maximumY || y < minimumY) {
            throw new IndexOutOfBoundsException();
        } else if (this.planeCoordinates[x][y] != null) {
            add(x, y, null);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes all elements stored in the grid.
     */
    public void clear() {
        for (int x = this.minimumX; x < maximumX; x++) {
            for (int y = this.minimumY; y < maximumY; y++) {
                this.planeCoordinates[x][y] = null;
            }
        }
    }

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
                       int newMaximumY) throws IllegalArgumentException {
        if (newMinimumX > this.maximumX || newMaximumX < this.minimumX || newMinimumY > this.maximumY ||
                newMaximumY < this.minimumY) {
            throw new IllegalArgumentException();
        } else if (newMinimumX > this.minimumX || newMaximumX < this.maximumX || newMinimumY > this.minimumY ||
                    newMaximumY < this.maximumY) {
            for (int x = Math.abs(this.minimumX); x <= Math.abs(this.maximumX); x++) {
                for (int y = Math.abs(this.minimumY); y <= Math.abs(this.maximumY); y++) {
                    if (get(x, y) != null && (
                            (x > newMaximumX || y > newMaximumY || x < newMinimumX || y < newMinimumY))) {
                        throw new IllegalArgumentException();
                    }
                }
            }
        }
        this.maximumX = newMaximumX;
        this.maximumY = newMaximumY;
        this.minimumX = newMinimumX;
        this.minimumY = newMinimumY;

        T[][] tempCoords = this.planeCoordinates;

        this.planeCoordinates = (T[][])new Object[(newMaximumX - newMinimumX) + 1][(newMaximumY - newMinimumY) + 1];

        for (int x = 0; x < this.maximumX; x++) {
            for (int y = 0; y < this.maximumY; y++) {
                add(x, y, tempCoords[x][y]);
            }
        }
    }
}

