package org.cracking.list;

/**
 * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
 */
public class NthLastElement <T> {
    public static void main(String[] args) {
        Node<Integer> root = Node.build(1, 2, 3, 4, 5);
        Integer i = new NthLastElement<Integer>().find(root, 1);
        System.out.println(i);
    }

    public T find(Node<T> root, int klast) {
        int count = 1;
        Node runner = null;

        for (Node node = root; node != null; node = node.next) {
            if (count == klast) {
                runner = root;
            } else if (count > klast) {
                runner = runner.next;
            }

            count++;
        }

        return runner != null ? (T) runner.data : null;
    }
}
