package org.sean.sorting;

public class QSort {

    public void print(int[] a) {
        for (int i : a) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println("\n");
    }

    private void swap(int[] a, int m, int n) {
        int tmp = a[m];
        a[m] = a[n];
        a[n] = tmp;
    }

    private int partition(int[] a, int lhv, int rhv) {
        int l = lhv;
        int r = rhv;

        int p = a[l];
        ++l;
        while (l < r) {
            for (; l < rhv; l++) { // rhv, lhv, note the original boundary when searching
                if (a[l] > p)
                    break;
            }

            for (; r > lhv; r--) {
                if (a[r] < p) {
                    break;
                }
            }

            if (l < r) {
                swap(a, l, r);
            }
        }

        System.out.print("- ");
        swap(a, lhv, r);

        print(a);

        return r;
    }

    public void sort(int[] a, int i, int j) {
        if (i >= j) return;

        int pos = partition(a, i, j);
        sort(a, i, pos - 1);
        sort(a, pos + 1, j);
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 6, 1, 7, 9, 0, 5};
        QSort qSort = new QSort();
        qSort.print(array);

        System.out.println("===============================");

        qSort.sort(array, 0, array.length - 1);
        qSort.print(array);
    }
}
