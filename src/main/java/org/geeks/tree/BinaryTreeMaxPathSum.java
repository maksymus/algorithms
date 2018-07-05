package org.geeks.tree;

import static org.geeks.tree.TreeNode.build;

/**
 * Given a binary tree, find the maximum path sum. The path may start and end at any node in the tree.
 * For example, given the below binary tree the result is 6:
 *
 *    1
 *   / \
 *  2   3
 */
public class BinaryTreeMaxPathSum {
    private int max = Integer.MIN_VALUE;

    public int maxSum(TreeNode root) {
        calculateMaxSum(root);
        return max;
    }

    private int calculateMaxSum(TreeNode node) {
        if (node == null)
            return 0;

        int leftMaxSum = calculateMaxSum(node.left);
        int rightMaxSum = calculateMaxSum(node.right);

        int currentMax = Math.max(node.val, Math.max(node.val + leftMaxSum, node.val + rightMaxSum));

        max = Math.max(max, Math.max(currentMax, leftMaxSum + node.val + rightMaxSum));

        return currentMax;
    }

    public static void main(String[] args) {
        System.out.println(new BinaryTreeMaxPathSum().maxSum(build(1, build(2), build(3))));
    }
}
