package org.interviewelements;

import java.util.HashMap;
import java.util.Map;

public class Primitive {
    public static void main(String[] args) {
        swap(123, -87);
        reverse(1);
        reverse(-9223372036854775808l);
        stringToInt("123");
        stringToInt("-35");
        convertBase(10, "123", 10);
        convertBase(2, "1000101001110011", 8);
        convertBase(2, "1000101001110011", 16);
    }

    /* swap ints w/o temp variable */
    private static void swap(int a, int b) {
        System.out.println("swap w/o tmp var:");
        System.out.printf("\tbefore swap: a = %d, b = %d\n", a, b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.printf("\tafter swap: a = %d, b = %d\n", a, b);
    }

    /* reverse bits in 64 bit int */
    private static long reverse(long a) {
        long reversed = 0;

        for (long i = 0; i < 64; i++) {
            reversed |= ((a >>> i) & 1) << (63 - i);
        }

        System.out.printf("reverse for %d (%s) is: %d (%s)\n", a, Long.toBinaryString(a),
                reversed, Long.toBinaryString(reversed));

        return reversed;
    }

    /* string to int */
    private static int stringToInt(String str) {
        char[] chars = str.toCharArray();

        boolean negative = (chars[0] == '-');

        int res = 0;
        for (int i = str.length() - 1; i >= (negative ? 1 : 0); i--) {
            res += Integer.parseInt("" + chars[i]) * Math.pow(10, str.length() - 1 - i);
        }

        System.out.printf("stringToInt: %s -> %d\n", str, negative ? res * -1 : res);

        return negative ? res * -1 : res;
    }

    private static String convertBase(int fromBase, String intString, int toBase) {
        Map<Character, Integer> charToInt = new HashMap() {{
            put('0', 0); put('1', 1); put('2', 2); put('3', 3); put('4', 4); put('5', 5); put('6', 6);
            put('7', 7); put('8', 8); put('9', 9); put('A', 10); put('B', 11); put('C', 12); put('D', 13);
            put('E', 14); put('F', 15);
        }};

        Map<Integer, Character> intToChar = new HashMap();
        for (Character character : charToInt.keySet()) {
            intToChar.put(charToInt.get(character), character);
        }

        int base10 = 0;
        for (int i = intString.length() - 1; i >= 0; i--) {
            char key = intString.toCharArray()[i];
            Integer integer = charToInt.get(key);

            if (integer == null || integer >= fromBase)
                throw new IllegalArgumentException("Wrong input number " + String.valueOf(key));

            base10 += integer * Math.pow(fromBase, intString.length() - 1 - i);
        }

        int base10Log = base10;

        String toBaseString = "";
        while (base10 > 0) {
            char ch = intToChar.get(base10 % toBase);
            base10 /= toBase;
            toBaseString = ch + toBaseString;
        }

        System.out.printf("from base%d [%s], base10 [%d], to base%d [%s]\n", fromBase, intString, base10Log, toBase,
                toBaseString);

        return toBaseString;
    }
}

