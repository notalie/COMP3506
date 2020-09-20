import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class QuaternaryHeapsortTest {

    @Test
    public void testSort() {
        Integer[] input = {5,1,100,2,0};

        QuaternaryHeapsort.quaternaryHeapsort(input);

        assertArrayEquals(new Integer[] { 0, 1, 2, 5, 100 }, input);
    }

    @Test
    public void testSort2() {
        String[] input = {"c", "a", "e", "b", "z"};

        QuaternaryHeapsort.quaternaryHeapsort(input);

        assertArrayEquals(new String[] { "a", "b", "c", "e", "z" }, input);
    }
    
    @Test
    public void testDownheap() {
        
        Integer[] input = {0, 10, 20, 30, 40};
        
        QuaternaryHeapsort.quaternaryDownheap(input, 0, input.length);
        
        assertArrayEquals(new Integer[] {40, 10, 20, 30, 0}, input);
    }
    
    @Test
    public void testDownHeap2() {
        Integer[] input = {1, 0, 2, 3, 4, 10, 20, 30, 40};
        
        QuaternaryHeapsort.quaternaryDownheap(input, 1, input.length);
        
        assertArrayEquals(new Integer[] { 1, 40, 2, 3, 4, 10, 20, 30, 0 }, input);
    }

    @Test
    public void testDownheap3() {

        Integer[] input = {0, 10, 20, 30, 40};

        QuaternaryHeapsort.quaternaryDownheap(input, 0, 1);

        // no swaps!
        assertArrayEquals(new Integer[] {0, 10, 20, 30, 40}, input);
    }

    @Test
    public void testDownheap4() {
        Integer[] input = {0, 10, 20, 30, 40};

        QuaternaryHeapsort.quaternaryDownheap(input, 0, 4);

        // 40 is considered for swapping
        assertArrayEquals(new Integer[] {30, 10, 20, 0, 40}, input);
    }

    @Test
    public void testDownHeap5() {
        Integer[] input = {1, 0, 2, 3, 4, 10, 20, 30, 40};

        QuaternaryHeapsort.quaternaryDownheap(input, 0, 5);

        // we only look at the first node's children, and swap with the largest
        assertArrayEquals(new Integer[] { 4, 0, 2, 3, 1, 10, 20, 30, 40 }, input);
    }

    @Test
    public void testDownHeap6() {
        Integer[] input = {1, 0, 2, 3, 4, 10, 20, 30, 40};

        QuaternaryHeapsort.quaternaryDownheap(input, 0, 4);

        // we only look at the first node's children, and swap with the largest within our size range
        assertArrayEquals(new Integer[] { 3, 0, 2, 1, 4, 10, 20, 30, 40 }, input);
    }

}
