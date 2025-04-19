package org.sean.array;

import java.util.HashMap;

/***
 * 2537. Count the Number of Good Subarrays
 */
public class GoodSubarrayCounter {
    public long countGood(int[] nums, int k) {
        int len = nums.length;
        int left = 0, right = 0;

        int currPairCnt = 0;
        long res = 0L;
        HashMap<Integer, Integer> map = new HashMap<>();

        while (left < len && right < len) {
            while (right < len) {
                int elem = nums[right];

                if (map.containsKey(elem)) {
                    int cnt = map.get(elem);
                    currPairCnt += cnt;
                    map.put(elem, cnt + 1);
                } else {
                    map.put(elem, 1);
                }
                if (currPairCnt >= k) {
                    int add = len - right;
                    res += add;
                    // System.out.printf("%d matched substring found, range from %d to %d\n", add, left, right);
                    break;
                }

                right++;
            }

            while (currPairCnt >= k && left < right) {
                int leftElem = nums[left];
                int leftCnt = map.getOrDefault(leftElem, 0);

                // relax from left side
                map.put(leftElem, leftCnt - 1);
                if (leftCnt == 1)
                    map.remove(leftElem);

                currPairCnt -= leftCnt - 1; // the num of increased pairs
                left++;

                if (currPairCnt >= k) {
                    // System.out.printf("matched substring found, range from %d to %d\n", left, right);
                    // [left', right...]
                    res += len - right;
                }
            }

            // advance the right pointer
            right++;
        }

        return res;
    }
}
