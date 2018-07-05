package org.sedgewick.stackqueue;

/*
 * Detect cycle in a linked list. A singly-linked data structure is a data structure made up of nodes where each node
 * has a pointer to the next node (or a pointer to null). Suppose that you have a pointer to the first node of a
 * singly-linked list data structure:
 * <li> Determine whether a singly-linked data structure contains a cycle. You may use only two pointers into the
 *      list (and no other variables). The running time of your algorithm should be linear in the number of nodes in the data structure.
 * <li> If a singly-linked data structure contains a cycle, determine the first node that participates in the cycle.
 *      You may use only a constant number of pointers into the list (and no other variables).
 *      The running time of your algorithm should be linear in the number of nodes in the data structure.
 * You may not modify the structure of the linked list.
 */
public class LinkedListCycle {
    private static class Node {
        public Node(int idx, Node next) {
            this.idx = idx;
            this.next = next;
        }

        private int idx;
        private Node next;
    }

    public static boolean hasCycles(Node head) {
        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, null);
        Node node3 = new Node(3, null);
        Node node4 = new Node(4, null);
        Node node5 = new Node(5, null);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        System.out.println(hasCycles(node1));
    }
}
