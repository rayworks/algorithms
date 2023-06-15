package org.sean.search;

/***
 * 1802. Maximum Value at a Given Index in a Bounded Array
 */
public class BoundedArray {
    public int maxValue(int n, int index, int maxSum) {
        if (n == 1)
            return maxSum;

        // 1, (1, 1 ?) 2, x-1, x, x-1 ... 1
        int left = 1;
        int right = maxSum / 2 + 1;
        int max = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            //System.out.println(">>> try value : " + mid);
            if (isFit(index, n, mid, maxSum)) { // <=
                left = mid + 1;
                max = Math.max(max, mid);
                //System.out.println(">>> fit : " + mid);
            } else {
                right = mid - 1;
            }
        }

        // System.out.println("==========================");

        return max;
    }

    private boolean isFit(int index, int n, int target, int maxSum) {
        long sum = 0L;
        // 1,2, 3, 4
        // 2 3 2 1 1 1
        int start = target - index;
        int preLen = index + 1;
        if (start > 0) {
            sum += (long) (target + start) * preLen / 2;
        } else {
            sum += (long) (target + 1) * target / 2;
            sum += preLen - target; // padding 1s for the rest
        }

        int end = target - (n - 1 - index);
        int postLen = n - index; // + target
        if (end > 0) {
            sum += (long) (end + target) * postLen / 2;
        } else {
            sum += (long) (1 + target) * target / 2;
            sum += postLen - target;
        }

        sum -= target;

        return sum <= maxSum;
    }
}
