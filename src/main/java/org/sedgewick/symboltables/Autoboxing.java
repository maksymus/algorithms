package org.sedgewick.symboltables;

import static java.lang.Double.valueOf;

/**
 * Java autoboxing and equals(). Consider two double values a and b and their corresponding Double values x and y.
 *  Find values such that (a == b) is true but x.equals(y) is false.
 *  Find values such that (a == b) is false but x.equals(y) is true.
 */
public class Autoboxing {
    public static void main(String[] args) {
        System.out.printf("(a == b): %b, x.equals(y): %b", 0.0 == -0.0, valueOf(0.0).equals(valueOf(-0.0)));
    }
}
