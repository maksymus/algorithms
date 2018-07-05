package org.cracking.stack;

import java.util.Arrays;

/**
 * Three in One: Describe how you could use a single array to implement three stacks.
 */
public class ThreeStack {
    public static void main(String[] args) {
        ThreeStackStatic threeStackStatic = new ThreeStackStatic(3, 10);
        threeStackStatic.push(0, 1);
        threeStackStatic.push(0, 2);
        threeStackStatic.push(0, 3);

        threeStackStatic.push(1, 1);
        threeStackStatic.push(2, 1);

        System.out.println(threeStackStatic.pop(0));
        System.out.println(threeStackStatic.pop(0));
        System.out.println(threeStackStatic.pop(0));



        System.out.println(Arrays.toString(threeStackStatic.stacks));
    }
}

class ThreeStackStatic <T> {
    private int number;
    private int capacity;
    private int[] sizes;
    T[] stacks;

    public ThreeStackStatic(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
        this.sizes = new int[number];
        this.stacks = (T[]) new Object[capacity * number];
    }

    public void push(int stack, T value) {
        if (stack >= number || stack < 0)
            throw new IllegalArgumentException("stack should be 0 to " + number);

        if (sizes[stack] >= capacity)
            throw new IllegalStateException(String.format("stack %d is full ", stack));

        stacks[stack * capacity + (sizes[stack]++)] = value;
    }

    public T pop(int stack) {
        if (stack >= number || stack < 0)
            throw new IllegalArgumentException("stack should be 0 to " + number);

        if (sizes[stack] <= 0)
            throw new IllegalStateException(String.format("stack %d is empty ", stack));

        return stacks[stack * capacity + (--sizes[stack])];
    }
}

class ThreeStackDynamic {

}