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

        public Node(T value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}

class Graph {
    private List<Node> nodes = new ArrayList<>();
    private boolean direct;

    public Graph(boolean direct) {
        this.direct = direct;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void join(Node node1, Node node2) {
        if (node1 == node2)
            return;

        node1.join(node2);

        if (!direct)
            node2.join(node1);
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public void add(Node ... nodes) {
        for (Node node : nodes) {
            add(node);
        }
    }

    public static class Node<T> {
        private T value;
        private List<Node> adjacent = new ArrayList<>();

        public Node(T value) {
            this.value = value;
        }

        private void join(Node other) {
            if (this == other)
                return;

            for (Node node : adjacent) {
                if (node == other)
                    return;
            }

            adjacent.add(other);
        }

        public List<Node> getAdjacent() {
            return adjacent;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}