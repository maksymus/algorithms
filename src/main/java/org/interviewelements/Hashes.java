package org.interviewelements;

public class Hashes {

    /**
     * Problem 12.1: Design a hash function that is suitable for words in a dictionary.
     */
    public static int stringHash(String str) {
        int res = 0, prime = 997, modulus = 20;
        for (char c : str.toCharArray()) {
            res = (res * prime + c) % modulus;
        }
        return res;
    }
}
