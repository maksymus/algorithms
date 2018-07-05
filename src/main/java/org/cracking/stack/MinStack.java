package org.cracking.stack;

import java.util.Comparator;
import java.util.Objects;

/**
 * How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Push, pop and min should all operate in 0(1) time.
 */
public class MinStack {
    public static void main(String[] args) {
        Stack<Integer> minStack = new Stack<>();
        minStack.push(4);
        minStack.push(5);
        minStack.push(7);
        minStack.push(2);
        minStack.push(8);
        minStack.push(9);
        minStack.push(1);
        minStack.push(3);

        while (!minStack.isEmpty()) {
            System.out.println(String.format("top: %d min %d", minStack.top(), minStack.min()));
            minStack.pop();
        }
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

        public T top() {
            if (stack == null)
                throw new IllegalArgumentException("stack is empty");

            return stack.data;
        }

        public T min() {
            if (stack == null)
                throw new IllegalArgumentException("stack is empty");

            return min.data;
        }

        public boolean isEmpty() {
            return stack == null;
        }
    }
}



