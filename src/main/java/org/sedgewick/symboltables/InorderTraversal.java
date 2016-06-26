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
        
        @Override
        public String toString() {
            return String.valueOf(key);
        }
    }

    // recursion implementation - requires extra space
    public static void inorderStack(Node node, Consumer<Node> consumer) {
        if (node == null)
            return;

        inorderStack(node.left, consumer);
        consumer.accept(node);
        inorderStack(node.right, consumer);
    }

    /**
     * Inorder Tree Traversal without recursion and without stack
     * (<a href="http://stackoverflow.com/questions/5502916/explain-morris-inorder-tree-traversal-without-using-stacks-or-recursion">
     * explain-morris-inorder-tree-traversal-without-using-stacks-or-recursion</a>)
     *
     * </br>
     * 1. Initialize current as root 
     * 2. While current is not <code>null</code>
     * If current does not have left child
     *      a) Print current’s data
     *      b) Go to the right, i.e., current = current->right
     * Else
     *      a) Make current as right child of the rightmost node in current's left subtree
     *      b) Go to this left child, i.e., current = current->left
     */
    public static void inorder(Node node, Consumer<Node> consumer) {
        Node current, pre;

        if (node == null)
            return;

        current = node;
        while (current != null) {
            if (current.left == null) {
                consumer.accept(current);
                current = current.right;
            } else {

                /* Find the inorder predecessor of current */
                pre = current.left;
                while (pre.right != null && pre.right != current) {
                    pre = pre.right;
                }

                /* Make current as right child of its inorder predecessor */
                if (pre.right == null) {
                    pre.right = current;
                    current = current.left;
                }
                /*
                 * Revert the changes made in if part to restore the original
                 * tree i.e., fix the right child of predecssor
                 */
                else {
                    pre.right = null;
                    consumer.accept(current);
                    current = current.right;
                }
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10, new Node(5, new Node(4), new Node(6)), new Node(15, new Node(13), new Node(16)));

        inorderStack(root, (Node n) -> System.out.print(n.key + " "));
        System.out.println();
        
        inorder(root, (Node n) -> System.out.print(n.key + " "));
        System.out.println();
    }
}
