package org.rust.linked;

/**
 * Given head nodes of two linked lists that may or may not intersect, find out if they intersect and return the
 * point of intersection; return null otherwise.
 */
public class IntersectionPoint {
    public static void main(String[] args) {

    }

    public static Node find(Node root1, Node root2) {
        int length1 = length(root1);
        int length2 = length(root2);

        Node start1 = root1;
        Node start2 = root2;

        if (length1 > length2) {
            for (int i = 0; i < length1 - length2; i++) {
                start1 = start1.next;
            }
        } else if (length2 > length1) {
            for (int i = 0; i < length2 - length1; i++) {
                start2 = start2.next;
            }
        }

        for (Node n1 = start1, n2 = start2; n1 != null; n1 = n1.next, n2 = n2.next) {
            if (n1 == n2) {
                return n1;
            }
        }

        return null;
    }

    private static int length(Node root) {
        int length = 0;

        for (Node node = root; node != null; node = node.next) {
            length++;
        }

        return length;
    }
}
