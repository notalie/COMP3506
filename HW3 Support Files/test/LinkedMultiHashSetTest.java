import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class LinkedMultiHashSetTest {

    @Test
    public void testRearrangeArray() {
        LinkedMultiHashSet<Integer> set = new LinkedMultiHashSet<>(5);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);

        set.remove(2);

        LinkedMultiHashSet.Node test2 = set.head;
        while(test2 != null) {
            System.out.println(test2.value);
            test2 = test2.next;
        }
    }

    @Test
    public void testBasicUsage() {
        LinkedMultiHashSet<Integer> set = new LinkedMultiHashSet<>(5);

        assertEquals(5, set.internalCapacity());

        set.add(5);
        assertEquals(1, set.count(5));
        assertEquals(1, set.size());
        assertTrue(set.contains(5));
        assertEquals(5, set.internalCapacity());

        set.add(5);
        assertEquals(2, set.count(5));
        assertEquals(2, set.size());
        assertTrue(set.contains(5));
        assertEquals(5, set.internalCapacity());

        set.add(3);
        assertEquals(2, set.count(5));
        assertEquals(1, set.count(3));
        assertEquals(3, set.size());
        assertTrue(set.contains(5));
        assertTrue(set.contains(3));
        assertEquals(5, set.internalCapacity());
    }

    @Test
    public void testRemovals() {
        LinkedMultiHashSet<Integer> set = new LinkedMultiHashSet<>(5);

        assertEquals(5, set.internalCapacity());

        set.add(5);
        assertEquals(1, set.count(5));
        assertEquals(1, set.size());
        assertTrue(set.contains(5));

        set.remove(5);
        assertEquals(0, set.count(5));
        assertEquals(0, set.size());
        assertFalse(set.contains(5));

        try {
            set.remove(5);
            fail();
        } catch (NoSuchElementException e) {
            // all good :)
        }

        set.add(5, 2);
        assertEquals(2, set.count(5));
        assertEquals(2, set.size());

        try {
            set.remove(5, 3);
            fail();
        } catch (NoSuchElementException e) {
            // all good :)
        }

        assertEquals(2, set.count(5));
        assertEquals(2, set.size());
    }

    @Test
    public void testIteratorSimple() {
        LinkedMultiHashSet<Integer> set = new LinkedMultiHashSet<>(5);

        set.add(1);
        set.add(2);
        set.add(3);

        Iterator<Integer> it = set.iterator();

        assertTrue(it.hasNext());
        assertEquals(1, (int) it.next());

        assertTrue(it.hasNext());
        assertEquals(2, (int) it.next());

        assertTrue(it.hasNext());
        assertEquals(3, (int) it.next());

        assertFalse(it.hasNext());
    }

    @Test
    public void testResize() {
        LinkedMultiHashSet<Integer> set = new LinkedMultiHashSet<>(3);

        set.add(1);
        set.add(2);
        Assert.assertEquals(set.internalCapacity(), 3);
        set.add(3);
        set.add(4);
        Assert.assertEquals(set.internalCapacity(), 6);
    }

    @Test
    public void testIteratorDuplicates() {
        LinkedMultiHashSet<Integer> set = new LinkedMultiHashSet<>(5);

        set.add(4);
        set.add(1);
        set.add(2);
        set.remove(4);
        set.add(3);
        set.add(4);
        set.add(1);
        set.add(3);

        Iterator<Integer> it = set.iterator();

        // duplicates
        assertTrue(it.hasNext());
        assertEquals(1, (int) it.next());
        assertTrue(it.hasNext());
        assertEquals(1, (int) it.next());

        assertTrue(it.hasNext());
        assertEquals(2, (int) it.next());

        // duplicates
        assertTrue(it.hasNext());
        assertEquals(3, (int) it.next());
        assertTrue(it.hasNext());
        assertEquals(3, (int) it.next());

        assertTrue(it.hasNext());
        assertEquals(4, (int) it.next());

        assertFalse(it.hasNext());
    }

    @Test
    public void testBasicUsageStrings() {
        LinkedMultiHashSet<String> set = new LinkedMultiHashSet<>(5);

        assertEquals(5, set.internalCapacity());

        set.add("hello");
        assertEquals(1, set.count("hello"));
        assertEquals(1, set.size());
        assertTrue(set.contains("hello"));
        assertEquals(5, set.internalCapacity());

        set.add("hello");
        assertEquals(2, set.count("hello"));
        assertEquals(2, set.size());
        assertTrue(set.contains("hello"));
        assertEquals(5, set.internalCapacity());

        set.add("goodbye");
        assertEquals(2, set.count("hello"));
        assertEquals(1, set.count("goodbye"));
        assertEquals(3, set.size());
        assertTrue(set.contains("hello"));
        assertTrue(set.contains("goodbye"));
        assertEquals(5, set.internalCapacity());
    }

    @Test
    public void testCollision() {
        LinkedMultiHashSet<Integer> set = new LinkedMultiHashSet<>(5);

        set.add(1);
        set.add(6);
        set.add(11);

        assertTrue(set.contains(1));
        assertTrue(set.contains(6));
        assertTrue(set.contains(11));


    }

    @Test
    public void testSimpleResize() {
        LinkedMultiHashSet<String> set = new LinkedMultiHashSet<>(5);

        set.add("a");
        set.add("b");
        set.add("e");
        assertEquals(3, set.distinctCount());
        assertEquals(5, set.internalCapacity());
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("e"));

        set.add("c");
        assertEquals(4, set.distinctCount());
        assertEquals(5, set.internalCapacity());
        assertEquals(4, set.size());

        set.add("d");
        assertEquals(5, set.distinctCount());
        assertEquals(10, set.internalCapacity());
        assertEquals(5, set.size());

        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
        assertTrue(set.contains("d"));
        assertTrue(set.contains("e"));

        set.add("hello");
        assertEquals(6, set.distinctCount());
        assertEquals(10, set.internalCapacity());
        assertEquals(6, set.size());
    }

    @Test
    public void testResizeIterator() {
        LinkedMultiHashSet<String> set = new LinkedMultiHashSet<>(5);

        set.add("a");
        set.add("b");
        set.add("e");
        assertEquals(3, set.distinctCount());
        assertEquals(5, set.internalCapacity());
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("e"));

        set.add("c");
        assertEquals(4, set.distinctCount());
        assertEquals(5, set.internalCapacity());
        assertEquals(4, set.size());

        set.add("d");
        assertEquals(5, set.distinctCount());
        assertEquals(10, set.internalCapacity());
        assertEquals(5, set.size());

        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
        assertTrue(set.contains("d"));
        assertTrue(set.contains("e"));

        set.add("hello");
        assertEquals(6, set.distinctCount());
        assertEquals(10, set.internalCapacity());
        assertEquals(6, set.size());


        Iterator<String> it = set.iterator();

        assertTrue(it.hasNext());
        assertEquals("a", it.next());
        assertEquals("b", it.next());
        assertEquals("e", it.next());
        assertEquals("c", it.next());
        assertEquals("d", it.next());
    }
}