/**
 * A 2D cartesian plane implemented as with an array. Each (x,y) coordinate can
 * hold a single item of type <T>.
 *
 * @param <T> The type of element held in the data structure
 */
public class ArrayCartesianPlane<T> implements CartesianPlane<T> {

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

    T data[][];


    public ArrayCartesianPlane(int minimumX, int maximumX, int minimumY,
            int maximumY) throws IllegalArgumentException {
        // TODO: implement the constructor
        int xlength;
        int ylength;
        this.data = (T[][])new Object[5][5];

    }

    // TODO: you are to implement all of ArrayCartesianPlanes's methods here
    public void add(int x, int y, T element) throws IllegalArgumentException {

    }

    public T get(int x, int y) throws IndexOutOfBoundsException {
        return this.data[x][y];
    }

    public boolean remove(int x, int y) throws IndexOutOfBoundsException {
        return false;
    }

    public void clear() {

    }

    public void resize(int newMinimumX, int newMaximumX, int newMinimumY,
                       int newMaximumY) throws IllegalArgumentException {

    }


    public static void main(String args []) {

    }





}

