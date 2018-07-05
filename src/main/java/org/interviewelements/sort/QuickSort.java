package org.interviewelements.sort;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] objs) {
        int i = 0, j = objs.length;

        sort(objs, i, j - 1);
    }

    private void sort(T[] objs, int i, int j) {
        // base case: list is empty
        if (i >= j)
            return;

        // recursion step: make partition and sort recursively
        int m = partition(objs, i, j);
        sort(objs, i, m - 1);
        sort(objs, m + 1, j);
    }

    // partition the array from l+1 to r with pivot a[l]
    private int partition(T[] a, int l, int r) {
        int i = l + 1; // pointer on left side
        int j = r; // pointer on right side
        T p = a[l]; // pivot element

        // move pointer to center or swap if on wrong sides
        while (i <= j) {
            if (a[i].compareTo(p) <= 0)
                i++;
            else if (a[j].compareTo(p) > 0)
                j--;
            else
                swap(a, i, j);
        }
        // swap pivot element between partitions
        swap(a, l, j);

        // return position of pivot element
        return j;
    }
}
