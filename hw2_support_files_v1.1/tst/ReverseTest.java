import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

public class ReverseTest {
    private SimpleArrayDeque<Integer> arrayDeque;
    private SimpleArrayDeque<Integer> emptyDeque;
    private SimpleLinkedDeque<Integer> linkedDeque;
    private Random rand;

    @Before
    public void setUp() {
        arrayDeque = new SimpleArrayDeque<>(10);
        arrayDeque.pushLeft(5);
        arrayDeque.pushLeft(4);

        linkedDeque = new SimpleLinkedDeque<>(10);
        linkedDeque.pushRight(7);
        linkedDeque.pushRight(10);

        emptyDeque = new SimpleArrayDeque<>(5);
        rand = new Random();
    }

    @Test
    public void testReverseSingleElement() {
        SimpleArrayDeque<Integer> otherDeque = new SimpleArrayDeque<>(10);
        Integer[] expected = new Integer[]{5, 4, 3, 2, 1};
        ReversibleDeque<Integer> deque = new ReversibleDeque<>(otherDeque);
        for (Integer num : expected) {
            deque.pushLeft(num);
        }

        deque.reverse();
        assertEquals(Integer.valueOf(5), deque.popLeft());
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvalidPeekLeftReversibleDeque() {
        ReversibleDeque<Integer> deque = new ReversibleDeque<>(emptyDeque);
        deque.peekLeft();
    }

    @Test
    public void testReverseIteratorReversibleDeque() {
        Integer[] arr = new Integer[15];
        SimpleLinkedDeque<Integer> data = new SimpleLinkedDeque<>();
        for (int i = 0; i < 15; i++) {
            Integer num = rand.nextInt(20);
            arr[i] = num;
            data.pushLeft(num);
        }

        ReversibleDeque<Integer> deque = new ReversibleDeque<>(data);
        Iterator<Integer> iter = deque.reverseIterator();
        Integer[] actual = new Integer[arr.length];
        int i = 0;
        while (iter.hasNext()) {
            actual[i++] = iter.next();
        }

        assertArrayEquals(arr, actual);
    }

    @Test
    public void sizeReversibleDeque() {
        ReversibleDeque<Integer> deque = new ReversibleDeque<>(arrayDeque);
        assertEquals(2, deque.size());
    }

    @Test
    public void testReversiblePushPop() {
        SimpleArrayDeque<Integer> otherDeque = new SimpleArrayDeque<>(10);
        Integer[] expected = new Integer[]{5, 4, 3, 2, 1};
        for (Integer num : expected) {
            otherDeque.pushLeft(num);
        }
        ReversibleDeque<Integer> deque = new ReversibleDeque<>(otherDeque);
        Integer[] arr = new Integer[5];
        int i = 0;
        while (!deque.isEmpty()) {
            arr[i++] = deque.popRight();
        }

        assertArrayEquals(expected, arr);
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvalidPopLeftReversibleDeque() {
        ReversibleDeque<Integer> deque = new ReversibleDeque<>(emptyDeque);
        deque.popLeft();
    }

    @Test(expected = RuntimeException.class)
    public void pushLeftReversibleDequeFull() {
        SimpleDeque<String> data = new SimpleLinkedDeque<>(1);
        ReversibleDeque<String> deque = new ReversibleDeque<>(data);
        deque.pushLeft("hello");
        deque.pushLeft("world");
    }

    @Test
    public void isFullReversibleDequeDataUnchanged() {
        SimpleDeque<String> data = new SimpleLinkedDeque<>(1);
        data.pushLeft("hello");
        ReversibleDeque<String> deque = new ReversibleDeque<>(data);
        assertTrue(deque.isFull());
    }

    @Test
    public void testIteratorReversibleDeque() {
        Integer[] arr = new Integer[15];
        SimpleLinkedDeque<Integer> data = new SimpleLinkedDeque<>();
        for (int i = 0; i < 15; i++) {
            Integer num = rand.nextInt(20);
            arr[i] = num;
            data.pushRight(num);
        }

        ReversibleDeque<Integer> deque = new ReversibleDeque<>(data);
        Iterator<Integer> iter = deque.iterator();
        Integer[] actual = new Integer[arr.length];
        int i = 0;
        while (iter.hasNext()) {
            actual[i++] = iter.next();
        }

        assertArrayEquals(arr, actual);
    }
}
