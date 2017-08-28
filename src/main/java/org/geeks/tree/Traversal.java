package org.geeks.tree;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.geeks.tree.TreeNode.build;

/**
 *     1
 *    / \
 *   2  3
 *  / \
 * 4  5
 *   /
 *   6
 */
public class Traversal {

    public List<TreeNode> preorder(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            result.add(node);

            if (node.right != null)
                stack.push(node.right);

            if (node.left != null)
                stack.push(node.left);
        }

        return result;
    }

    public List<TreeNode> inorder(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();

        for (TreeNode l = root; l != null; l = l.left)
            stack.push(l);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node);

            TreeNode right = node.right;
            for (TreeNode l = right; l != null; l = l.left)
                stack.push(l);
        }

        return result;
    }

    public List<TreeNode> postorder(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        Deque<TreeNode> reverse = new LinkedList<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.left != null)
                stack.push(node.left);

            if (node.right != null)
                stack.push(node.right);

            reverse.push(node);
        }

        while (!reverse.isEmpty())
            result.add(reverse.pop());

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = build(1, build(2, build(4), build(5, build(6), null)), build(3));

        Traversal traversal = new Traversal();

        print(traversal.preorder(root));
        print(traversal.inorder(root));
        print(traversal.postorder(root));
    }

    public static void print(List<TreeNode> nodes) {
        String collect = nodes.stream().map(node -> String.valueOf(node.val)).collect(Collectors.joining(" "));
        System.out.println(collect);
    }
}
