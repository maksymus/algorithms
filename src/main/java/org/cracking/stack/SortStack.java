package org.cracking.stack;

/**
 * Write a program to sort a stack such that the smallest items are on the top. You can use
 * an additional temporary stack, but you may not copy the elements into any other data structure
 * (such as an array). The stack supports the following operations: push, pop, peek, and is Empty.
 */
public class SortStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(4);
        stack.push(2);
        stack.push(5);
        stack.push(3);

        Stack<Integer> sorted = new SortStack().sort(stack);
        print(sorted);
    }

    public <T extends Comparable<T>> Stack<T> sort(Stack<T> stack) {
        Stack<T> helper = new Stack<>();

        while (!stack.isEmpty()) {
            T elem = stack.pop();

            while (!helper.isEmpty() && helper.top().compareTo(elem) > 0)
                stack.push(helper.pop());

            helper.push(elem);
        }

        while (!helper.isEmpty())
            stack.push(helper.pop());

        return stack;
    }

    private static class Stack<T> {
        private Node root;

        public void push(T elem) {
            root = new Node(elem, root);
        }

        public T pop() {
            T data = (T) root.data;
            root = root.next;
            return data;
        }

        public T top() {
            return (T) root.data;
        }

        public boolean isEmpty() {
            return root == null;
        }
    }

    private static void print(Stack<?> stack) {
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
