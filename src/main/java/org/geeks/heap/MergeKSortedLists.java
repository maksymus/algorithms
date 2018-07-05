package org.geeks.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted lists.

 * Given m linked lists, the minimum elements of all arrays can form a heap.
 * It takes O(log(m)) to insert an element to the heap and it takes O(1) to delete the minimum element.
 */
public class MergeKSortedLists {
    private static class Node {
        Node next;
        int i;

        public Node(int i, Node next) {
            this.next = next;
            this.i = i;
        }
    }

    public Node merge(Node[] nodes) {
        if (nodes == null)
            return null;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.i));

        Node root = null;
        Node result = null;

        for (Node node : nodes) {
            if (node == null)
                continue;

            pq.offer(node);
        }

        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            if (result != null)
                result.next = poll;
            else
                root = poll;

            result = poll;

            if (poll.next != null)
                pq.offer(poll.next);
        }

        return root;
    }

    public static void main(String[] args) {
        Node root1 = new Node(1, new Node(4, new Node(5, null)));
        Node root2 = new Node(2, new Node(3, new Node(6, null)));

        Node merge = new MergeKSortedLists().merge(new Node[] {root1, root2});

        for (Node tmp = merge; tmp != null; tmp = tmp.next) {
            System.out.print(tmp.i + " ");
        }
    }
}
