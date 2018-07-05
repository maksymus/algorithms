package org.geeks.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Initial tree:
 *      6
 *    /   \
 *   3     4
 *  / \   / \
 * 7   3 8   1
 *
 *
 * Inverted tree:
 *      6
 *    /   \
 *   4     3
 *  / \   / \
 * 1   8 3   7
 */
public class InvertBinaryTree {
    public void recursive(TreeNode node) {
        if (node == null)
            return;

        TreeNode right = node.right;
        TreeNode left = node.left;

        node.right = left;
        node.left = right;

        recursive(right);
        recursive(left);
    }

    public void stack(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            TreeNode right = node.right;
            TreeNode left = node.left;

            node.right = left;
            node.left = right;

            if (left != null)
                stack.push(left);

            if (right != null)
                stack.push(right);
        }
    }
}
