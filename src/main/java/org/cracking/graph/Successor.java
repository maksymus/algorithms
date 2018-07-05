package org.cracking.graph;

import java.util.Deque;
import java.util.LinkedList;

import static org.cracking.graph.Tree.Node;

/**
 * Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a
 * binary search tree. You may assume that each node has a link to its parent.
 */
public class Successor {
    public static void main(String[] args) {
        Node<Integer> root = new Node<>(2, new Node(1), new Node(3));
        System.out.println(new Successor().find(root, root));
    }

    public Node find(Node root, Node given) {
        Deque<Node> stack = new LinkedList<>();

        for (Node tmp = root; tmp != null; tmp = tmp.getLeft()) {
            stack.push(tmp);
        }

        boolean found = false;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (found)
                return node;

            found = node == given;

            Node right = node.getRight();
            if (right != null) {
                for (Node tmp = right; tmp != null; tmp = tmp.getLeft()) {
                    stack.push(tmp);
                }
            }
        }

        return null;
    }
}
