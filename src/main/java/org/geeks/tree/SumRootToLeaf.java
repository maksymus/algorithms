package org.geeks.tree;

import static org.geeks.tree.TreeNode.build;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number. Find the total sum of all root-to-leaf numbers.
 *
 * For example,
 *
 *      1
 *     / \
 *    2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 */
public class SumRootToLeaf {
    public int sum(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode node, int num) {
        if (node == null)
            return 0;

        num = num * 10 + node.val;
        if (node.left == null && node.right == null)
            return num;

        return dfs(node.left, num) + dfs(node.right, num);
    }

    public static void main(String[] args) {
//        TreeNode root = build(1, build(2), build(3));
//        TreeNode root = build(1, null, build(3));
        TreeNode root = build(1, build(2, null, build(1)), build(3));
        System.out.println(new SumRootToLeaf().sum(root));
    }
}
