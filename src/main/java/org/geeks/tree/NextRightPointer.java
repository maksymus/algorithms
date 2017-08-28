package org.geeks.tree;

import java.util.Deque;
import java.util.LinkedList;

import static org.geeks.tree.TreeLinkNode.build;

/**
 * Given the following perfect binary tree,
 *
 *       1
 *     /  \
 *    2    3
 *   / \  / \
 *  4  5  6  7
 * After calling your function, the tree should look like:
 *
 *       1 -> NULL
 *     /  \
 *    2 -> 3 -> NULL
 *   / \  / \
 *  4->5->6->7 -> NULL
 */
public class NextRightPointer {
    public TreeLinkNode transform(TreeLinkNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        Deque<Integer> levels = new LinkedList<>();

        queue.offer(root);
        levels.offer(0);

        TreeLinkNode previous = null;
        Integer previousLevel = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            Integer level = levels.remove();

            if (previous != null && previousLevel == level)
                previous.next = node;

            if (node.left != null) {
                queue.add(node.left);
                levels.add(level + 1);
            }

            if (node.right != null) {
                queue.add(node.right);
                levels.add(level + 1);
            }

            previous = (TreeLinkNode) node;
            previousLevel = level;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeLinkNode root = build(1,
                build(2, build(4), build(5)),
                build(3, build(6), build(7)));

        TreeLinkNode transform = new NextRightPointer().transform(root);
        System.out.println(transform);
    }
}
