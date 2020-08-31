import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
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
    public void sizeElementsArrayDeque() {
        arrayDeque.pushRight(2);
        assertEquals(1, arrayDeque.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void popLeftArrayDequeEmpty() {
        emptyDeque.popLeft();
    }

    @Test
    public void isFullArrayDeque() {
        SimpleDeque<String> deque = new SimpleArrayDeque<>(1);
        deque.pushLeft("hello");
        assertTrue(deque.isFull());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidConstructorNegLinkedDeque() {
        new SimpleArrayDeque<Integer>(-2);
    }

    @Test
    public void isEmptyArrayDeque() {
        SimpleDeque<Integer> deque = new SimpleArrayDeque<>(20);
        assertTrue(deque.isEmpty());

        deque.pushRight(1);
        assertFalse(deque.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void peekLeftArrayDequeEmpty() {
        emptyDeque.peekLeft();
    }

    @Test
    public void peekLeftArrayDequeSingleElement() {
        SimpleDeque<String> deque = new SimpleArrayDeque<>(1);
        deque.pushRight("hello");
        int priorSize = deque.size();
        assertEquals("hello", deque.peekLeft());
        assertEquals(priorSize, deque.size());
    }

    @Test(expected = RuntimeException.class)
    public void pushLeftArrayDequeFull() {
        SimpleDeque<String> deque = new SimpleArrayDeque<>(1);
        deque.pushLeft("hello");
        deque.pushLeft("world");
    }

    @Test
    public void iterator() {
        Integer[] nums = {1, 2, 3, 4};
        arrayDeque.pushLeft(1);
        arrayDeque.pushLeft(2);
        arrayDeque.pushLeft(3);
        arrayDeque.pushLeft(4);

        Iterator<Integer> iterator = arrayDeque.iterator();
        int index = nums.length - 1;
        while(iterator.hasNext()) {
            Integer elem = iterator.next();
            if (elem != null) {
                Assert.assertEquals(elem, nums[index--]);
            }
        }
    }

    @Test
    public void reverseIterator() {
        Integer[] nums = {1, 2, 3, 4};
        arrayDeque.pushRight(1);
        arrayDeque.pushRight(2);
        arrayDeque.pushRight(3);
        arrayDeque.pushRight(4);

        int index = 0;
        Iterator<Integer> iterator = arrayDeque.reverseIterator();
        while(iterator.hasNext()) {
            Integer elem = iterator.next();
            if (elem != null) {
                Assert.assertEquals(elem, nums[index++]);
            }
        }
    }

    @Test
    public void iteratorArrayDeque() {
        SimpleArrayDeque<Integer> deque = new SimpleArrayDeque<>(10);
        Integer[] arr = new Integer[8];
        for (int i = 0; i < 7; i++) {
            arr[i] = rand.nextInt(10);
            deque.pushRight(arr[i]);
        }
        Iterator<Integer> iter = deque.iterator();
        Integer[] arr2 = new Integer[8];
        int i = 0;
        while (iter.hasNext()) {
            arr2[i++] = iter.next();
        }
        assertArrayEquals(arr2, arr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidConstructorNegArrayDeque() {
        new SimpleArrayDeque<Integer>(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidConstructorArrayDequeOther() {
        arrayDeque.pushLeft(2);
        arrayDeque.pushLeft(3);
        new SimpleArrayDeque<>(1, arrayDeque);
    }

    @Test
    public void validConstructorArrayDequeSameType() {
        SimpleArrayDeque<Integer> fullDeque = new SimpleArrayDeque<>(10);
        fullDeque.pushLeft(5);
        fullDeque.pushLeft(4);
        fullDeque.iterator();

        SimpleArrayDeque<Integer> other = new SimpleArrayDeque<>(fullDeque.size(), fullDeque);
        assertTrue(other.isFull());
    }

    @Test
    public void reverseIteratorArrayDeque() {
        SimpleArrayDeque<Integer> deque = new SimpleArrayDeque<>(10);
        Integer[] arr = new Integer[8];
        for (int i = 0; i < 7; i++) {
            arr[i] = rand.nextInt(10);
            deque.pushLeft(arr[i]);
        }

        Iterator<Integer> iter = deque.reverseIterator();
        Integer[] arr2 = new Integer[8];
        int i = 0;
        while (iter.hasNext()) {
            arr2[i++] = iter.next();
        }
        assertArrayEquals(arr2, arr);
    }
}
