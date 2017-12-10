package org.geeks.math;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public class ExcelSheet {

    public static final int NUM_CHARS = 26;

    /**
     * Given a positive integer, return its corresponding column title as appear in an Excel sheet. For example:
     *
     * 1 -> A
     * 2 -> B
     * 3 -> C
     * ...
     * 26 -> Z
     * 27 -> AA
     * 28 -> AB
     */
    public String title(int number) {
        StringBuilder sb = new StringBuilder();

        while (number > 0) {
            number--;
            int num = number % NUM_CHARS;
            number /= NUM_CHARS;
            sb.append((char) (num + 'A'));

        }

        return sb.reverse().toString();
    }

    /**
     * Given a column title as appear in an Excel sheet, return its corresponding column number. For example:
     *
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     * ...
     * AAA -> 703
     */
    public int number(String title) {
        int result = 0;

        for (int i = 0; i < title.length(); i++) {
            int num = (title.charAt(i) - 'A') + 1;
            result += num * Math.pow(NUM_CHARS, title.length() - i - 1);
        }

        return result;
    }

    public static void main(String[] args) {
        ExcelSheet excelSheet = new ExcelSheet();

        for (int i = 1; i <= 1000; i++) {
            String title = excelSheet.title(i);
            int number = excelSheet.number(title);
            System.out.println(title + ": " + number);
        }
    }
}
