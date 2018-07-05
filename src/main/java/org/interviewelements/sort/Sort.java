package org.interviewelements.sort;

public abstract class Sort<T extends Comparable<T>> {
    public abstract void sort(T objs[]);

    protected void swap(T[] objs, int i, int j) {
        if (i != j) {
            T tmp = objs[i];
            objs[i] = objs[j];
            objs[j] = tmp;
        }
    }
}
