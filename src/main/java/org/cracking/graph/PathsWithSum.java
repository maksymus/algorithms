package org.cracking.graph;

import static org.cracking.graph.Tree.Node;

/**
 * You are given a binary tree in which each node contains an integer value (which
 * might be positive or negative). Design an algorithm to count the number of paths that sum to a
 * given value. The path does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to child nodes).
 */
public class PathsWithSum {

    public static void main(String[] args) {
        //       10
        //      /  \
        //     5   -3
        //    / \    \
        //   3   2   11
        //  / \   \
        // 3  -2   1
        //
        Node<Integer> root = new Node(10,
                new Node(5,
                        new Node(3, new Node(3), new Node(-2)),
                        new Node(2, null, new Node(1))),
                new Node(-3,
                        null, new Node(11)));

        System.out.println(new PathsWithSum().count(root, 8));

    }

    // TODO use hash map to hash sum values at each node
    public int count(Node<Integer> root, int sum) {
        if (root == null)
            return 0;

        int rootCount = count(root, sum, 0);

        int leftCount = count(root.getLeft(), sum);
        int rightCount = count(root.getRight(), sum);

        return rootCount + leftCount + rightCount;
    }

    private int count(Node<Integer> node, int sum, int currentSum) {
        if (node == null)
            return 0;

        int nodeSum = currentSum + node.getValue();

        int leftCount = count(node.getLeft(), sum, nodeSum);
        int rightCount = count(node.getRight(), sum, nodeSum);

        return leftCount + rightCount + (nodeSum == sum ? 1 : 0);
    }
}
