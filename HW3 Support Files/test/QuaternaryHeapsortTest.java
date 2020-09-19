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
    public void testDownheap() {
        
        Integer[] input = {0, 10, 20, 30, 40};
        
        QuaternaryHeapsort.quaternaryDownheap(input, 0);
        
        assertArrayEquals(new Integer[] {40, 10, 20, 30, 0}, input);
    }
    
    @Test
    public void testDownHeap2() {
        Integer[] input = {1, 0, 2, 3, 4, 10, 20, 30, 40};
        
        QuaternaryHeapsort.quaternaryDownheap(input, 1);
        
        assertArrayEquals(new Integer[] { 1, 40, 2, 3, 4, 10, 20, 30, 0 }, input);
    }

    @Test
    public void testSort3() {
        Integer[] input = {5, 4, 9, 8, 3, 2, 6, 1};
        QuaternaryHeapsort.quaternaryHeapsort(input);
        assertArrayEquals(new Integer[]{1,2,3,4,5,6,8,9},input);
    }

    @Test
    public void testSort4() {
        Integer[] input = {9,28,15,4,2,12,21,7,29,3,23,27,8,24,19,10,1,11,17,30,6,5,13,20,14,25,16,22,18,26};
        QuaternaryHeapsort.quaternaryHeapsort(input);
        assertArrayEquals(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30}, input);
    }


}
