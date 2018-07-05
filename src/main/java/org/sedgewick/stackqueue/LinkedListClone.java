package org.sedgewick.stackqueue;

/*
 * Clone a linked structure with two pointers per node. Suppose that you are given a reference to the first node
 * of a linked structure where each node has two pointers: one pointer to the next node in the
 * sequence (as in a standard singly-linked list) and one pointer to an arbitrary node.
 *
 *  private class Node {
 *      private String item;
 *      private Node next;
 *      private Node random;
 *  }
 *
 * Design a linear-time algorithm to create a copy of the doubly-linked structure. You may modify the original linked
 * structure, but you must end up with two copies of the original.
 */

import java.util.ArrayList;
import java.util.Random;

class Node {
    int data;
    Node next;
    Node random;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("Node{data=%d, next=%s, random=%s}",
                data,
                next != null ? next.data : "",
                random != null ? random.data : "");
    }
}

public class LinkedListClone {

    public static void main(String[] args) {
        Node startNode = new LinkedListClone().generate(10);
        new LinkedListClone().printNodes(startNode);

        Node clone = new LinkedListClone().clone(startNode);
        System.out.println(clone);
    }

    public Node clone(Node node) {
        Node copy = copy(node);
        resetRandomLinks(node, copy);
        fixRandomLinks(node, copy);
        return copy;
    }

    private Node copy(Node node) {
        Node current = node;
        Node start = null, previous = null;

        while (current != null) {
            Node tmp = new Node(current.data);

            if (previous != null) {
                previous.next = tmp;
                previous = tmp;
            } else {
                start = previous = tmp;
            }

            current = current.next;
        }

        return start;
    }

    private void resetRandomLinks(Node node, Node copy) {
        Node currentNode = node;
        Node currentCopy = copy;

        while (currentNode != null && currentCopy != null) {
            currentCopy.random = currentNode.random;
            currentNode.random = currentCopy;

            currentNode = currentNode.next;
            currentCopy = currentCopy.next;
        }
    }

    private void fixRandomLinks(Node node, Node copy) {
        Node currentNode = node;
        Node currentCopy = copy;

        while (currentNode != null && currentCopy != null) {
            Node random = currentCopy.random;

            currentCopy.random = (random != null ? random.random : null);
            currentNode.random = random;

            currentNode = currentNode.next;
            currentCopy = currentCopy.next;
        }
    }

    public Node generate(int numNodes) {
        Node previous = null, start = null;
        ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < numNodes; i++) {
            Node node = new Node(i + 1);
            nodes.add(node);

            if (previous == null) {
                start = node;
            } else {
                previous.next = node;
            }

            previous = node;
        }

        for (Node node = start; node != null; node = node.next) {
            int randomIdx = new Random().nextInt(nodes.size());
            node.random = nodes.get(randomIdx);
            nodes.remove(randomIdx);
        }

        return start;
    }

    public void printNodes(Node start) {
        System.out.println("Start =====================================");
        for (Node node = start; node != null; node = node.next) {
            System.out.println(node);
        }
        System.out.println("End =====================================");
    }
}
