package org.geeks.tree;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode left(TreeNode left) {
        this.left = left;
        return this;
    }

    public TreeNode right(TreeNode right) {
        this.right = right;
        return this;
    }

    public static TreeNode build(int val, TreeNode left, TreeNode right) {
        return new TreeNode(val, left, right);
    }

    public static TreeNode build(int val) {
        return new TreeNode(val);
    }

    public static int getMaxHeight(TreeNode node) {
        if (node == null)
            return 0;

        int left = getMaxHeight(node.left);
        int right = getMaxHeight(node.right);

        return 1 + Math.max(left, right);
    }

    public static int minHeight(TreeNode node) {
        if (node == null)
            return 0;

        int leftDepth = minHeight(node.left);
        int rightDepth = minHeight(node.right);

        return Math.min(leftDepth, rightDepth) + 1;
    }
}