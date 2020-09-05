package org.sean.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/***
 * 436. Find Right Interval
 */
public class RightIntervalFinder {
    Map<Integer, Integer> startValIndexMap = new HashMap<>();

    public int[] findRightInterval(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return new int[] {-1};
        }

        int len = intervals.length;
        int i = 0;
        for (int[] pair : intervals) {
            startValIndexMap.put(pair[0], i++);
        }

        Map<Integer, Integer> outPosMinIndexes = new HashMap<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        for (int l = 0; l < len - 1; l++) {
            int preStart = intervals[l][0];
            int preEnd = intervals[l][1];

            int pos = binSearch(intervals, l + 1, preEnd);
            outPosMinIndexes.put(startValIndexMap.get(preStart), pos);
        }
        // add one for the last sorted elem
        outPosMinIndexes.put(startValIndexMap.get(intervals[len - 1][0]), -1);

        int[] outIndexes = new int[len];
        for (int l = 0; l < len; l++) {
            outIndexes[l] = outPosMinIndexes.get(l);
        }

        return outIndexes;
    }

    // return the nearest pos the target value if found ; otherwise -1
    int binSearch(int[][] intervals, int startPos, int target) {
        int len = intervals.length;
        if (target <= intervals[startPos][0]) return startValIndexMap.get(intervals[startPos][0]);
        if (target > intervals[len - 1][0]) return -1;

        // start from index 1
        int beg = startPos;
        int end = len - 1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            int midStart = intervals[mid][0];
            // if (mid > 0)
            {
                // trending
                if(mid == len - 1) {
                    if (target > intervals[mid - 1][0] && target <= midStart) {
                        return startValIndexMap.get(midStart);
                    }
                    break;
                }
                if (target >= intervals[mid + 1][0]) {
                    beg = mid + 1;
                } else if (target <= intervals[mid - 1][0]) {
                    end = mid - 1;
                } else {
                    if (target > intervals[mid - 1][0] && target <= midStart) {
                        return startValIndexMap.get(midStart);
                    }
                    if (target > midStart && target <= intervals[mid + 1][0]) {
                        return startValIndexMap.get(intervals[mid + 1][0]);
                    }
                }
            }
        }
        return -1;
    }
}
