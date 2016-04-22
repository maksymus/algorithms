package org.interviewelements.tree;

/**
 * Base class for tries.
 * 
 * @author maksym
 * @param <T>
 */
public abstract class Tree<T extends Comparable<T>> {

    public static class Node<S> {
        public S data;

        Node(S data) {
            this.data = data;
        }

        S getData() {
            return data;
        }
    }

    // public abstract T find(T key);
    public abstract boolean contains(T key);

    public abstract void insert(T key);

    public abstract void delete(T key);

    public abstract int size();
}
