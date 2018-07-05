package org.sedgewick.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/*
 * Intersection of two sets. Given two arrays a[] and b[], each containing N distinct 2D points in the plane, design a
 * subquadratic algorithm to count the number of points that are contained both in array a[] and array b[].
 */
public class SortsIntersection {

    private static class Point implements Comparable<Point> {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point that) {
            int xcomp = Integer.compare(this.x, that.x);

            if (xcomp != 0)
                return xcomp;

            return Integer.compare(this.y, that.y);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    private Point a[];
    private Point b[];

    public SortsIntersection(int n) {
        a = new Point[n];
        b = new Point[n];

        IntStream.range(0, n).forEach(i -> {
            Random rand = new Random();
            a[i] = new Point(rand.nextInt(10), rand.nextInt(10));
            b[i] = new Point(rand.nextInt(10), rand.nextInt(10));
        });

        Arrays.sort(a);
        Arrays.sort(b);
    }

    public void print() {
        System.out.println("as: " + Arrays.toString(a));
        System.out.println("bs: " + Arrays.toString(b));
    }

    public List<Point> intersection() {
        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0, j = 0; i < a.length && j < b.length;) {
            int result = a[i].compareTo(b[j]);

            if (result == 0) {
                points.add(a[i]);
                i++; j++;
            } else if (result < 0) {
                i++;
            } else {
                j++;
            }
        }

        return points;
    }

    public static void main(String[] args) {
        SortsIntersection sortsIntersection = new SortsIntersection(20);
        sortsIntersection.print();
        System.out.println(Arrays.toString(sortsIntersection.intersection().toArray()));

    }
}
