package org.rust.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root.
 * For example, boundary traversal of the following tree is “20 8 4 10 14 25 22”
 *
 * https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
 */
public class BoundaryTraversal {
    public static void main(String[] args) {
        Node tree = new Node(20,
                new Node(8,
                        new Node(4),
                        new Node(12,
                                new Node(10), new Node(14))),
                new Node(22, null, new Node(25)));

        traverse(tree);
    }

    public static void traverse(Node tree) {
        // print root
        System.out.println(tree.data);

        // print left side
        for (Node tmp = tree.left; tmp != null && !isLeaf(tmp); tmp = tmp.left)
            System.out.println(tmp.data);

        // print leaves
        Deque<Node> stack = new LinkedList<>();
        stack.push(tree);
        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (isLeaf(node))
                System.out.println(node.data);

            if (node.right != null)
                stack.push(node.right);

            if (node.left != null)
                stack.push(node.left);
        }

        // print right side backwards
        for (Node tmp = tree.right; tmp != null && !isLeaf(tmp); tmp = tmp.right)
            stack.push(tmp);

        while (!stack.isEmpty())
            System.out.println(stack.pop().data);
    }

    public static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }
}
