package org.geeks.list;

/**
 * Given a singly linked list L: L0→L1→ ... →Ln-1→Ln,
 * reorder it to:                L0→Ln→L1→Ln-1→L2→Ln-2→...
 * <p>
 * For example, given {1,2,3,4}, reorder it to {1,4,2,3}.
 * You must do this in-place without altering the nodes' values.
 */
public class ReorderList {
    public static void main(String[] args) {
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4,
                new ListNode(5, new ListNode(6, new ListNode(7)))))));

        ReorderList reorderList = new ReorderList();

        ListNode middle = reorderList.middle(root);
        ListNode reorder = reorderList.reorder(middle);
        reorderList.merge(root, reorder);
    }

    private ListNode middle(ListNode node) {
        ListNode fast = node, slow = node;

        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    private ListNode reorder(ListNode node) {
        ListNode previous = null;

        while (node != null) {
            ListNode tmp = node.next;
            node.next = previous;
            previous = node;
            node = tmp;
        }

        return previous;
    }

    public void merge(ListNode left, ListNode right) {
        if (left == null && right == null)
            return;

        ListNode current = null;

        while (true) {
            if (left == null) {
                current.next = right;
                return;
            }

            if (right == null) {
                current.next = left;
                return;
            }

            ListNode leftNext = left.next;
            ListNode rightNext = right.next;

            if (current == null) {
                current = left;
                current.next = right;
            } else {
                current.next = left;
                current.next.next = right;
            }

            current = right;

            left = leftNext;
            right = rightNext;
        }
    }
}

