package org.sedgewick.symboltables;

/**
 * Check if a binary tree is a BST.
 * Given a binary tree where each Node contains a key, determine whether it is a binary search tree. Use extra space
 * proportional to the height of the tree.
 */
public class BSTChecker {
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

    private Node root;

    public BSTChecker(Node root) {
        this.root = root;
    }

    private boolean isBST() {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(Node node, int min, int max) {
        if (node == null)
            return true;

        if (!isKeyInRange(node.left, min, node.key))
            return false;

        if (!isKeyInRange(node.right, node.key, max))
            return false;

        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }

    private boolean isKeyInRange(Node node, int min, int max) {
        if (node == null)
            return true;

        return node.key >= min && node.key <= max;
    }

    public static void main(String[] args) {
        Node root1 = new Node(5, new Node(3, new Node(1), null), new Node(7));
        System.out.println(new BSTChecker(root1).isBST());

        Node root2 = new Node(5, new Node(3, new Node(1), new Node(2)), new Node(7));
        System.out.println(new BSTChecker(root2).isBST());

        Node root3 = new Node(3, new Node(2, new Node(1), new Node(4)), new Node(7));
        System.out.println(new BSTChecker(root3).isBST());
    }
}
