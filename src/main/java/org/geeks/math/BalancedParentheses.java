package org.geeks.math;

/**
 * Input : n=1
 * Output: {}

 * Input : n=2
 * Output:
 * {}{}
 * {{}}
 */
public class BalancedParentheses {
    public void print(int n) {
        print("", n, n);
    }

    private void print(String prefix, int open, int close) {
        if (close < open)
            return;

        if (open == 0 && close == 0) {
            System.out.println(prefix);
            return;
        }

        if (open > 0) {
            print(prefix + "{", open - 1, close);
        }

        if (close > 0) {
            print(prefix + "}", open, close - 1);
        }
    }

    public static void main(String[] args) {
        new BalancedParentheses().print(3);
    }
}
