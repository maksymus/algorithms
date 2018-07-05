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
        return sideView(root, true);
    }

    public List<Integer> left(TreeNode root) {
        return sideView(root, false);
    }

    private List<Integer> sideView(TreeNode root, boolean rightView) {
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

            TreeNode first = rightView ? node.left : node.right;
            TreeNode second = rightView ? node.right : node.left;

            if (first != null) {
                queue.add(first);
                levels.add(level + 1);
            }

            if (second != null) {
                queue.add(second);
                levels.add(level + 1);
            }
        }

        return result;
    }

    public List<Integer> top(TreeNode root) {
        int maxRight = 0;
        int minLeft = 0;

        LinkedList<Integer> result = new LinkedList<>();

        Deque<TreeNode> queue = new LinkedList<>();
        Deque<Integer> levels = new LinkedList<>();

        queue.offer(root);
        levels.offer(0);
        result.add(root.val);

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            Integer level = levels.remove();

            if (level > maxRight) {
                result.addLast(node.val);
                maxRight = level;
            } else if (level < minLeft) {
                result.addFirst(node.val);
                minLeft = level;
            }

            if (node.left != null) {
                queue.add(node.left);
                levels.add(level - 1);
            }

            if (node.right != null) {
                queue.add(node.right);
                levels.add(level + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(1, TreeNode.build(2, null, TreeNode.build(5)), TreeNode.build(3));

        TreeSideView treeSideView = new TreeSideView();
        List<Integer> rightSideView = treeSideView.right(root);
        List<Integer> leftSideView = treeSideView.left(root);
        List<Integer> topView = treeSideView.top(root);

        System.out.println("right:\t" + rightSideView);
        System.out.println("left:\t" + leftSideView);
        System.out.println("top: \t" + topView);
    }
}
