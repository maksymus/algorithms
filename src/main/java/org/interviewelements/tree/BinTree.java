package org.interviewelements.tree;

import org.util.BinTreeNode;

public class BinTree {

    public static int maxHeight(BinTreeNode<?> node) {
        if (node == null)
            return 0;

        int leftDepth = maxHeight(node.getLeft());
        int rightDepth = maxHeight(node.getRight());

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * A binary tree is said to be balanced if for each node in the tree, the difference in the height of its left
     * and right subtrees is at most one.
     */
    public static boolean isBalaneced(BinTreeNode<?> node) {
        return maxBalancedHeight(node) != -1;
    }

    private static int maxBalancedHeight(BinTreeNode<?> node) {
        if (node == null)
            return 0;

        int leftDepth = maxBalancedHeight(node.getLeft());
        if (leftDepth == -1)
            return -1;

        int rightDepth = maxBalancedHeight(node.getRight());
        if (rightDepth == -1)
            return -1;

        if (Math.abs(leftDepth - rightDepth) > 1)
            return -1; // unbalanced

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        BinTreeNode root = new BinTreeNode(30, new BinTreeNode(20), new BinTreeNode(40, new BinTreeNode(35), null));
        System.out.println(maxHeight(root));
    }
}
