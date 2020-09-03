import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

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
public class DequeSampleTest {
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

    @Test(expected = IllegalArgumentException.class)
    public void invalidConstructorNegArrayDeque() {
        new SimpleArrayDeque<Integer>(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidConstructorNegLinkedDeque() {
        new SimpleLinkedDeque<Integer>(-2);
    }

    @Test
    public void validConstructorArrayDequeSameType() {
        System.out.println(arrayDeque.size());
        SimpleArrayDeque<Integer> other = new SimpleArrayDeque<>(arrayDeque.size(), arrayDeque);

        assertTrue(other.isFull());
    }

    @Test
    public void validConstructorLinkedDequeSameType() {
        SimpleLinkedDeque<Integer> other =
                new SimpleLinkedDeque<>(linkedDeque.size(), linkedDeque);

        assertTrue(other.isFull());
    }

    @Test
    public void validConstructorLinkedDequeNoCapacity() {
        SimpleLinkedDeque<Integer> other = new SimpleLinkedDeque<>(arrayDeque);
        assertEquals(2, other.size());
    }

    @Test
    public void validEmptyConstructorLinkedDeque() {
        SimpleLinkedDeque<Integer> other = new SimpleLinkedDeque<>();
        assertTrue(other.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidConstructorArrayDequeOther() {
        new SimpleArrayDeque<>(1, arrayDeque);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidConstructorLinkedDequeOther() {
        new SimpleLinkedDeque<>(1, arrayDeque);
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvalidPeekLeftReversibleDeque() {
        ReversibleDeque<Integer> deque = new ReversibleDeque<>(emptyDeque);
        deque.peekLeft();
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvalidPopLeftReversibleDeque() {
        ReversibleDeque<Integer> deque = new ReversibleDeque<>(emptyDeque);
        deque.popLeft();
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

    @Test
    public void sizeElementsArrayDeque() {
        assertEquals(2, arrayDeque.size());
    }

    @Test
    public void sizeElementsLinkedDeque() {
        assertEquals(2, linkedDeque.size());
    }

    @Test
    public void sizeReversibleDeque() {
        ReversibleDeque<Integer> deque = new ReversibleDeque<>(arrayDeque);
        assertEquals(2, deque.size());
    }

    @Test
    public void isEmptyArrayDeque() {
        SimpleDeque<Integer> deque = new SimpleArrayDeque<>(20);
        assertTrue(deque.isEmpty());

        deque.pushRight(1);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void isEmptyLinkedDeque() {
        SimpleDeque<Integer> deque = new SimpleLinkedDeque<>(20);
        assertTrue(deque.isEmpty());

        deque.pushRight(1);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void isFullArrayDeque() {
        SimpleDeque<String> deque = new SimpleArrayDeque<>(1);
        deque.pushLeft("hello");
        assertTrue(deque.isFull());
    }

    @Test
    public void isFullLinkedDeque() {
        SimpleDeque<String> deque = new SimpleLinkedDeque<>(1);
        deque.pushLeft("hello");
        assertTrue(deque.isFull());
    }

    @Test
    public void isFullReversibleDequeDataUnchanged() {
        SimpleDeque<String> data = new SimpleLinkedDeque<>(1);
        data.pushLeft("hello");
        ReversibleDeque<String> deque = new ReversibleDeque<>(data);
        assertTrue(deque.isFull());
    }

    @Test(expected = RuntimeException.class)
    public void pushLeftArrayDequeFull() {
        SimpleDeque<String> deque = new SimpleArrayDeque<>(1);
        deque.pushLeft("hello");
        deque.pushLeft("world");
    }

    @Test(expected = RuntimeException.class)
    public void pushLeftLinkedDequeFull() {
        SimpleDeque<String> deque = new SimpleLinkedDeque<>(1);
        deque.pushLeft("hello");
        deque.pushLeft("world");
    }

    @Test(expected = RuntimeException.class)
    public void pushLeftReversibleDequeFull() {
        SimpleDeque<String> data = new SimpleLinkedDeque<>(1);
        ReversibleDeque<String> deque = new ReversibleDeque<>(data);
        deque.pushLeft("hello");
        deque.pushLeft("world");
    }

    @Test
    public void peekLeftArrayDequeSingleElement() {
        SimpleDeque<String> deque = new SimpleArrayDeque<>(1);
        deque.pushRight("hello");
        int priorSize = deque.size();
        assertEquals("hello", deque.peekLeft());
        assertEquals(priorSize, deque.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void peekLeftArrayDequeEmpty() {
        emptyDeque.peekLeft();
    }

    @Test(expected = NoSuchElementException.class)
    public void popLeftArrayDequeEmpty() {
        emptyDeque.popLeft();
    }

    @Test
    public void peekLeftLinkedDequeSingleElement() {
        SimpleDeque<String> deque = new SimpleLinkedDeque<>(1);
        deque.pushRight("hello");
        int priorSize = deque.size();
        assertEquals("hello", deque.peekLeft());
        assertEquals(priorSize, deque.size());
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
}