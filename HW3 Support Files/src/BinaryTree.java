/**
 * A binary tree, where each node contains at most two children.
 * Each node contains a value and references to its left and right children (if they exist).
 *
 * @param <E> the type of the tree's elements
 */
public class BinaryTree<E> {
    private E value; // the element at this node
    private BinaryTree<E> left; // the left child (subtree)
    private BinaryTree<E> right; // the right child (subtree)

    /**
     * Constructs a new binary tree node with the given value and no children.
     *
     * @param value element to store at this node, cannot be null.
     */
    public BinaryTree(E value) {
        this(value, null, null);
    }

    /**
     * Constructs a new binary tree node with the given value and children.
     *
     * @param value element to store at this node, cannot be null.
     * @param left left subtree, can be null.
     * @param right right subtree, can be null.
     */
    public BinaryTree(E value, BinaryTree<E> left, BinaryTree<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * Gets the value stored at this node.
     * @return value stored at this node.
     */
    public E getValue() {
        return value;
    }

    /**
     * Returns whether this is a leaf node, i.e. whether it has no children.
     * @return whether this is a leaf node.
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Sets the left child of this node to the given subtree.
     * Any existing left child will be overridden.
     *
     * @param left the new left child or null
     */
    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    /**
     * Sets the right child of this node to the given subtree.
     * Any existing right child will be overridden.
     *
     * @param right the new right child or null
     */
    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }

    /**
     * @return the left child subtree of this tree node
     */
    public BinaryTree<E> getLeft() {
        return left;
    }

    /**
     * @return the right child subtree of this tree node
     */
    public BinaryTree<E> getRight() {
        return right;
    }

}
