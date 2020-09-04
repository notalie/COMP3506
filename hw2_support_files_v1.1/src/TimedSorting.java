import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class TimedSorting {
    private int[] arraySizes = {5, 10, 50, 100, 500, 1000, 10000};
    Random rand = new Random();


    private <T extends Comparable> T[] populateArray(int arraySize) {
        T[] array = (T[]) new Comparable[arraySize];
        for (int i = 0 ; i < arraySize; i++) {
            array[i] = (T)(Integer)rand.nextInt();
        }
        return array;
    }

    public <T extends Comparable> void timeArray(T[] array) {
        // Selection Sort
        T[] copiedArray = array.clone();
        long startTime = System.nanoTime();
        SortingAlgorithms.selectionSort(copiedArray, false);
        long endTime = System.nanoTime();
        System.out.println("Selection Sort," + ((endTime - startTime)/1000000.0));

        // Insertion Sort
        copiedArray = array.clone();
        startTime = System.nanoTime();
        SortingAlgorithms.insertionSort(copiedArray, false);
        endTime = System.nanoTime();
        System.out.println("Insertion Sort," + ((endTime - startTime)/1000000.0));

        // Merge Sort
        copiedArray = array.clone();
        startTime = System.nanoTime();
        SortingAlgorithms.mergeSort(copiedArray, false);
        endTime = System.nanoTime();
        System.out.println("Merge Sort," + ((endTime - startTime)/1000000.0));

        // Quicksort
        copiedArray = array.clone();
        startTime = System.nanoTime();
        SortingAlgorithms.quickSort(copiedArray, false);
        endTime = System.nanoTime();
        System.out.println("Quick Sort," + ((endTime - startTime)/1000000.0));
    }


    public <T extends Comparable> void unsortedTimes() {
        for (int n = 0; n < this.arraySizes.length; n++) {
            T[] array = this.populateArray(arraySizes[n]);
            System.out.println(arraySizes[n] + " elements");
            timeArray(array);
        }
    }

    public <T extends Comparable> void ascendingTimes() {
        for (int n = 0; n < this.arraySizes.length; n++) {
            T[] array = this.populateArray(arraySizes[n]);
            System.out.println(arraySizes[n] + " elements");
            Arrays.sort(array, null);
            timeArray(array);
        }
    }


    public <T extends Comparable> void descendingTimes() {
        for (int n = 0; n < this.arraySizes.length; n++) {
            T[] array = this.populateArray(arraySizes[n]);
            System.out.println(arraySizes[n] + " elements");
            Arrays.sort(array, Collections.reverseOrder());
            timeArray(array);
        }
    }



    public static void main(String[] args) {
        TimedSorting ts = new TimedSorting();
        ts.descendingTimes();
    }
}
