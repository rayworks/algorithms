package org.sean.dynamicpro;

import java.util.*;

// 300. Longest Increasing Subsequence
public class Lis {
    public int lengthOfLIS0(int[] nums) { // O(N^2)
        if (nums == null) return 0;

        int size = nums.length;
        if (size <= 1) return size;

        int[] dp = new int[size];
        dp[0] = 1;

        int maxLen = 0;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public int lengthOfLIS1(int[] nums) { // O(N*lgN)

        Iterator<Integer> iterator;
        Set<Integer> set = new TreeSet<>();

        for (int num : nums) {
            if (set.contains(num)) continue;

            set.add(num);
            iterator = set.iterator();
            while (iterator.hasNext()) { // lg(N)
                Integer elem = iterator.next();
                if (elem == num) {
                    break;
                }
            }

            // remove the larger one after current new added element
            if (iterator.hasNext()) {
                Integer next = iterator.next();
                iterator.remove();
            }
        }

        return set.size();
    }

    public int lengthOfLIS(int[] nums) { // O(N*lgN)
        if (nums == null || nums.length <= 1)
            return nums == null ? 0 : nums.length;

        int len = nums.length;

        ArrayList<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);
        for (int i = 1; i < len; i++) {
            int elem = nums[i];

            int pos = binSearch(dp, 0, dp.size() - 1, elem);
            if (pos == -1)
                continue;
            else if (pos >= dp.size())
                dp.add(elem);
            else
                dp.set(pos, elem);
        }

        return dp.size();
    }

    private int binSearch(List<Integer> arr, int low, int high, int val) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr.get(mid) == val) return -1;
            else if (arr.get(mid) > val) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}
