package org.sean.sorting;

public class MergeSort {
    private void swap(int[] a, int m, int n) {
        int tmp = a[m];
        a[m] = a[n];
        a[n] = tmp;
    }

    private int[] tmp = null;

    public void sort(int[] a, int left, int right) {

        if (tmp == null) {
            tmp = new int[a.length];
        }

        if (right <= left) {
            return;
        } else if (right - left == 1) {
            if (a[left] > a[right]) {
                swap(a, left, right);
            }
        } else {

            int len = right + left;
            int mid = len / 2;

            sort(a, left, mid - 1);
            sort(a, mid, right);

            // merge 2 sorted sub-arrays into one array
            int i = left;
            int j = mid;
            int k = 0;
            while (i < mid && j <= right) {
                if (a[i] <= a[j]) {
                    tmp[k] = a[i];
                    ++i;
                } else {
                    tmp[k] = a[j];
                    ++j;
                }
                ++k;
            }

            // append the remaining elements if any
            while (i < mid) tmp[k++] = a[i++];

            while (j <= right) tmp[k++] = a[j++];

            // copy back
            k = 0;
            for (int l = left; l <= right; l++) {
                a[l] = tmp[k++];
            }
        }
    }

    public void print(int[] a) {
        for (int i : a) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println("\n");
    }

    // ------------------------------- CLRS -------------------------------
    void doSort(int[] a, int left, int mid, int right) {
        System.out.println(String.format(">>>left: %d | mid : %d | right : %d", left, mid, right));

        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (int i = 0; i < n1; i++) {
            L[i] = a[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = a[mid + 1 + j];
        }
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;

        int i, j;
        i = j = 0;
        for (int k = left; k <= right; k++) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                ++i;
            } else {
                a[k] = R[j];
                ++j;
            }
        }
    }

    public void mergesort(int[] a, int left, int right) {
        if (left < right) {
            int q = (left + right) / 2;
            mergesort(a, left, q);
            mergesort(a, q + 1, right);

            doSort(a, left, q, right);
        }
    }
    // ------------------------------- CLRS -------------------------------

    public static void main(String[] args) {
        int[] array = new int[] {3, 6, 1, 7, 9, 0, 5, 8};
        MergeSort sort = new MergeSort();
        sort.print(array);

        System.out.println("===============================");
        sort.sort(array, 0, array.length - 1);

        sort.print(array);
    }
}
