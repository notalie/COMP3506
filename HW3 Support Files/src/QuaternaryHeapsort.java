public class QuaternaryHeapsort {
    /**
     * Gets the left index of a quaternary heap based on what was passed in
     * @param j - the index to find the left side for
     * @return an index responding to the left most index
     */
    private static int left(int j) {
        return 4 * j + 1;
    }

    /**
     * Gets the middle left index of a quaternary heap based on what was passed in
     * @param j - the index to find the middle left side for
     * @return an index responding to the middle left most index
     */
    private static int midLeft(int j) {
        return 4 * j + 2;
    }

    /**
     * Gets the middle right index of a quaternary heap based on what was passed in
     * @param j - the index to find the middle right side for
     * @return an index responding to the middle right most index
     */
    private static int midRight(int j) {
        return 4 * j + 3;
    }

    /**
     * Gets the right index of a quaternary heap based on what was passed in
     * @param j - the index to find the right side for
     * @return an index responding to the right most index
     */
    private static int right(int j) {
        return 4 * j + 4;
    }

    /**
     * Checks if there exists a leftmost element based on the passed in index
     * @param j - the index to check
     * @param size - the size of the heap
     * @return if there exists a leftmost element
     */
    private static boolean hasLeft(int j, int size) {
        return left(j) < size;
    }

    /**
     * Checks if there exists a middle leftmost element based on the passed in index
     * @param j - the index to check
     * @param size - the size of the heap
     * @return if there exists a leftmost element
     */
    private static boolean hasMidLeft(int j, int size) {
        return midLeft(j) < size;
    }

    /**
     * Checks if there exists a middle rightmost element based on the passed in index
     * @param j - the index to check
     * @param size - the size of the heap
     * @return if there exists a middle leftmost element
     */
    private static boolean hasMidRight(int j, int size) {
        return midRight(j) < size;
    }

    /**
     * Checks if there exists a rightmost element based on the passed in index
     * @param j - the index to check
     * @param size - the size of the heap
     * @return if there exists a rightmost element
     */
    private static boolean hasRight(int j, int size) {
        return right(j) < size;
    }

    /**
     * Swaps two elements within the heap array
     * @param i - the first element to switch
     * @param j - the second element to switch
     * @param heap - the heap array to change
     */
    private static <T extends Comparable<T>> void swap(int i, int j, T heap[]) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Sorts the input array, in-place, using a quaternary heap sort.
     *
     * @param input to be sorted (modified in place)
     */
    public static <T extends Comparable<T>> void quaternaryHeapsort(T[] input) {

        // Build the Max Heap
        int index = input.length / 2 - 1;
        while (index >= 0) {
            quaternaryDownheap(input, index--, input.length);
        }

        int heapSize = input.length - 1;
        while (heapSize >= 0) {
            swap(heapSize, 0, input);
            quaternaryDownheap(input, 0, heapSize--);
        }
    }

    /**
     * Performs a downheap from the element in the given position on the given max heap array.
     *
     * A downheap should restore the heap order by swapping downwards as necessary.
     * The array should be modified in place.
     *
     * @param input array representing a quaternary max heap.
     * @param start position in the array to start the downheap from.
     * @param size the size of the heap in the input array, starting from index 0
     */
    public static <T extends Comparable<T>> void quaternaryDownheap(T[] input, int start, int size) {
        while(hasLeft(start, size)) {
            int leftIndex = left(start);
            int smallChildIndex = leftIndex;

            // Check Right Value
            if (hasRight(start, size)) {
                int rightIndex = right(start);
                if (input[smallChildIndex].compareTo(input[rightIndex]) < 0) {
                    smallChildIndex = rightIndex;
                }
            }

            // Check MidLeft value
            if (hasMidLeft(start, size)) {
                int midLeftIndex = midLeft(start);
                if (input[smallChildIndex].compareTo(input[midLeftIndex]) < 0) {
                    smallChildIndex = midLeftIndex;
                }
            }
            // Check MidRight value
            if (hasMidRight(start, size)) {
                int midRightIndex = midRight(start);
                if (input[smallChildIndex].compareTo(input[midRightIndex]) < 0) {
                    smallChildIndex = midRightIndex;
                }
            }

            // Stops when a value is found that is smaller
            if (input[smallChildIndex].compareTo(input[start]) < 0) {
                break;
            }
            swap(start, smallChildIndex, input);
            start = smallChildIndex;
        }
    }
}
