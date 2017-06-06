package org.geeks;

public class NumberTheory {

    /**
     * Modular Exponentiation (Power in Modular Arithmetic) Given three numbers
     * x, y and p, compute (x^y) % p.
     */
    public static int modularExponentiation(int x, int y, int p) {
        int res = 1; // Initialize result

        x = x % p; // Update x if it is more than or equal to p

        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) == 1)
                res = (res * x) % p;

            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(modularExponentiation(3, 2, 5));
    }
}
