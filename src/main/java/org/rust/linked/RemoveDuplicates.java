package org.rust.linked;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RemoveDuplicates {
    public static void main(String[] args) {
        Node root = Node.build(1, 1, 3, 2, 5, 1, 5);
        hashing(root);
        System.out.println(root);

        Node root1 = Node.build(1, 1, 3, 2, 5, 1, 5);
        loops(root1);
        System.out.println(root1);
    }

    public static void hashing(Node root) {
        Set<Object> set = new HashSet<>();

        for (Node tmp = root, prev = null; tmp != null; tmp = tmp.next) {
            if (set.contains(tmp.data)) {
                prev.next = tmp.next;
            } else  {
                prev = tmp;
                set.add(tmp.data);
            }
        }
    }

    public static void loops(Node root) {
        for (Node outer = root; outer != null; outer = outer.next) {
            for (Node inner = outer.next, prev = outer; inner != null; inner = inner.next) {
                if (Objects.equals(outer.data, inner.data)) {
                    prev.next = inner.next;
                } else {
                    prev = inner;
                }
            }
        }
    }
}
