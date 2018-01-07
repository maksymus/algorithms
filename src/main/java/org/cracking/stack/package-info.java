package org.cracking.stack;

// Stacks and Queues

class Node<T> {
    Node<T> next;
    T data;

    public Node(T data, Node<T> next) {
        this.next = next;
        this.data = data;
    }

    public Node(T data) {
        this.data = data;
    }
}