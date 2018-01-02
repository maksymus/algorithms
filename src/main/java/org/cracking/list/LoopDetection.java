package org.cracking.list;

/**
 * Given a circular linked list, implement an algorithm that returns the node at the
 * beginning of the loop.
 *
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
 * as to make a loop in the linked list.
 *
 * EXAMPLE
 * Input:  A -> B -> C -> D -> E -> C [the same C as earlier]
 * Output: C
 */
public class LoopDetection {
    public static void main(String[] args) {
        Node e = new Node('e');
        Node d = new Node('d', e);
        Node c = new Node('c', d);
        Node b = new Node('b', c);
        Node a = new Node('a', b);
        e.next = c;

        System.out.println(new LoopDetection().findLoop(a));
    }

    public Node findLoop(Node root) {
        Node node = root;
        Node runner = root;

        while (runner != null && runner.next != null) {
            node = node.next;
            runner = runner.next.next;

            if (node == runner)
                break;
        }

        node = root;

        while (runner != null) {
            if (runner == node) {
                return runner;
            }

            node = node.next;
            runner = runner.next;
        }

        return null;
    }
}


