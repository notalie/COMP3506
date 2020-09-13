import java.awt.*;
import java.util.Comparator;

/**
 * A comparator for Binary Trees.
 */
public class BinaryTreeComparator<E extends Comparable<E>> implements Comparator<BinaryTree<E>> {

    public int compareRecurse(BinaryTree<E> tree1, BinaryTree<E> tree2) {
        if (tree1 == null) { // Error checking if any are null
            return -1;
        } else if (tree2 == null) {
            return 1;
        }

        if (tree1.getValue().compareTo(tree2.getValue()) != 0) { // tree1 < tree2 or tree1 < tree2
            return tree1.getValue().compareTo(tree2.getValue());
        }

        // We know that the values are equal. Now recuse I think?
        if (compareRecurse(tree1.getLeft(), tree2.getLeft()) != 0 &&
                compareRecurse(tree1.getRight(), tree2.getRight()) != 0) {
            return 0;
        }
        return -1;
    }

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
        int leftSide = 0;
        int rightSide = 0;

        return compareRecurse(tree1, tree2);
    }
}
