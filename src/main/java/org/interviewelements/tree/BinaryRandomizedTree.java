package org.interviewelements.tree;

import java.util.Random;

public class BinaryRandomizedTree<T extends Comparable<T>> extends BinaryTree<T> {

    private Random random = new Random();

    @Override
    public void insert(T element) {
        root = insertRandom(root, element);
    }

    // ======================================================

    private BinaryNode insertRandom(BinaryNode node, T element) {

        if (node == null)
            return new BinaryNode(element);

        int count = node.size;

        if (random.nextInt(count + 1) == count) {
            return insertRoot(node, element);
        }

        if (node.data.compareTo(element) > 0) {
            node.left = insertRandom(node.left, element);
        } else {
            node.right = insertRandom(node.right, element);
        }

        node.size = size(node);

        return node;
    }

    private BinaryNode insertRoot(BinaryNode node, T element) {
        if (node == null)
            return new BinaryNode(element);

        if (node.data.compareTo(element) > 0) {
            node.left = insertRoot(node.left, element);
            node = rotateRight(node);
        } else {
            node.right = insertRoot(node.right, element);
            node = rotateLeft(node);
        }

        return node;
    }

    private BinaryNode rotateRight(BinaryNode node) {
        BinaryNode tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;

        node.size = size(node);
        tmp.size = size(tmp);

        return tmp;
    }

    private BinaryNode rotateLeft(BinaryNode node) {
        BinaryNode tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;

        node.size = size(node);
        tmp.size = size(tmp);

        return tmp;
    }

    private int size(BinaryNode node) {
        if (node == null)
            return 0;

        int leftSize = node.left != null ? node.left.size : 0;
        int rightSize = node.right != null ? node.right.size : 0;

        return 1 + leftSize + rightSize;
    }

    public static void main(String[] args) {
        BinaryRandomizedTree<Integer> binTree = new BinaryRandomizedTree<>();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {
            binTree.insert(i);
        }

        long end = System.currentTimeMillis();

        System.out.println("Took: " + (end - start));

        // System.out.println("inserted");
        //
        System.out.println(binTree.size() + ": " + binTree.depth());
        //
        // for (int i = 0; i < 100; i++) {
        // System.out.println((int) (Math.random() * 4));
        // }
    }
}
