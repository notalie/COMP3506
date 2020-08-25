import java.util.Arrays;

public class SortingAlgorithms {



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
                if (input[j].compareTo(input[i]) < 0 && !reversed) { // input[j] < input[i] - smallest to largest
                    elementIndex = j;
                } else if (input[j].compareTo(input[i]) > 0 && reversed){ // input[j] > input[i] - largest to smallest
                    elementIndex = j;
                }
            }
            if (elementIndex != i) {
                T temp = input[elementIndex]; // keep a pointer to the element
                input[elementIndex] = input[i];
                input[i] = temp;
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
                while (holePosition > 0 && input[holePosition - 1 ].compareTo(valueToInsert) > 0) {
                    input[holePosition] = input[holePosition - 1];
                    holePosition -= 1;
                }
            } else { // Descending Order
                while (holePosition > 0 && input[holePosition - 1 ].compareTo(valueToInsert) < 0) {
                    input[holePosition] = input[holePosition - 1];
                    holePosition -= 1;
                }
            }

            input[holePosition] = valueToInsert;
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
        
    }

    public static <T extends Comparable> void test() {
        T[] aux = (T[]) new Comparable[5];
        Integer[] toCompare = {-1,4,3,90,-5};
        for (int i = 0; i < toCompare.length; i++) {
            aux[i] = (T)toCompare[i];
        }

        mergeSort(aux, false);
        System.out.println(Arrays.toString(aux));
        mergeSort(aux, true);
        System.out.println(Arrays.toString(aux));
    }


    public static void main(String[] args) {
        test();
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
        
    }
}
