package org.cracking.list;

/**
 * Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node.
 * Note that the intersection is defined based on reference, not value. That is, if the kth
 * node of the first linked list is the exact same node (by reference) as the jth node of the second
 * linked list, then they are intersecting.
 */
public class Intersection {
    public static void main(String[] args) {
        Node root1 = Node.build("root1");
        Node root2 = Node.build("root2");
        root1.next = root2;

        System.out.println(new Intersection().find(root1, root2));
    }

    public Node find(Node node1, Node node2) {
        int length1 = length(node1);
        int length2 = length(node2);

        if (length1 < length2) {
            node2 = skip(node2, length2 - length1);
        } else if (length1 > length2) {
            node1 = skip(node1, length1 - length2);
        }

        while (node1 != null || node2 != null) {
            if (node1 == node2)
                return node1;

            node1 = node1.next;
            node2 = node2.next;
        }

        return null;
    }

    private Node skip(Node node, int n) {
        while (node != null && n > 0) {
            node = node.next;
            n--;
        }

        return node;
    }

    private int length(Node node) {
        int count = 0;

        while (node != null) {
            node = node.next;
            count++;
        }

        return count;
    }
}
