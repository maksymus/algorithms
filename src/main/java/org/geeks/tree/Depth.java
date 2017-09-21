package org.geeks.tree;

import java.util.Deque;
import java.util.LinkedList;

import static org.geeks.tree.TreeNode.build;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Using BFS
 */
public class Depth {

    public int min(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        Deque<Integer> counts = new LinkedList<>();

        queue.add(root);
        counts.add(1);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            Integer count = counts.remove();

            if (node.right == null && node.left == null)
                return count;

            if (node.right != null) {
                queue.push(node.right);
                counts.add(count + 1);
            }

            if (node.left != null) {
                queue.push(node.left);
                counts.add(count + 1);
            }
        }

        return 0;
    }

    public int max(TreeNode root) {
        if (root == null)
            return 0;

        Deque<TreeNode> stack = new LinkedList<>();
        Deque<Integer> counts = new LinkedList<>();

        stack.push(root);
        counts.add(1);

        int max = 0;

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            Integer count = counts.pop();

            if (count > max)
                max = count;

            if (node.right != null) {
                stack.push(node.right);
                counts.push(count + 1);
            }

            if (node.left != null) {
                stack.push(node.left);
                counts.push(count + 1);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        TreeNode root = build(1, build(2), build(3, build(4), build(5)));
        Depth depth = new Depth();

        System.out.println(depth.min(root));
        System.out.println(depth.max(root));
    }
}
