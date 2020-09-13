import java.util.Comparator;

/**
 * A comparator for Binary Trees.
 */
public class BinaryTreeComparator<E extends Comparable<E>> implements Comparator<BinaryTree<E>> {

    /**
     * Compares two binary trees with the given root nodes.
     *
     * Two nodes are compared by their left childs, their values, then their right childs,
     * in that order. A null is less than a non-null, and equal to another null.
     *
     * @param tree1 root of the first binary tree, may be null.
     * @param tree2 root of the second binary tree, may be null.
     * @return -1, 0, +1 if tree1 is less than, equal to, or greater than tree2, respectively.
     */
    @Override
    public int compare(BinaryTree<E> tree1, BinaryTree<E> tree2) {
        // TODO: implement question 3 here
        return 0;
    }
}
