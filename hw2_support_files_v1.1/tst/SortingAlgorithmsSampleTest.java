import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * DISCLAIMER (Please read carefully):
 *
 * Please note that these tests are **NOT** comprehensive. We advise that you
 * not only add tests of your own, but also make use of the coverage tool
 * available on IntelliJ (the one that looks like a shield with the green run
 * button at the bottom). There are many more tests in the marking suite, so
 * we highly recommend you test all sorts of edge cases as much as possible.
 *
 * If there are any inconsistencies between the spec / javadoc and the tests
 * please let the teaching staff know on Piazza.
 */
public class SortingAlgorithmsSampleTest {
    private Integer[] unsorted;
    private Integer[] sorted;
    private String[] sortedAscending;

    @Before
    public void setUp() {
        unsorted = new Integer[]{5, 10, 9, 2, 1, 4, 0};
        sorted = Arrays.copyOf(unsorted, unsorted.length);
        Arrays.sort(sorted);

        sortedAscending = new String[]{"apple", "banana", "camel", "dog",
                "duck", "elephant", "fun", "giraffe", "gorilla", "hello",
                "hey", "hi", "world"};
    }

    /* --------------------------- SELECTION SORT --------------------------- */

    @Test
    public void testSelectionSortUnsorted() {
        SortingAlgorithms.selectionSort(unsorted, false);
        assertArrayEquals(unsorted, sorted);
    }

    @Test
    public void testSelectionSortUnsortedReverse() {
        SortingAlgorithms.selectionSort(unsorted, true);
        int j = 0;
        for (int i = unsorted.length - 1; i >= 0; i--) {
            if (!(sorted[i].equals(unsorted[j++]))) {
                fail();
            }
        }
    }

    @Test
    public void testSelectionSortSortedAscending() {
        String[] toSort = Arrays.copyOf(sortedAscending, sortedAscending.length);
        SortingAlgorithms.selectionSort(toSort, false);

        assertArrayEquals(toSort, sortedAscending);
    }

    /* --------------------------- INSERTION SORT -------------------------- */

    @Test
    public void testInsertionSortUnsorted() {
        SortingAlgorithms.insertionSort(unsorted, false);
        assertArrayEquals(unsorted, sorted);
    }

    @Test
    public void testInsertionSortUnsortedReverse() {
        SortingAlgorithms.insertionSort(unsorted, true);
        int j = 0;
        for (int i = unsorted.length - 1; i >= 0; i--) {
            if (!(sorted[i].equals(unsorted[j++]))) {
                fail();
            }
        }
    }

    @Test
    public void testInsertionSortSortedAscending() {
        String[] toSort = Arrays.copyOf(sortedAscending, sortedAscending.length);
        SortingAlgorithms.insertionSort(toSort, false);

        assertArrayEquals(toSort, sortedAscending);
    }

    /* -------------------------------- MERGE SORT -------------------------- */

    @Test
    public void testMergeSortUnsorted() {
        SortingAlgorithms.mergeSort(unsorted, false);
        assertArrayEquals(unsorted, sorted);
    }

    @Test
    public void testMergeSortUnsortedReverse() {
        SortingAlgorithms.mergeSort(unsorted, true);
        int j = 0;
        for (int i = unsorted.length - 1; i >= 0; i--) {
            if (!(sorted[i].equals(unsorted[j++]))) {
                fail();
            }
        }
    }

    @Test
    public void testMergeSortSortedAscending() {
        String[] toSort = Arrays.copyOf(sortedAscending, sortedAscending.length);
        SortingAlgorithms.mergeSort(toSort, false);

        assertArrayEquals(toSort, sortedAscending);
    }

    /* -------------------------------- QUICK SORT -------------------------- */

    @Test
    public void testQuickSortUnsorted() {
        SortingAlgorithms.quickSort(unsorted, false);
        assertArrayEquals(unsorted, sorted);
    }

    @Test
    public void testQuickSortUnsortedReverse() {
        SortingAlgorithms.quickSort(unsorted, true);
        int j = 0;
        for (int i = unsorted.length - 1; i >= 0; i--) {
            if (!(sorted[i].equals(unsorted[j++]))) {
                fail();
            }
        }
    }

    @Test
    public void testQuickSortSortedAscending() {
        String[] toSort = Arrays.copyOf(sortedAscending, sortedAscending.length);
        SortingAlgorithms.quickSort(toSort, false);

        assertArrayEquals(toSort, sortedAscending);
    }
}