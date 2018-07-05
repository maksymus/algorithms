package org.rust.linked;

/**
 * Given a singly linked list, reverse nodes at even indices.
 * Input List:  1->2->3->4->5->6
 * Output List: 1->3->5->6->4->2
 */
public class ReverseEvenNodes {
    public static void main(String[] args) {
        Node reversed = reverse(Node.build(1, 2, 3, 4, 5, 6));
        System.out.println(reversed);
    }

    public static Node reverse(Node root) {
        Node odd = root;
        Node even = null;

        Node prevOdd = null;

        while (odd != null && odd.next != null) {
            prevOdd = odd;

            Node tmp = even;
            even = odd.next;

            odd.next = odd.next.next;
            odd = odd.next;

            even.next = tmp;
        }

        if (odd != null)
            odd.next = even;
        else if (prevOdd != null)
            prevOdd.next = even;

        return root;
    }
}
