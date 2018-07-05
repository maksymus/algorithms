package org.cracking.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You are implementing a binary search tree class from scratch, which, in addition
 * to insert, find, and delete, has a method getRandomNode() which returns a random node
 * from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
 * for getRandomNode, and explain how you would implement the rest of the methods.
 */
public class RandomNode {
    public static void main(String[] args) {
        Tree<Integer> tree = generateTree();

        Map<Integer, Integer> map = new HashMap<>();

        int numTests = 1000000;

        for (int i = 0; i < numTests; i++) {
            Node<Integer> randomNode = tree.getRandom();
            map.merge(randomNode.value, 1, (i1, i2) -> i1 + 1);
        }

        for (Integer i : map.keySet()) {
            System.out.println(String.format("%d: %f%%", i,
                    (double) map.get(i) / (double) numTests * 100.));
        }
    }

    private static Tree<Integer> generateTree() {
        int[] ints = IntStream.range(0, 100).toArray();
        Random random = new Random();

        for (int i = 0; i < ints.length; i++) {
            int pos = random.nextInt(ints.length);
            int val = ints[i];
            ints[i] = ints[pos];
            ints[pos] = val;
        }

        Tree<Integer> tree = new Tree<>();

        for (int i = 0; i < ints.length; i++) {
            tree.insert(ints[i]);
        }
        return tree;
    }

    public static class Tree<T extends Comparable<T>> {
        private Node<T> root;

        public void insert(T elem) {
            if (root == null) {
                root = new Node(elem);
            } else {
                root.insert(elem);
            }
        }

        public Node<T> getRandom() {
            if (root != null)
                return root.getRandom();

            return null;
        }
    }

    // split probabilities evenly:
    // pick root if 1/n
    // pick left tree if 1/(left tree size)
    // pick right tree if 1/(right tree size)
    public static class Node<T extends Comparable<T>>  {
        private T value;
        private Node left;
        private Node right;
        private int size;

        public Node(T value) {
            this.value = value;
            this.size = 1;
        }

        public void insert(T elem) {
            if (value.compareTo(elem) > 0) {
                if (left == null) left = new Node(elem);
                else left.insert(elem);
            } else {
                if (right == null) right = new Node(elem);
                else right.insert(elem);
            }

            size++;
        }

        public Node<T> getRandom() {
            Random random = new Random();

            Node current = this;

            while (true) {
                Node left = current.left;
                int leftSize = left != null ? left.size : 0;
                int rand = random.nextInt(current.size);

                if (rand == leftSize)
                    return current;

                if (rand < leftSize)
                    current = current.left;
                else
                    current = current.right;
            }
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
