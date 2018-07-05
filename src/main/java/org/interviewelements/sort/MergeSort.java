package org.interviewelements.sort;

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] objs) {

        if (objs.length < 7) {
            insertionSort(objs);
            return;
        }

        int mid = objs.length / 2;

        T[] left = Arrays.copyOfRange(objs, 0, mid);
        T[] right = Arrays.copyOfRange(objs, mid, objs.length);

        sort(left);
        sort(right);

        merge(objs, left, right);
    }

    private void insertionSort(T[] objs) {
        for (int i = 0; i < objs.length; i++) {
            for (int j = i; j < objs.length; j++) {
                if (((Comparable<T>) objs[i]).compareTo(objs[j]) > 0) {
                    swap(objs, i, j);
                }
            }
        }
    }

    private void merge(T[] objs, T[] a, T[] b) {
        int i = 0, j = 0;

        for (int k = 0; k < a.length + b.length; k++) {

            if (i >= a.length) {
                System.arraycopy(b, j, objs, k, b.length - j);
                break;
            }

            if (j >= b.length) {
                System.arraycopy(a, i, objs, k, a.length - i);
                break;
            }

            objs[k] = ((Comparable<T>) a[i]).compareTo(b[j]) < 0 ? a[i++] : b[j++];
        }
    }
}
