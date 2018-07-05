package org.sedgewick.stackqueue;

import java.util.Stack;

/**
 * Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) and also a
 * return-the-maximum operation. Assume the elements are reals numbers so that you can compare t
 */
public class StackWithMax<T extends Comparable<T>> {

    private Stack<T> stack = new Stack<>();
    private Stack<T> maxStack = new Stack<>();

    public void push(T elem) {
        stack.push(elem);

        if (maxStack.isEmpty())
            maxStack.push(elem);
        else {
            T max = maxStack.peek().compareTo(elem) > 0 ? maxStack.peek() : elem;
            maxStack.push(max);
        }
    }

    public T pop() {
        T elem = stack.peek();
        maxStack.pop();
        stack.pop();
        return elem;
    }

    public T max() {
        return maxStack.peek();
    }

    public static void main(String[] args) {
        StackWithMax<Integer> s = new StackWithMax<>();
        s.push(3);
        s.push(6);
        s.push(4);
        s.push(5);

        System.out.println(s.max());
        s.pop();

        System.out.println(s.max());
        s.pop();

        System.out.println(s.max());
        s.pop();

        System.out.println(s.max());
        s.pop();
    }
}
