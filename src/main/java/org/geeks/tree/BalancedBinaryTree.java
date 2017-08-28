package org.geeks.tree;

public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode node) {
        int height = getHeight(node);
        return height != -1;
    }

    public int getHeight(TreeNode node) {
        if (node == null)
            return 0;

        int left = getHeight(node.left);
        int right = getHeight(node.right);

        if (left == -1 || right == -1)
            return -1;

        if (Math.abs(left - right) > 1)
            return -1;

        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {

    }
}
