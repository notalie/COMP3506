public class StrongHeap {
    /*
    * 1. Find depth of the tree (simple algorithm).
    * 2. Count the number of nodes in a tree (by traversing and increasing the counter by one on visiting any node).
    * 3. For a complete binary tree of level d number of nodes equals to pow(2,d+1)-1.
    */

    /* Global Counter for counting the amount of total nodes visited, acts as a global variable within the program */
    private static int visitedNodes = 0;

    /**
     * Recursive Method to be called to check if a the binary tree is a strong heap
     * @param node - the current node that the program is checking
     * @param currentSum - the current sum of the binary tree
     * @param rootValue - the value of the root/top element
     * @param nodeCounter - counter that tracks the amount of nodes visited
     * @return - boolean corresponding to if the heap is a strong one of not
     * Time Complexity: O(h) - where h is the tree
     * Space Complexity: O(n) - where n is the amount of nodes in the tree
     */
    private static boolean strongHeapRecurse(BinaryTree<Integer> node, int currentSum, int rootValue, int nodeCounter) {
        if (++nodeCounter > visitedNodes) { // Adds to the visited nodes counter and updates the visited nodes variable
            visitedNodes = nodeCounter;
        }

        // Not comparing values with the root value
        if (node.getValue() >= rootValue && nodeCounter > 1) {
            return false;
        }

        // Checking for completeness
        if (node.getRight() != null && node.getLeft() == null) {
            return false;
        }

        // Recurse within the left node
        if (node.getLeft() != null) {
            return strongHeapRecurse(node.getLeft(), node.getValue() + currentSum, rootValue, nodeCounter);
        }

        // Recurse within the right node
        if (node.getRight() != null) {
            return strongHeapRecurse(node.getRight(), node.getValue() + currentSum, rootValue, nodeCounter);
        }

        // First check if the value is not 0, check if the total visited nodes is over or equal to 3.
        // Minus the root value to avoid the double up initial add to the sum
        if (node.getValue() != 0 && visitedNodes >= 3 && (node.getValue() + currentSum - rootValue >= rootValue)) {
            return false;
        }
        return true;
    }


    /**
     * Determines whether the binary tree with the given root node is
     * a "strong binary heap", as described in the assignment task sheet.
     *
     * A strong binary heap is a binary tree which is:
     *  - a complete binary tree, AND
     *  - its values satisfy the strong heap property.
     *
     * - Also calls the recursive method
     * @param root root of a binary tree, cannot be null.
     * @return true if the tree is a strong heap, otherwise false.
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static boolean isStrongHeap(BinaryTree<Integer> root) {
        if (root.getLeft() == null && root.getRight() == null) {
            return true;
        } else {
            return strongHeapRecurse(root, 0, root.getValue(), 0);
        }
    }
}
