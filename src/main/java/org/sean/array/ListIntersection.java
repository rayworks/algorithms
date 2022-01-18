package org.sean.array;

import java.util.LinkedList;

/***
 * 986. Interval List Intersections
 */
public class ListIntersection {
    int lastLocatedPos = 0;
    LinkedList<int[]> ret = new LinkedList<>();

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0)
            return new int[0][0];

        int lenA = A.length;
        int lenB = B.length;

        int i = 0;

        int[] pair;
        while (i < lenB) {
            pair = B[i];
            int pos = findRangeInterval(pair, lastLocatedPos, A);
            if (pos >= 0) {
                boolean fullyMerged = mergeIntervals(B, i, A, pos);
                if (fullyMerged) {
                    ++i;
                } else {
                    // still have partial segment
                    continue;
                }
            } else { // not found
                ++i;
            }
        }
        if (!ret.isEmpty()) {
            int[][] arraies = new int[ret.size()][2];
            int index = 0;
            for (int[] arr : ret) {
                arraies[index++] = arr;
            }

            return arraies;
        }

        return new int[0][0];
    }

    int findRangeInterval(int[] elem, int fromPos, int[][] targets) {
        int length = targets.length;
        for (int i = fromPos; i < length; i++) {
            int[] ints = targets[i];
            if (elem[0] >= ints[0] && elem[0] <= ints[1]
                    || (elem[1] >= ints[0] && elem[1] <= ints[1])
                    || ints[0] >= elem[0] && ints[0] <= elem[1]
                    || ints[1] >= elem[0] && ints[1] <= elem[1]
            ) {
                return i;
            }
        }
        return -1;
    }


    /***
     * @return true if merged completely; otherwise false.
     */
    private boolean mergeIntervals(int[][] b, int i, int[][] a, int pos) {
        int[] arr1 = b[i];
        int[] arr2 = a[pos];
        int s1 = arr1[0], e1 = arr1[1];
        int s2 = arr2[0], e2 = arr2[1];
        int s, e;
        s = s1 <= s2 ? s2 : s1;
        e = e1 <= e2 ? e1 : e2;

        ret.add(new int[]{s, e});
        //System.out.println(String.format(">>> %d:%d added ", s, e));

        if (e2 == e && e1 == e2) {
            lastLocatedPos = pos + 1;
        } else if (e2 - e > 0) {
            arr2[0] = e + 1;
            a[pos] = arr2;

        } else if (e1 - e > 0) {
            arr1[0] = e + 1;
            b[i] = arr1;

            lastLocatedPos = pos + 1;

            return false;
        }

        return true;

    }
}
