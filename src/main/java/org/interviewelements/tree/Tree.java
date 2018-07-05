package org.interviewelements.tree;

/**
 * Base class for tries.
 * 
 * @author maksym
 * @param <T>
 */
public interface Tree<T> {

    public static class Node<S> {
        public S data;

        protected Node(S data) {
            this.data = data;
        }

        S getData() {
            return data;
        }
    }

    boolean contains(T key);
    void insert(T key);
    void delete(T key);
    int size();
}
