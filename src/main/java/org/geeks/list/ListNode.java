package org.geeks.list;

/**
 * Created by maksym on 7/4/17.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode build(int[] vals) {

        ListNode previous = null, first = null;
        for (int i = 0; i < vals.length; i++) {
            ListNode current = new ListNode(vals[i]);

            if (previous == null) {
                first = current;
            } else {
                previous.next = current;
            }

            previous = current;
        }

        return first;
    }

    public int length() {
        int length = 0;

        for (ListNode tmp = this; tmp != null; tmp = tmp.next, length++);

        return length;

    }

    @Override
    public String toString() {
        if (next == null)
            return String.valueOf(val);

        return val + " -> " + next.toString();
    }
}
