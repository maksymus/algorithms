package org.cracking.list;

import java.util.Objects;

/**
 * Write code to remove duplicates from an unsorted linked list.
 * How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDups {
    public static void main(String[] args) {
        Node<Integer> root = Node.build(1, 2, 1, 2, 3);

        new RemoveDups().remove(root);
        System.out.println(root);
    }

    void remove(Node root) {
        for (Node node = root; node != null; node = node.next) {
            Node runner = node;
            while (runner.next != null) {
                Node next = runner.next;
                if (Objects.equals(node.data, next.data)) {
                    runner.next = next.next;
                } else {
                    runner = runner.next;
                }
            }
        }
    }
}
