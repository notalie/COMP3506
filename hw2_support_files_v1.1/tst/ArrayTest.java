import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;
public class ArrayTest {
    private SimpleArrayDeque<Integer> arrayDeque;
    private SimpleArrayDeque<Integer> emptyDeque;
    private Random rand;

    @Before
    public void setUp() {
        arrayDeque = new SimpleArrayDeque<>(10);
        emptyDeque = new SimpleArrayDeque<>(5);
        rand = new Random();
    }

    @Test
    public void testAddPopLeft() {
        arrayDeque.pushLeft(1);
        arrayDeque.pushLeft(2);
        Assert.assertEquals(arrayDeque.peekLeft(), Integer.valueOf(2));
        Assert.assertEquals(arrayDeque.popLeft(), Integer.valueOf(2));
    }

    @Test
    public void testAddPopRight() {
        arrayDeque.pushRight(1);
        arrayDeque.pushRight(2);
        Assert.assertEquals(arrayDeque.peekRight(), Integer.valueOf(2));
        Assert.assertEquals(arrayDeque.popRight(), Integer.valueOf(2));
    }

    @Test
    public void iterator() {
        Integer[] nums = {1, 2, 3, 4};
        arrayDeque.pushLeft(1);
        arrayDeque.pushLeft(2);
        arrayDeque.pushLeft(3);
        arrayDeque.pushLeft(4);

        Iterator<Integer> iterator = arrayDeque.iterator();
       int index = 0;
        while(iterator.hasNext()) {
            Assert.assertEquals(arrayDeque.popRight(), nums[index++]);
        }
    }

    @Test
    public void reverseIterator() {
        Integer[] nums = {1, 2, 3, 4};
        arrayDeque.pushRight(1);
        arrayDeque.pushRight(2);
        arrayDeque.pushRight(3);
        arrayDeque.pushRight(4);

        Iterator<Integer> iterator = arrayDeque.iterator();
        int index = nums.length - 1;
        while(iterator.hasNext()) {
            Assert.assertEquals(arrayDeque.popRight(), nums[index--]);
        }
    }
}
