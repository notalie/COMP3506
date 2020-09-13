import java.awt.*;
import java.util.Comparator;

/**
 * A comparator for Binary Trees.
 */
public class BinaryTreeComparator<E extends Comparable<E>> implements Comparator<BinaryTree<E>> {

    public int compareRecurse(BinaryTree<E> tree1, BinaryTree<E> tree2, int counter) {
        if (tree1 == null) { // Error checking if any are null
            return -1;
        } else if (tree2 == null) { // Checks to see if the node is null
            return 1;
        }

        if (tree1.getValue().compareTo(tree2.getValue()) != 0) { // see if the values are the same, if not - return
            return tree1.getValue().compareTo(tree2.getValue());
        }

        if (tree1.getLeft() != null && tree2.getLeft() != null) { // Left exists for both
            counter += compareRecurse(tree1.getLeft(), tree2.getLeft(), counter);
        } else if (tree1.getLeft() == null && tree2.getLeft() != null) { // Check for non existent left
            return -1; //
        } else if (tree1.getLeft() != null && tree2.getLeft() == null) { // Check for non existent left
            return 1;
        }

        if (tree1.getRight() != null && tree2.getRight() != null) { // Right exists for both
            counter += compareRecurse(tree1.getRight(), tree2.getRight(), counter);
        } else if (tree1.getRight() == null && tree2.getRight() != null) { // Check for non existent right
            return -1;
        } else if (tree1.getRight() != null && tree2.getRight() == null) { // Check for non existent right
            return 1;
        }

        if (counter < 0) {
            return -1;
        } else if (counter > 0) {
            return 1;
        } else {
            return 0;
        }
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
        return compareRecurse(tree1, tree2, 0);
    }
}
