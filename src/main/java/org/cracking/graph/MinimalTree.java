package org.cracking.graph;

import static org.cracking.graph.Tree.Node;

/**
 * Given a sorted (increasing order) array with unique integer elements, write an
 * algorithm to create a binary search tree with minimal height.
 */
public class MinimalTree {
    public static void main(String[] args) {
        Tree tree = new MinimalTree().buildTree(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println(tree);
    }

    public <T> Tree buildTree(T[] arr) {
        return new Tree(buildNode(arr, 0, arr.length));
    }

    private <T> Node<T> buildNode(T[] arr, int start, int end) {
        if (start >= end)
            return null;

        int middle = start + (end - start) / 2;

        Node left = buildNode(arr, start, middle);
        Node right = buildNode(arr, middle + 1, end);

        return new Node<>(arr[middle], left, right);
    }
}
