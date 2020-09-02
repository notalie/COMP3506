import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

public class LinkedTest {
    private SimpleArrayDeque<Integer> arrayDeque;
    private SimpleArrayDeque<Integer> emptyDeque;
    private SimpleLinkedDeque<Integer> linkedDeque;
    private Random rand;

    @Before
    public void setUp() {
        linkedDeque = new SimpleLinkedDeque<>(10);
        linkedDeque.pushRight(7);
        linkedDeque.pushRight(10);

        arrayDeque = new SimpleArrayDeque<>(10);
        arrayDeque.pushLeft(5);
        arrayDeque.pushLeft(4);

        emptyDeque = new SimpleArrayDeque<>(5);
        rand = new Random();
    }

    @Test(expected = NoSuchElementException.class)
    public void peekLeftLinkedDequeEmpty() {
        SimpleLinkedDeque<Integer> deque = new SimpleLinkedDeque<>(emptyDeque);
        deque.peekLeft();
    }

    @Test(expected = NoSuchElementException.class)
    public void popLeftLinkedDequeEmpty() {
        SimpleLinkedDeque<Integer> deque = new SimpleLinkedDeque<>(emptyDeque);
        deque.popLeft();
    }

    @Test
    public void validConstructorLinkedDequeNoCapacity() {
        SimpleLinkedDeque<Integer> other = new SimpleLinkedDeque<>(arrayDeque);
        assertEquals(2, other.size());
    }

    @Test
    public void reverseIteratorLinkedDeque() {
        Integer[] arr = new Integer[15];
        SimpleLinkedDeque<Integer> deque = new SimpleLinkedDeque<>();
        for (int i = 0; i < 15; i++) {
            Integer num = rand.nextInt(20);
            arr[i] = num;
            deque.pushLeft(num);
        }

        Iterator<Integer> iter = deque.reverseIterator();
        Integer[] actual = new Integer[arr.length];
        int i = 0;
        while (iter.hasNext()) {
            actual[i++] = iter.next();
        }

        assertArrayEquals(arr, actual);
    }

    @Test
    public void validEmptyConstructorLinkedDeque() {
        SimpleLinkedDeque<Integer> other = new SimpleLinkedDeque<>();
        assertTrue(other.isEmpty());
    }

    @Test
    public void iteratorLinkedDeque() {
        Integer[] arr = new Integer[15];
        SimpleLinkedDeque<Integer> deque = new SimpleLinkedDeque<>();
        for (int i = 0; i < 15; i++) {
            Integer num = rand.nextInt(20);
            arr[i] = num;
            deque.pushRight(num);
        }

        Iterator<Integer> iter = deque.iterator();
        Integer[] actual = new Integer[arr.length];
        int i = 0;
        while (iter.hasNext()) {
            actual[i++] = iter.next();
        }

        assertArrayEquals(arr, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidConstructorLinkedDequeOther() {
        new SimpleLinkedDeque<>(1, arrayDeque);
    }

    @Test
    public void isFullLinkedDeque() {
        SimpleDeque<String> deque = new SimpleLinkedDeque<>(1);
        deque.pushLeft("hello");
        assertTrue(deque.isFull());
    }

    @Test
    public void validConstructorLinkedDequeSameType() {
        SimpleArrayDeque<Integer> other =
                new SimpleArrayDeque<>(linkedDeque.size(), linkedDeque);

        assertTrue(other.isFull());
    }

    @Test
    public void peekLeftLinkedDequeSingleElement() {
        SimpleDeque<String> deque = new SimpleLinkedDeque<>(1);
        deque.pushRight("hello");
        int priorSize = deque.size();
        assertEquals("hello", deque.peekLeft());
        assertEquals(priorSize, deque.size());
    }

    @Test
    public void sizeElementsLinkedDeque() {
        assertEquals(2, linkedDeque.size());
    }

    @Test(expected = RuntimeException.class)
    public void pushLeftLinkedDequeFull() {
        SimpleDeque<String> deque = new SimpleLinkedDeque<>(1);
        deque.pushLeft("hello");
        deque.pushLeft("world");
    }


}
