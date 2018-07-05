package org.interviewelements.sort;

public class ShellSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] objs) {
        int inner, outer;

        T tmp;

        int h = 1;

        while (h <= objs.length / 3) {
            h = h * 3 + 1;
        }

        while (h > 0) {
            for (outer = h; outer < objs.length; outer++) {
                tmp = objs[outer];
                inner = outer;

                while (inner > h - 1 && objs[inner - h].compareTo(tmp) >= 0) {
                    objs[inner] = objs[inner - h];
                    inner -= h;
                }
            }

            h = (h - 1) / 3;
        }
    }
}
