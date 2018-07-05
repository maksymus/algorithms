package org.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Linked list link.
 */
public class Link<S> {
    protected Link<S> next;
    protected S data;

    public Link(S data) {
        this.data = data;
    }

    public Link(Link<S> next, S data) {
        this.next = next;
        this.data = data;
    }

    public List<S> toList() {
        ArrayList<S> list = new ArrayList<S>();

        Link<S> tmp = this;
        while (tmp != null) {
            list.add(tmp.data);
            tmp = tmp.next;
        }

        return list;
    }

    public Link<S> getNext() {
        return next;
    }

    public void setNext(Link<S> next) {
        this.next = next;
    }
}
