package org.rust.linked;

import java.util.Comparator;
import java.util.Objects;

/**
 * Given two sorted linked lists, merge them such that resulting linked list is also sorted.
 */
public class MergeSorted {
    public static void main(String[] args) {
        Node merged = merge(Node.build(1, 3, 5, 7), Node.build(2, 4));
        System.out.println(merged);
    }

    public static <T extends Comparable<T>> Node<T> merge(Node<T> root1, Node<T> root2) {
        Node<T> node1 = root1;
        Node<T> node2 = root2;

        Node<T> root = null;
        Node<T> current = null;

        while (node1 != null || node2 != null) {
            int result = Objects.compare(node1, node2, (n1, n2) -> {
                if (n1 == null)
                    return 1;
                else if (n2 == null)
                    return -1;

                return Objects.compare(n1.data, n2.data,
                    Comparator.nullsFirst(Comparator.naturalOrder()));
            });

            Node node = new Node();

            if (result <= 0) {
                node.data = node1.data;
                node1 = node1.next;
            } else {
                node.data = node2.data;
                node2 = node2.next;
            }

            if (root == null) {
                root = current = node;
            } else {
                current.next = node;
                current = node;
            }
        }

        return root;
    }
}
