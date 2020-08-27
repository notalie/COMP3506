
public class SortingAlgorithms {
    /**
     * Switches the objects contained within two indexes
     * @param input - the array containing a bunch of comparable objects
     * @param elementIndex - the first index number of the element to switch out
     * @param toSwitchIndex - the second index number of the element to
     *                      switch out
     */
    private static <T extends Comparable> void swap(T[] input, int elementIndex,
                int toSwitchIndex) {
        T temp = input [elementIndex];
        input[elementIndex] = input[toSwitchIndex];
        input[toSwitchIndex] = temp;
    }

    /**
     * Sorts the given array using the selection sort algorithm.
     * This should modify the array in-place.
     *
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void selectionSort(T[] input, boolean reversed) {
        for (int i = 0; i < input.length - 1; i++) {
            int elementIndex = i;
            for (int j = i + 1; j < input.length; j++) {
                if (input[j].compareTo(input[elementIndex]) < 0 && !reversed) { 
                    // input[j] < input[i] - smallest to largest
                    elementIndex = j;
                } else if (input[j].compareTo(input[elementIndex]) > 0 && 
                        reversed) { 
                    // input[j] > input[i] - largest to smallest
                    elementIndex = j;
                }
            }
            if (elementIndex != i) {
                swap(input, elementIndex, i);
            }
        }
    }


    /**
     * Sorts the given array using the insertion sort algorithm.
     * This should modify the array in-place.
     *
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void insertionSort(T[] input, boolean reversed) {
        for (int i = 0; i < input.length; i++) {
            T valueToInsert = input[i];
            int holePosition = i;
            if (!reversed) { // Ascending Order
                while (holePosition > 0 && 
                        input[holePosition - 1 ].compareTo(valueToInsert) > 0) {
                    input[holePosition] = input[holePosition - 1];
                    holePosition -= 1;
                }
            } else { // Descending Order
                while (holePosition > 0 && 
                        input[holePosition - 1 ].compareTo(valueToInsert) < 0) {
                    input[holePosition] = input[holePosition - 1];
                    holePosition -= 1;
                }
            }
            input[holePosition] = valueToInsert;
        }
    }

    /**
     * Mergest the two arrays from the right and left side and modifies the main
     * array passed in
     * @param input - the array of comparable objects
     * @param left - the left index of the array to merge
     * @param middle - the middle index of the merge array
     * @param right - the right side index of the array to merge
     * @param reversed - If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     **/
    private static <T extends Comparable> void merge(T[] input, int left, 
                int middle, int right, boolean reversed) {
        int n1 = middle - left + 1; // size of first half of A
        int n2 = right - middle; // size of second half of A
        T[] L = (T[]) new Comparable[n1];
        T[] R = (T[]) new Comparable[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = input[left + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = input[middle + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            // L[i] <= R[j] for ascending
            if (L[i].compareTo(R[j]) <= 0 && !reversed) {
                input[k++] = L[i++];
            } else if (L[i].compareTo(R[j]) > 0 && reversed) { // descending
                input[k++] = L[i++];
            } else { // add it otherwise to the right side
                input[k++] = R[j++];
            }
        }

        while (i < n1) { // Add left side to input
            input[k++] = L[i++];
        }

        while (j < n2) { // Add right side to input
            input[k++] = R[j++];
        }
    }

    /**
     * Recursive helper method that breaks down the inputs until the
     *  right index >= left index
     * @param input - an array of comparable objects
     * @param left - the left side index
     * @param right - the right side index
     * @param reversed - If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     **/
    private static <T extends Comparable> void mergeSortRecurse(T[] input, 
                int left, int right, boolean reversed) {
        if (left < right)  {
            int middle = (left + right) / 2;
            mergeSortRecurse(input, left, middle, reversed);
            mergeSortRecurse(input, middle + 1, right, reversed);
            merge(input, left, middle, right, reversed);
        }
    }

    /**
     * Sorts the given array using the merge sort algorithm.
     * This should modify the array in-place.
     * 
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void mergeSort(T[] input, boolean reversed) {
        mergeSortRecurse(input, 0, input.length - 1, reversed);
    }


    /**
     * A recursive inplace quicksort helper function called from the original
     * quicksort function. It can reverse or maintain order.
     * @param input - an array of comparable objects
     * @param left - the left index of the array
     * @param right - the right index of the array
     * @param reversed - If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     */
    private static <T extends Comparable> void inPlaceQuickSort(T[] input, 
                int left, int right, boolean reversed) {
        if (left >= right) { // base case
            return;
        }
        T pivot = input[left + (right - left) / 2];
        int h = left;
        int k = right;
        while (h <= k) { // in place partitioning

            if (!reversed) { // Ascending order
                // keep going while h < pivot
                while (input[h].compareTo(pivot) < 0) {
                    h++;
                }
                // keep going while k > pivot
                while (input[k].compareTo(pivot) > 0) {
                    k--;
                }
            } else { // Descending order
                // keep going while h >= pivot
                while (input[h].compareTo(pivot) > 0) {
                    h++;
                }
                // keep going while k < pivot
                while (input[k].compareTo(pivot) < 0) {
                    k--;
                }
            }
            if (h <= k) {
                swap(input, h++, k--);
            }
        }
        if (left < k) {
            inPlaceQuickSort(input, left, k, reversed);
        }
        if (h < right) {
            inPlaceQuickSort(input, h, right, reversed);
        }
    }


    /**
     * Sorts the given array using the quick sort algorithm.
     * This should modify the array in-place.
     * 
     * You should use the value at the middle of the input  array(i.e. floor(n/2)) 
     * as the pivot at each step.
     * 
     * @param input An array of comparable objects.
     * @param reversed If false, the array should be sorted ascending.
     *                 Otherwise, it should be sorted descending.
     * @requires input != null
     */
    static <T extends Comparable> void quickSort(T[] input, boolean reversed) {
        inPlaceQuickSort(input, 0, input.length -  1, reversed);
    }
}
