package org.geeks.math;

public class Pow {
    /**
     * Given an integer, write a function to determine if it is a power of n.
     */
    public boolean isPowerN(int number, int n) {
        if (number < 0)
            return false;

        if (n == 2)
            return isPowerTwo(number);

        int res = (int) (Math.log(number) / Math.log(n));
        double pow = Math.pow(res, n);

        return pow == number;
    }

    /**
     * Given an integer, write a function to determine if it is a power of 2.
     */
    public boolean isPowerTwo(int number) {
        if (number < 0)
            return false;

        return (number & (number - 1)) == 0;
    }

    public int pow1(int num, int n) {
        if (n == 0)
            return 1;

        int result = num;

        for (int i = 1; i < n; i++) {
            result = result * num;
        }

        return result;
    }

    public int pow2(int num, int n) {
        int result = 1;

        while (n > 0) {
            if (n % 2 != 0) {
                result = result * num;
            }

            num = num * num;
            n = n >> 1;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Pow().isPowerN(15, 2));
        System.out.println(new Pow().isPowerTwo(15));
        System.out.println(new Pow().isPowerTwo(4));

        for (int i = 0; i < 10; i++) {
            System.out.println(new Pow().pow2(2, i));
        }
    }
}
