package org.geeks.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * For example, given the following binary tree,

 *      1
 *    /   \
 *   2    3
 *   \
 *    5
 *
 * You can see [1, 3, 5].
 */
public class TreeSideView {

    public List<Integer> right(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();

        Deque<TreeNode> queue = new LinkedList<>();
        Deque<Integer> levels = new LinkedList<>();

        queue.offer(root);
        levels.offer(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            Integer level = levels.remove();

            if (result.size() == level) {
                result.add(node.val);
            } else {
                result.set(level, node.val);
            }

            if (node.left != null) {
                queue.add(node.left);
                levels.add(level + 1);
            }

            if (node.right != null) {
                queue.add(node.right);
                levels.add(level + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(1, TreeNode.build(2, TreeNode.build(5), null), TreeNode.build(3));
        List<Integer> rightSideView = new TreeSideView().right(root);

        System.out.println(rightSideView);
    }
}
