package org.geeks.tree;

public class LowestCommonAncestor {

    /**
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     */
    public TreeNode lcaBst(TreeNode node, TreeNode node1, TreeNode node2) {
        if (!isBst(node))
            throw new IllegalArgumentException("Not a BST tree");

        if (node == null)
            return node;

        if (node1.val < node.val && node.val < node2.val)
            return node;

        if (node1.val < node.val && node2.val < node.val)
            return lcaBst(node.left, node1, node2);

        if (node.val < node1.val && node.val < node2.val)
            return lcaBst(node.right, node1, node2);

        return node;
    }

    /**
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     */
    public TreeNode lca(TreeNode node, TreeNode node1, TreeNode node2) {
        if (node == null)
            return null;

        if (node == node1 || node == node2)
            return node;

        TreeNode left = lca(node.left, node1, node2);
        TreeNode right = lca(node.right, node1, node2);

        if (left != null && right != null)
            return node;

        return left != null ? left : right;
    }

    private boolean isBst(TreeNode root) {
        return isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBst(TreeNode node, int left, int right) {
        if (node == null)
            return true;

        if (node.val < left || node.val > right)
            return false;

        return isBst(node.left, left, node.val) && isBst(node.right, node.val, right);
    }
}
