package org.cracking.graph;

// Trees and Graphs

import java.util.ArrayList;
import java.util.List;

class Tree {
    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public static class Node<T>  {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public T getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}

class Graph<T> {
    private List<Node<T>> nodes = new ArrayList<>();
    private boolean direct;

    public Graph(boolean direct) {
        this.direct = direct;
    }

    public List<Node<T>> getNodes() {
        return nodes;
    }

    public void join(Node<T> node1, Node<T> node2) {
        if (node1 == node2)
            return;

        node1.join(node2);

        if (!direct)
            node2.join(node1);
    }

    public void add(Node<T> node) {
        nodes.add(node);
    }

    public void add(Node<T> ... nodes) {
        for (Node node : nodes) {
            add(node);
        }
    }

    public static class Node<T> {
        private T value;
        private List<Node<T>> adjacents = new ArrayList<>();

        public Node(T value) {
            this.value = value;
        }

        private void join(Node<T> other) {
            if (this == other)
                return;

            for (Node node : adjacents) {
                if (node == other)
                    return;
            }

            adjacents.add(other);
        }

        public List<Node<T>> getAdjacents() {
            return adjacents;
        }

        public T getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}