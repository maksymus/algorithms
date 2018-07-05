package org.interviewelements;

import org.util.Link;

public class LinkedLists {
    public static <T> Link<T> reverse(Link<T> list) {
        Link<T> prev = null;
        while (list != null) {
            Link<T> tmp = list.getNext();
            list.setNext(prev);
            prev = list;
            list = tmp;
        }

        return prev;
    }

    public static boolean isCyclic(Link<?> link) {
        Link<?> current = link;
        Link<?> skipping = link;

        boolean even = false;

        while (current != null) {
            current = current.getNext();

            if (current == skipping)
                return true;

            if (even)
                skipping = skipping.getNext();

            even = !even;

        }

        return false;
    }
}
