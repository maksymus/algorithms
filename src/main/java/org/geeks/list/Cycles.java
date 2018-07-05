package org.geeks.list;

/**
 * Given a linked list, determine if it has a cycle in it.
 */
public class Cycles {
    public boolean hasCycle(ListNode node) {
        ListNode fast = node;
        ListNode slow = node;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                return true;
        }

        return false;
    }
}
