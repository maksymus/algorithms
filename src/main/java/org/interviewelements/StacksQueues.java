package org.interviewelements;

import java.util.NoSuchElementException;
import java.util.Stack;

public class StacksQueues {

    /** Stack with max element */
    public static class StackMax <T extends Comparable> {
        private Stack<T> stack = new Stack<>();
        private Stack<T> max = new Stack<>();

        public void push(T item) {
            stack.push(item);
            if (max.empty()) {
                max.push(item);
            } else {
                T maxItem = max.peek().compareTo(item) > 0 ? max.peek() : item;
                this.max.push(maxItem);
            }
        }

        public T pop() {
            max.pop();
            return stack.pop();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }

        public T max() {
            return max.peek();
        }
    }

    /** Circular queue */
    public static class CircularQueue<T> {
        private int size;
        private int start;
        private int end;

        private Object[] data;

        public CircularQueue() {
            data = new Object[2];
        }

        public void enqueue(T item) {
            resize();

            data[end] = item;
            end = ++end & (data.length - 1);
            size++;
        }

        private T dequeue() {
            if (start == end)
                throw new NoSuchElementException();

            T item = (T) data[start];
            data[start] = null;
            start = ++start & (data.length - 1);
            size--;

            resize();

            return item;
        }

        private void resize() {
            if (size >= data.length * 3 / 4) {
                data = copy(data.length << 1);
            } else if (size < data.length / 4) {
                data = copy(data.length >> 1);
            }
        }

        private Object[] copy(int newSize) {
            Object[] copyTo = new Object[newSize];
            if (start == end) {
                assert(size == 0) : "start == end but size != 0";
                start = end = 0;
                return copyTo;
            }

            int length = end > start ? end - start : data.length - start;
            System.arraycopy(data, start, copyTo, 0, length);

            if (end < start) {
                System.arraycopy(data, 0, copyTo, length, end);
                length += end;
            }

            start = 0;
            end = length;

            return copyTo;
        }
    }

    /** Queue with two stacks */
    public static class TwoStackQueue <T> {
        private Stack<T> stack1 = new Stack<>();
        private Stack<T> stack2 = new Stack<>();

        public void enqueue(T item) {
            stack1.push(item);
        }

        public T dequeue() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }

            if (stack2.isEmpty())
                throw new NoSuchElementException("queue is empty");

            return stack2.pop();

        }

    }

    /** Sorting with stack */
    public <T extends Comparable> void stackSort(Stack<T> s) {
        if (!s.isEmpty()) {
            T element = s.pop();
            stackSort(s);
            stackSort(s, element);
        }
    }
    private <T extends Comparable> void stackSort(Stack<T> s, T element) {
        if (s.isEmpty() || element.compareTo(s.peek()) >= 0) {
            s.push(element);
        } else {
            T pop = s.pop();
            stackSort(s, element);
            s.push(pop);
        }
    }

    public static void main(String[] args) {
        CircularQueue<Integer> q = new CircularQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println(q.dequeue());
        q.enqueue(4);
        System.out.println(q.dequeue());

        q.enqueue(5);
        System.out.println(q.dequeue());
        q.enqueue(6);
        System.out.println(q.dequeue());
        q.enqueue(7);
        System.out.println(q.dequeue());
        q.enqueue(8);
        q.enqueue(9);

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}
