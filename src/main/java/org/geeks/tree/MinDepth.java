package org.geeks.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Using BFS
 */
public class MinDepth {

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
}
