package org.cracking.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.cracking.graph.Tree.Node;

/**
 * A binary search tree was created by traversing through an array from left to right
 * and inserting each element. Given a binary search tree with distinct elements, print all possible
 * arrays that could have led to this tree.
 *
 * Example:
 *        4
 *     /     \
 *    2      6
 *   / \    / \
 *  1  3   5  7
 *
 * Output: {2, 1, 3}, {2, 3, 1}
 */
public class BSTSequences {
    public static void main(String[] args) {
        Node root = new BSTSequences().buildTree(4, 6, 2, 1, 3, 5, 7);
//        Node root = new BSTSequences().buildTree(1, 2, 3);

        List<List<Integer>> sequences = new BSTSequences().sequences(root);
        sequences.forEach(sequence -> System.out.println(Arrays.toString(sequence.toArray())));
    }

    public List<List<Integer>> sequences(Node<Integer> root) {
        List<Integer> prefix = new ArrayList<>();
        prefix.add(root.getValue());

        List<Node<Integer>> front = new LinkedList<>();
        if (root.getLeft() != null)
            front.add(root.getLeft());

        if (root.getRight() != null)
            front.add(root.getRight());

        List<List<Integer>> result = new ArrayList<>();

        sequences(prefix, front, result);

        return result;
    }

    private void sequences(List<Integer> prefix, List<Node<Integer>> frontNodes, List<List<Integer>> result) {

        if (frontNodes.isEmpty())
            result.add(prefix);

        for (Node<Integer> frontNode: frontNodes) {
            LinkedList<Node<Integer>> newFront = new LinkedList<>(frontNodes);
            newFront.remove(frontNode);

            Node<Integer> left = frontNode.getLeft();
            Node<Integer> right = frontNode.getRight();

            if (left != null)
                newFront.add(left);

            if (right != null)
                newFront.add(right);

            List<Integer> newPrefix = new ArrayList<>(prefix);
            newPrefix.add(frontNode.getValue());

            sequences(newPrefix, newFront, result);
        }
    }

    public Node buildTree(int ... sequence) {
        Node root = null;

        for (int num : sequence) {
            if (root == null)
                root = new Node(num);
            else
                insertNode(root, num);
        }

        return root;
    }

    private void insertNode(Node root, int num) {
        Node<Integer> current = root;

        if (current.getValue() > num) {
            if (current.getLeft() != null) {
                insertNode(current.getLeft(), num);
            } else {
                current.setLeft(new Node(num));
            }
        } else {
            if (current.getRight() != null) {
                insertNode(current.getRight(), num);
            } else {
                current.setRight(new Node(num));
            }
        }
    }

}
