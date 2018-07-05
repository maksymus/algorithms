package org.geeks.list;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 * The program should run in O(1) space complexity and O(nodes) time complexity.
 * Example:
 *   Given 1->2->3->4->5->NULL,
 *  return 1->3->5->2->4->NULL.
 */
public class OddEvenList {
    public static void main(String[] args) {
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4,
                new ListNode(5, new ListNode(6, new ListNode(7)))))));

//        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4,
//                new ListNode(5, new ListNode(6))))));

        new OddEvenList().connect(root);
        System.out.println(root);

    }

    public void connect(ListNode root) {
        if (root == null || root.next == null)
            return;

        ListNode oddCurrent = root;
        ListNode evenRoot = root.next;
        ListNode current = root;

        while (current.next != null) {
            ListNode tmp = current.next;
            current.next = current.next.next;
            current = tmp;

            if (oddCurrent.next == current)
                oddCurrent = current;
        }

        oddCurrent.next = evenRoot;
    }
}
