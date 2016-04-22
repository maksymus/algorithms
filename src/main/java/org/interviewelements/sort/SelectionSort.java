package org.interviewelements.sort;

public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    public void sort(T objs[]) {
        sort(objs, objs.length);
    }

    private void sort(T[] objs, int to) {
        if (to == 0) {
            return;
        }

        int min = objs.length - to;

        for (int i = min; i < objs.length; i++) {
            if (objs[i].compareTo(objs[min]) < 0) {
                min = i;
            }
        }

        swap(objs, objs.length - to, min);

        sort(objs, to - 1);
    }
}
