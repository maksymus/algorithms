package org.interviewelements;

public class Strings {
    /**
     * Implement run-length encoding and decoding functions (letters of alphabet only).
     */
    public static String runLengthEncoding(String input) {
        if (input == null || input.length() == 0)
            return "";

        char[] chars = input.toCharArray();

        StringBuilder sb = new StringBuilder();

        char current = chars[0];
        int count = 0;
        for (char ch : chars) {
            if (ch == current) {
                count++;
            } else {
                sb.append(count).append(current);
                count = 1;
                current = ch;
            }
        }

        sb.append(count).append(current);
        return sb.toString();
    }

    /**
     * Given two strings s (the "search string") and t (the "text"), find the
     */
    public static int robinKarpSubstring(String text, String search) {
        int searchHash = hash(search, 0, 0, search.length());

        for (int i = 0, textHash = 0; i < text.length() - search.length() + 1; i++) {
            textHash = hash(text, textHash, i, search.length());

            if (textHash == searchHash) {
                int j = 0;
                for (; j < search.length(); j++) {
                    if (text.charAt(i + j) != search.charAt(j)) {
                        break;
                    }
                 }

                if (j == search.length()) {
                    // found match
                    return i;
                }
            }
        }

        return -1;
    }
    private static int hash(String text, int oldHash, int start, int length) {
        // simple non-effective hash function to demonstrate robin-karp
        if (start == 0)
            return text.chars().limit(length).reduce(0, (a, b) -> a + b);

        oldHash -= text.charAt(start - 1);          // remove previous char
        oldHash += text.charAt(start + length - 1); // add next char

        return oldHash;
    }

    /**
     * Reverse string
     */
    public static String reverse(String string) {
        StringBuilder sb = new StringBuilder(string);
        for (int i = 0; i < string.length() / 2 ; i++) {
            char left = sb.charAt(i);
            char right = sb.charAt(string.length() - i - 1);

            sb.setCharAt(i, right);
            sb.setCharAt(string.length() - i - 1, left);
        }

        return sb.toString();
    }
}
