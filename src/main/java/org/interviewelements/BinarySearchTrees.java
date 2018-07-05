package org.interviewelements;

import java.util.*;

public class BinarySearchTrees {
    public static class Node<T> {
        public T data;
        public Node parent;
        public Node left;
        public Node right;
    }

    /**
     * Problem 14.1 : Write a function that takes as input the root of a binary tree whose
     * nodes have a key field, and returns true iff the tree satisfies the BST property.
     */
    public static boolean isBST(Node<? extends  Comparable<?>> root) {
        return isBST(root, null, null);
    }

    private static <T extends  Comparable> boolean isBST(Node<T> node, T lowerBound, T upperBound) {
        if (node == null)
            return true;

        T data = node.data;

        if ((lowerBound != null && lowerBound.compareTo(data) > 0)
                || (upperBound != null && upperBound.compareTo(data) < 0))
            return false;

        return isBST(node.left, lowerBound, data) && isBST(node.right, data, upperBound);
    }

    /**
     * Search all elements <code>{ from <= element <= to }</code>
     */
    public static <T extends  Comparable<T>> List<T> rangeSearch(Node<T> bst, T from, T to) {
        if (bst == null)
            return Collections.emptyList();

        ArrayList<T> range = new ArrayList<T>();

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(bst);

        while (!queue.isEmpty()) {
            Node<T> node = queue.remove();

            if (from.compareTo(node.data) <= 0 && to.compareTo(node.data) >= 0) {
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);

                range.add(node.data);
            } else if (from.compareTo(node.data) <= 0) {
                if (node.left != null)
                    queue.add(node.left);
            } else if (to.compareTo(node.data) >= 0) {
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        return range;
    }

}
