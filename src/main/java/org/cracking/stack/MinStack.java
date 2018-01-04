package org.cracking.stack;

import java.util.Comparator;
import java.util.Objects;

/**
 * How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Push, pop and min should all operate in 0(1) time.
 */
public class MinStack {
    public static void main(String[] args) {

    }


    private static class Stack<T extends Comparable<T>> {
        private Node<T> stack;
        private Node<T> min;

        public void push(T value) {
            if (stack == null) {
                stack = new Node(value);
                min = new Node(value);
            } else {
                T minValue = min.data.compareTo(value) <= 0 ? min.data : value;
                stack = new Node(value, stack);
                min = new Node(minValue, min);
            }
        }

        public T pop() {
            if (stack == null)
                throw new IllegalArgumentException("stack is empty");

            T data = stack.data;
            stack = stack.next;
            min = min.next;

            return data;
        }

        public T min() {
            if (stack == null)
                throw new IllegalArgumentException("stack is empty");

            return min.data;
        }
    }

    private static class Node<T> {
        Node<T> next;
        T data;

        public Node(T data, Node<T> next) {
            this.next = next;
            this.data = data;
        }

        public Node(T data) {
            this.data = data;
        }
    }
}



