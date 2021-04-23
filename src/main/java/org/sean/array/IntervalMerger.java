package org.sean.array;

import java.util.Arrays;
import java.util.Comparator;

/***
 * 56. Merge Intervals
 */
public class IntervalMerger {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        int i = 0, j = 1;
        int len = intervals.length;

        // sort by the start point N*lgN
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int reducedCnt = 0;
        while (i < j && j < len) {
            int[] merged = doMerge(intervals[i], intervals[j]);
            if (merged == null) {
                i = j;
                j++;
            } else {
                intervals[i][0] = merged[0];
                intervals[i][1] = merged[1];

                // marked as merged
                intervals[j][0] = -1;
                intervals[j][1] = -1;

                ++reducedCnt;

                j++;
            }
        }

        return filterMergedArray(intervals, reducedCnt, len);
    }

    int[] doMerge(int[] line1, int[] line2) {
        int s1 = line1[0];
        int e1 = line1[1];
        int s2 = line2[0];
        int e2 = line2[1];

        if (s2 > e1) {
            return null;
        } else {
            return new int[]{s1, Math.max(e1, e2)};
        }
    }

    private int[][] filterMergedArray(int[][] intervals, int reducedCnt, int len) {
        int[][] out = new int[len - reducedCnt][2];
        int m = 0;
        for (int k = 0; k < len; k++) {
            if (intervals[k][0] >= 0) {
                out[m++] = intervals[k];
            }
        }
        return out;
    }
}
