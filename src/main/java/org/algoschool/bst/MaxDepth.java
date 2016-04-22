package org.algoschool.bst;

public class MaxDepth {

    private static class Node {
        private int key;           // sorted by key
        private Node left, right;  // left and right subtrees

        public Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public Node(Integer key) {
            this.key = key;
        }
    }

    private static int maxDepth(Node bst) {
        if (bst == null)
            return 0;

        int leftDepth = maxDepth(bst.left);
        int rightDepth = maxDepth(bst.right);

        if (leftDepth > rightDepth)
            return leftDepth + 1;
        else
            return rightDepth + 1;
    }

    public static void main(String[] args) {
        Node root = new Node(30, new Node(20), new Node(40, new Node(35), null));
        System.out.println(maxDepth(root));
    }
}
