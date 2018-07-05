package org.rust.linked;

/**
 * Given head pointer of a linked list, sort it in ascending order using insertion sort.
 */
public class InsertionSort {
    public static void main(String[] args) {
        Node sorted = sort(Node.build(1, 3, 2, 4));
        System.out.println(sorted);
    }

    public static Node<Integer> sort(Node<Integer> root) {
        for (Node<Integer> outer = root, outerPrev = null; outer != null; ) {
            label:
            for (Node<Integer> inner = root, innerPrev = null; inner != outer;) {
                if (Integer.compare(outer.data, inner.data) < 0) {
                    outerPrev.next = outer.next;

                    if (innerPrev != null)
                        innerPrev.next = outer;
                    else
                        root = outer;

                    outer.next = inner;
                    outer = outerPrev;

                    break label;
                }

                innerPrev = inner;
                inner = inner.next;
            }

            outerPrev = outer;
            outer = outer.next;
        }

        return root;
    }
}
