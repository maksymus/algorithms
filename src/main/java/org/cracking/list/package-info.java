package org.cracking.list;

// Chapter 2. Linked List

import java.util.Arrays;

class Node<T> {
    Node next = null;
    T data;

    public Node() {
        this(null, null);
    }

    public Node(T data) {
        this(data, null);
    }

    public Node(T data, Node next) {
        this.next = next;
        this.data = data;
    }

    @Override
    public String toString() {
        String str = data != null ? data.toString() : "";
        return str + (next != null ? "->" + next.toString() : "");
    }

    public static <T> Node build(T ... data) {
        if (data == null || data.length == 0)
            return null;

        Node root = null, current = null;
        for (int i = 0; i < data.length; i++) {
            Node tmp = new Node(data[i]);

            if (root == null) {
                root = tmp;
            } else {
                current.next = tmp;
            }

            current = tmp;
        }

        return root;
    }
}