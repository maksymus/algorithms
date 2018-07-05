package org.rust.tree;

class Node {
    Node left;
    Node right;
    int data;

    public Node(int data) {
        this.data = data;
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}