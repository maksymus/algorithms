package org.cracking.graph;

import static org.cracking.graph.Tree.Node;

/**
 * Implement a function to check if a binary tree is balanced. For the purposes of
 * this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
 * node never differ by more than one.
 */
public class CheckBalanced {
    public static void main(String[] args) {

        //      1
        //     / \
        //    2   3
        //       /
        //      4
        //     /
        //    5
        Node<Integer> root = new Node<>(1, new Node(2), new Node(3,
                new Node(4, new Node(5), null), null));
        System.out.println(new CheckBalanced().isBalanced(root));
    }

    public boolean isBalanced(Node root) {
        return balancedHeight(root) >= 0;
    }

    private int balancedHeight(Node node) {
        if (node == null)
            return 0;

        Node left = node.getLeft();
        Node right = node.getRight();

        int leftHeight = balancedHeight(left);
        if (leftHeight == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        int rightHeight = balancedHeight(right);
        if (rightHeight == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        if (Math.abs(leftHeight - rightHeight) > 1)
            return Integer.MIN_VALUE;

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
