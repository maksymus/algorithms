package org.cracking.list;

import java.util.Comparator;
import java.util.Objects;

/**
 * Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
 * before all nodes greater than or equal to x. If x is contained within the list the values of x only need
 * to be after the elements less than x (see below). The partition element x can appear anywhere in the
 * "right partition"; it does not need to appear between the left and right partitions.
 */
public class PartitionList {
    public static void main(String[] args) {
        Node partition = new PartitionList().partition(Node.build(1, 3, 2, 6, 9, 3), 2);
        System.out.println(partition);
    }

    public <T extends Comparable<T>> Node<T> partition(Node<T> node, T elem) {
        Node head = node;
        Node tail = node;

        while (node != null) {
            Node next = node.next;

            if (Objects.compare(node.data, elem, Comparator.naturalOrder()) <= 0) {
                node.next = head;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }

            node = next;
        }

        tail.next = null;

        return head;
    }
}
