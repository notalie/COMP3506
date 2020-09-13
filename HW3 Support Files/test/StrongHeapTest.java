import org.junit.Test;

import static org.junit.Assert.*;

public class StrongHeapTest {

    private <E> BinaryTree<E> tree(BinaryTree<E> left, E value, BinaryTree<E> right) {
        return new BinaryTree<>(value, left, right);
    }

    private <E> BinaryTree<E> left(BinaryTree<E> left, E value) {
        return new BinaryTree<>(value, left, null);
    }

    private <E> BinaryTree<E> right(E value, BinaryTree<E> right) {
        return new BinaryTree<>(value, null, right);
    }

    private <E> BinaryTree<E> leaf(E value) {
        return new BinaryTree<>(value, null, null);
    }

    @Test
    public void testSingleNode() {
        assertTrue(StrongHeap.isStrongHeap(leaf(1)));
    }

    @Test
    public void testExamples() {
        assertTrue(StrongHeap.isStrongHeap(
                tree(tree(leaf(4), 5, leaf(3)), 10, leaf(6))
        ));
        assertFalse(StrongHeap.isStrongHeap(
                tree(tree(leaf(5), 5, leaf(3)), 10, leaf(6))
        ));
        assertFalse(StrongHeap.isStrongHeap(
                tree(right(5, leaf(3)), 10, leaf(6))
        ));
    }

    @Test
    public void testTwoNodesTrue() {
        assertTrue(StrongHeap.isStrongHeap(
                left(leaf(0), 10)));
    }

    @Test
    public void testTwoNodesFalse() {
        assertFalse(StrongHeap.isStrongHeap(
                left(leaf(100), 10)
        ));
    }

    @Test
    public void testNegatives() {
        assertTrue(StrongHeap.isStrongHeap(
                left(leaf(-100), 0)
        ));
    }

    @Test
    public void testThreeNodesTrue() {
        assertTrue(StrongHeap.isStrongHeap(
                tree(leaf(10), 100, leaf(99))
        ));
    }

    @Test
    public void testThreeNodesFalse() {
        // 100 not < 100
        assertFalse(StrongHeap.isStrongHeap(
                tree(leaf(100), 100, leaf(99))
        ));
    }

    @Test
    public void testFourNodesTrue() {
        // 0 + 99 < 100
        assertTrue(StrongHeap.isStrongHeap(
                tree(left(leaf(0), 99), 100, leaf(99))
        ));
    }

    @Test
    public void testFourNodesFalse() {
        // 98 + 99 not < 100
        assertFalse(StrongHeap.isStrongHeap(
                tree(left(leaf(98), 99), 100, leaf(99))
        ));
    }

    @Test
    public void testNonCompleteFalse() {
        // not a complete tree
        assertFalse(StrongHeap.isStrongHeap(
                right(100, leaf(0))
        ));
    }

}