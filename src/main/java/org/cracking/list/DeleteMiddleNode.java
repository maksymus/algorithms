package org.cracking.list;

/**
 * Implement an algorithm to delete a node in the middle (i.e., any node but
 * the first and last node, not necessarily the exact middle) of a singly linked list, given only access to
 * that node.
 * EXAMPLE
 * Input: the node c from the linked list a->b->c->d->e->f
 * Result: nothing is returned, but the new linked list looks like a->b->d->e->f
 */
public class DeleteMiddleNode {
    public static void main(String[] args) {
        Node f = new Node('f');
        Node e = new Node('e', f);
        Node d = new Node('d', e);
        Node c = new Node('c', d);
        Node b = new Node('b', c);
        Node a = new Node('a', b);

        new DeleteMiddleNode().delete(b);
        System.out.println(a);
    }

    public void delete(Node node) {
        if (node == null || node.next == null)
            return;

        Node next = node.next;
        node.data = next.data;
        node.next = next.next;
    }
}
