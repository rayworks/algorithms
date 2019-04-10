package org.sean.array;

import java.util.HashSet;
import java.util.Set;

// 128. Longest Consecutive Sequence
public class ConsecutiveSequenceFinder {
    private int maxLen = 0;
    private Set<Integer> set = new HashSet<>();

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        set.clear();
        int size = nums.length;
        for (int n : nums) {
            set.add(n);
        }

        int cnt = 0;
        int elem;
        int preKey;
        int postKey;

        for (int i = 0; i < size; i++) {
            cnt = 0;

            elem = nums[i];
            if (set.contains(elem)) {
                ++cnt;
                set.remove(elem);

                preKey = elem - 1;
                postKey = elem + 1;
                while (set.contains(preKey)) {
                    set.remove(preKey);

                    preKey--;
                    ++cnt;
                }

                while (set.contains(postKey)) {
                    set.remove(postKey);
                    postKey++;

                    ++cnt;
                }

                if (cnt > maxLen) {
                    maxLen = cnt;
                }
            }
        }

        return maxLen;
    }
}
