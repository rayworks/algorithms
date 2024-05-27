package org.sean.array;

/***
 * 1608. Special Array With X Elements Greater Than or Equal X
 */
public class NumInSpecialArray {
    // #region BinSearch, time complexity O(N*lgN)
    public int specialArray0(int[] nums) {
        // 0 <= nums[i] <= 1000
        // 1 <= nums.length <= 100

        // left : 0
        // right : 1000
        // binSearch
        int left = 0;
        int right = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = numOfNonSmaller(nums, mid);
            if (cnt == mid) {
                return mid;
            } else if (cnt < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
    // #endregion

    private int numOfNonSmaller(int[] nums, int x) {
        int cnt = 0;
        for (int i : nums) {
            if (i >= x)
                cnt++;
        }
        return cnt;
    }


    // #region Counting sort + prefix sum, time complexity O(N)
    public int specialArray(int[] nums) {
        int len = nums.length;
        int[] counters = new int[len + 1];
        for (int i = 0; i < len; i++) {
            if(nums[i] >= len)
                counters[len] += 1;
            else
                counters[nums[i]] += 1;

        }
        int count = 0;
        for (int i = len; i >= 1; i--) {
            count += counters[i];
            if(i == count)
                return i;
        }
        return -1;
    }
    // #endregion
}
