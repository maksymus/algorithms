package org.cracking.graph;

import static org.cracking.graph.Tree.Node;

/**
 * Design an algorithm and write code to find the first common ancestor
 * of two nodes in a binary tree. Avoid storing additional nodes in a data structure.
 */
public class FirstCommonAncestor {

    public static void main(String[] args) {

        //      2
        //     / \
        //    1   5
        //       / \
        //      4   6

        Node node4 = new Node(4);
        Node node6 = new Node(6);
        Node node5 = new Node(5, node4, node6);

        Node node1 = new Node(1);
        Node node2 = new Node(2, node1, node5);

        Node root = node2;

        Node ancestor = new FirstCommonAncestor().find(root, node4, node6);
        System.out.println(ancestor);
    }

    public Node find(Node node, Node node1, Node node2) {
        if (node == null)
            return null;

        if (node == node1 || node == node2)
            return node;

        Node left = find(node.getLeft(), node1, node2);
        Node right = find(node.getRight(), node1, node2);

        if (left != null && right != null)
            return node;

        return left != null ? left : right;
    }
}
