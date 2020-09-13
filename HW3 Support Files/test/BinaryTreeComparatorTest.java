import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeComparatorTest {

    private BinaryTreeComparator<Integer> c;

    @Before
    public void setup() {
        c = new BinaryTreeComparator<>();
    }

    private <E> BinaryTree<E> tree(BinaryTree<E> left, E value, BinaryTree<E> right) {
        return new BinaryTree<>(value, left, right);
    }

    private <E> BinaryTree<E> left(E value, BinaryTree<E> left) {
        return new BinaryTree<>(value, left, null);
    }

    private <E> BinaryTree<E> right(E value, BinaryTree<E> right) {
        return new BinaryTree<>(value, null, right);
    }

    private <E> BinaryTree<E> leaf(E value) {
        return new BinaryTree<>(value, null, null);
    }


    @Test
    public void testExamples() {
        assertEquals(-1, c.compare(
                tree(leaf(5), 4, leaf(7)),
                tree(leaf(10), 4, leaf(7))
        ));
        assertEquals(1, c.compare(
                tree(leaf(5), 10, leaf(3)),
                tree(leaf(5), 4, leaf(7))
        ));
        assertEquals(-1, c.compare(
                right(10, leaf(4)),
                tree(leaf(2), 1, leaf(3))
        ));
    }

    @Test
    public void testSingleNodes() {
        assertEquals(-1, c.compare(leaf(0), leaf(10)));
        assertEquals(0, c.compare(leaf(10), leaf(10)));
        assertEquals(1, c.compare(leaf(2), leaf(-1)));
    }

    @Test
    public void testSmallTrees() {
        assertEquals(-1, c.compare(
                tree(leaf(0), 10, leaf(100)),
                tree(leaf(0), 20, leaf(0))
        ));
        assertEquals(1, c.compare(
                tree(leaf(10), 10, leaf(10)),
                tree(leaf(0), 10, leaf(10))
        ));
        assertEquals(-1, c.compare(
                tree(leaf(10), 10, leaf(0)),
                tree(leaf(10), 10, leaf(10))
        ));
    }

    @Test
    public void testMissingRight() {
        assertEquals(-1, c.compare(
                tree(leaf(1), 10, null),
                tree(leaf(1), 10, leaf(1))
        ));
    }

}