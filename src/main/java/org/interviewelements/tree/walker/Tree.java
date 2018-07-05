package org.interviewelements.tree.walker;

import java.util.ArrayList;
import java.util.List;

public class Tree<T extends Comparable<T>> {

    public static class Node<T> {
        private T data;
        private List<Node<T>> leaves = new ArrayList<Node<T>>();

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public List<Node<T>> getLeaves() {
            return leaves;
        }
    }

    private Node<T> root = null;

    public Node<T> add(Node<T> node, T data) {
        Node<T> e = new Node<T>(data);

        if (node == null && root == null)
            return root = e;
        if (root == null)
            throw new NullPointerException("Root is empt");
        if (node == null)
            throw new NullPointerException("Node is null");

        node.getLeaves().add(e);

        return e;
    }

    public Node<T> getRoot() {
        return root;
    }
}
