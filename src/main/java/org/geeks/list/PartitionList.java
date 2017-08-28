package org.geeks.list;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example, given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
 */
public class PartitionList {
    public ListNode partision(ListNode root, int x) {

        ListNode left = null;
        ListNode right = null;
        ListNode rightRoot = null;

        for (ListNode tmp = root; tmp != null; tmp = tmp.next) {
            if (tmp.val < x) {
                if (left != null) {
                    left.next = tmp;
                } else {
                    left = tmp;
                }
            } else {
                if (right != null) {
                    right.next = tmp;
                } else {
                    right = tmp;
                    rightRoot = tmp;
                }
            }
        }

        if (left == null)
            return rightRoot;

        left.next = rightRoot;

        return left;
    }
}
