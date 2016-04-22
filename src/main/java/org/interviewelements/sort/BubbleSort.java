package org.interviewelements.sort;

public class BubbleSort<T extends Comparable<T>> extends Sort<T> {

    public void sort(T objs[]) {
        sort(objs, objs.length);
    }

    private void sort(T objs[], int to) {
        if (to == 0) {
            return;
        }

        for (int i = 0; i < to - 1; i++) {
            if (objs[i].compareTo(objs[i + 1]) > 0) {
                swap(objs, i, i + 1);
            }
        }

        sort(objs, to - 1);
    }
}
