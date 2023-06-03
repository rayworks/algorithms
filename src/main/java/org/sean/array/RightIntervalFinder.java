package org.sean.array;

import java.util.*;

/***
 * 436. Find Right Interval
 */
public class RightIntervalFinder {
    // region Solution2
    static class Elem implements Comparable<Elem>{
        public Elem(int[] pair, int pos) {
            this.pair = pair;
            this.pos = pos;
        }

        int[] pair;
        int pos;

        @Override
        public int compareTo( Elem o) {
            return Integer.compare(this.pair[0], o.pair[0]);
        }
    }

    public int[] findRightInterval(int[][] intervals) { // O(N*lgN)
        List<Elem> elems = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            elems.add(new Elem(intervals[i], i));
        }

        Collections.sort(elems);

        int pos = 0;
        int[] res = new int[intervals.length];
        for (int[] interval: intervals) {
            res[pos++] = locateRightPos(interval, elems);
        }

        return res;
    }

    // low_bound()
    private int locateRightPos(int[] pair, List<Elem> intervals) {
        // end <= start'
        int left = 0;
        int right = intervals.size() - 1;

        int target = pair[1];
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int val = intervals.get(mid).pair[0];

            if(val == target) {
                return intervals.get(mid).pos;
            } else if (val < target) {
                left = mid + 1;
            } else { // >
                right = mid - 1;
            }
        }

        if (left >= intervals.size())
            return -1;

        if (right < 0)
            return -1;

        return intervals.get(left).pos;
    }
    // endregion
    Map<Integer, Integer> startValIndexMap = new HashMap<>();

    public int[] findRightInterval0(int[][] intervals) {
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
