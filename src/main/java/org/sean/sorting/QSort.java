package org.sean.sorting;

import java.util.Random;

public class QSort {

    private void swap(int[] a, int m, int n) {
        int tmp = a[m];
        a[m] = a[n];
        a[n] = tmp;
    }

    private int partition(int[] a, int lhv, int rhv) {
        // randomizing the pivot
        final int randIndex = new Random().nextInt(rhv - lhv + 1) + lhv;
        swap(a, randIndex, lhv);
        int p = a[lhv];

        int l = lhv + 1;
        int r = rhv;

        while (true) {
            for (; l < r; l++) {
                if (a[l] > p)
                    break;
            }

            for (; l < r; r--) {
                if (a[r] < p) {
                    break;
                }
            }

            if (l >= r) break;

            swap(a, l, r);
        }

        // put pivot at the right position
        int pivotPos = a[l] < a[lhv] ? l : l - 1;
        swap(a, lhv, pivotPos);

        return pivotPos;
    }

    private void doSort(int[] a, int i, int j) {
        if (i >= j) return;

        int pos = partition(a, i, j);
        doSort(a, i, pos - 1);
        doSort(a, pos + 1, j);
    }

    public void sort(int[] a) {
        doSort(a, 0, a.length - 1);
    }
}
