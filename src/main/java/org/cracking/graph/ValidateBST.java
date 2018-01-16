package org.cracking.graph;

import static org.cracking.graph.Tree.Node;

/**
 * Implement a function to check if a binary tree is a binary search tree.
 */
public class ValidateBST {
    public static void main(String[] args) {
        Node<Integer> root = new Node<>(2, new Node(1), new Node(3));
        System.out.println(new ValidateBST().isValid(root));
    }

    public <T extends Comparable<T>> boolean isValid(Node<T> node) {
        return isValid(node, null, null);
    }

    public <T extends Comparable<T>> boolean isValid(Node<T> node, T min, T max) {
        if (node == null)
            return true;

        T value = node.getValue();

        if (min != null && value.compareTo(min) < 0)
            return false;

        if (max != null && value.compareTo(max) > 0)
            return false;

        return isValid(node.getLeft(), min, value) && isValid(node.getRight(), value, max);
    }
}
