package org.sedgewick.sort;

/*
 * Dutch national flag. Given an array of N buckets, each containing a red, white, or blue pebble, sort them by color.
 * The allowed operations are:
 *  <li>swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 *  <li>color(i): color of pebble in bucket i.
 * The performance requirements are as follows:
 *  <li>At most N calls to color().
 *  <li>At most N calls to swap().
 *  <li>Constant extra space.
 */
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DutchFlag {
    public enum Color { RED, WHITE, BLUE }

    public static void sort(Color colors[]) {
        int low = 0, mid = 0, high = colors.length - 1;

        while (mid <= high) {
            switch (colors[mid]) {
                case RED:
                    swap(colors, low++, mid++);
                    break;
                case WHITE:
                    mid++;
                    break;
                case BLUE:
                    swap(colors, mid, high--);
                    break;
            }
        }
    }

    private static void swap(Color colors[], int i, int j) {
        Color tmp = colors[i];
        colors[i] = colors[j];
        colors[j] = tmp;
    }

    public static void main(String[] args) {
        Supplier<Color> supplier = () -> {
            switch (new Random().nextInt(3)) {
                case 0: return Color.RED;
                case 1: return Color.WHITE;
                case 2: return Color.BLUE;
            }
            return null;
        };

        Color[] colors = Stream.generate(supplier).limit(10).collect(Collectors.toList()).toArray(new Color[] {});
        DutchFlag.sort(colors);

        Arrays.stream(colors).forEach(color -> System.out.print(color + " "));
    }
}
