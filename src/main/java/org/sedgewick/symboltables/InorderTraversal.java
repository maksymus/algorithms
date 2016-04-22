package org.sedgewick.symboltables;

import java.util.function.Consumer;

/**
 * Inorder traversal with constant extra space. Design an algorithm to perform an inorder traversal of a binary
 * search tree using only a constant amount of extra space.
 */
public class InorderTraversal {
    private static class Node {
        public Node(int key) {
            this.key = key;
        }

        public Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        private int key;

        private Node left;
        private Node right;
    }

    // recursion implementation - requires extra space
    public static void inorderr(Node node, Consumer<Node> consumer) {
        if (node == null)
            return;

        inorderr(node.left, consumer);
        consumer.accept(node);
        inorderr(node.right, consumer);
    }


    public static void main(String[] args) {
        Node root = new Node(10, new Node(5, new Node(4), new Node(6)), new Node(15, new Node(13), new Node(16)));

        inorderr(root, (Node n) -> System.out.print(n.key + " "));
        System.out.println();

    }
}
