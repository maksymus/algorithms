package org.cracking.list;

/**
 * Sum Lists: You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
 * function that adds the two numbers and returns the sum as a linked list.
 *
 * EXAMPLE
 * Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
 * Output: 2 -> 1 -> 9. That is, 912.

 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
 * Output: 9 -> 1 -> 2. That is, 912.
 */
public class SumList {
    public static void main(String[] args) {
        Node result = new SumList().sumReverse(Node.build(7, 1, 6), Node.build(5, 9, 2));
        System.out.println(result);
    }

    public Node<Integer> sumReverse(Node<Integer> left, Node<Integer> right) {
        Node current = null;
        int overflow = 0;

        while (left != null || right != null) {
            int result = overflow
                    + (left != null ? left.data : 0)
                    + (right != null ? right.data : 0);

            Node node = new Node(result % 10);
            overflow = result / 10;

            node.next = current;
            current = node;

            left = left != null ? left.next : null;
            right = right != null ? right.next : null;
        }

        return current;
    }

    // TODO add sumForward()
}
