package org.rust.tree;

import java.util.Deque;
import java.util.LinkedList;

public class LevelOrderTraversal {
    public static void main(String[] args) {
        Node left = new Node(100);
        Node right = new Node(200);

        Node tree = new Node(125, left, right);

        traverse(tree);
    }

    public static void traverse(Node tree) {
        Deque<Node> queue = new LinkedList<>();

        if (tree != null)
            queue.add(tree);

        while (!queue.isEmpty()) {
            Deque<Node> level = new LinkedList<>();

            while (!queue.isEmpty()) {
                Node node = queue.remove();
                Node left = node.left;
                Node right = node.right;

                if (left != null)
                    level.add(left);

                if (right != null)
                    level.add(right);

                System.out.print(node.data + " ");
            }
            System.out.println();
            queue = level;
        }
    }
}
