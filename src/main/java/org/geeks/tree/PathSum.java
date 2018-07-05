package org.geeks.tree;

import java.util.*;

import static org.geeks.tree.TreeNode.build;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values
 * along the path equals the given sum.

 * For example:
 * Given the below binary tree and sum = 22,
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7   2   5   1
 * the method returns the following:

 * [
 *  [5,4,11,2],
 *  [5,8,4,5]
 * ]
 */
public class PathSum {
    class SumTreeNode extends TreeNode {
        private SumTreeNode parent;
        private int sum;

        public SumTreeNode(TreeNode node, SumTreeNode parent, int sum) {
            super(node.val, node.left, node.right);
            this.parent = parent;
            this.sum = sum;
        }
    }

    public boolean recursive(TreeNode node, int sum) {
        if (node == null)
            return false;

        if (sum == 0 && node.left == null && node.right == null)
            return true;

        return recursive(node.left, sum - node.val)
                || recursive(node.right, sum - node.val);
    }

    public List<List<Integer>> paths(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();

        Deque<SumTreeNode> stack = new LinkedList<>();
        stack.push(new SumTreeNode(root, null, sum));

        while (!stack.isEmpty()) {
            SumTreeNode node = stack.pop();

            if (node.right != null)
                stack.push(new SumTreeNode(node.right, node, node.sum - node.val));

            if (node.left != null)
                stack.push(new SumTreeNode(node.left, node, node.sum - node.val));

            if (node.right == null && node.left == null && node.val == node.sum) {
                LinkedList<Integer> path = new LinkedList<>();
                for (SumTreeNode n = node; n != null; n = n.parent) {
                    path.push(n.val);
                }
                result.add(path);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = build(5,
                build(4, build(11, build(7), build(2)), null),
                build(8, build(13), build(4, build(5), build(1))));

        List<List<Integer>> paths = new PathSum().paths(root, 22);
        for (List<Integer> path: paths) {
            System.out.println(Arrays.toString(path.toArray()));
        }
    }
}
