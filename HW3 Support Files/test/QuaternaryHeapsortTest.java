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
        Integer[] input = {1, 2, 3, 4, 0, 10, 20, 30, 40};
        
        QuaternaryHeapsort.quaternaryDownheap(input, 4);
        
        assertArrayEquals(new Integer[] { 1, 2, 3, 4, 40, 10, 20, 30, 0 }, input);
    }
    
}
