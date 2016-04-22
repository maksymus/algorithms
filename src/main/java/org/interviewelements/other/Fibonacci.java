package org.interviewelements.other;

import java.math.BigInteger;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fib(0));
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(10));
    }

    public static BigInteger fib(int n) {
        if (n == 0)
            return BigInteger.ZERO;

        if (n <= 2)
            return BigInteger.ONE;

        BigInteger n2 = BigInteger.ONE;
        BigInteger n1 = BigInteger.ONE;

        for (int i = 3; i <= n; i++) {
            BigInteger tmp = n1;
            n1 = n2.add(n1);
            n2 = tmp;
        }

        return n1;
    }
}
