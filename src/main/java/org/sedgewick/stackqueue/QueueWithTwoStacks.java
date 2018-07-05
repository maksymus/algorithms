package org.sedgewick.stackqueue;

import java.util.Stack;

/*
 * Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.
 */
public class QueueWithTwoStacks<T extends  Comparable<T>>{
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();

    public void enqueue(T elem) {
        stack1.push(elem);
    }

    public T dequeue() {
        if (stack2.isEmpty())
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());

        return stack2.pop();
    }

    public static void main(String[] args) {
        QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());

        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
