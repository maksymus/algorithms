package org.interviewelements.sort;

public class InsertionSort<T extends Comparable<T>> extends Sort<T> {
    public void sort(T objs[]) {
        for (int i = 0; i < objs.length; i++) {
            T cur = objs[i];
            for (int j = i - 1; j >= 0 && cur.compareTo(objs[j]) < 0; j--) {
                objs[j + 1] = objs[j];
                objs[j] = cur;
            }
        }
    }
}
