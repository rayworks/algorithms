package org.sean.array;

import java.util.ArrayDeque;
import java.util.Deque;

/***
 * 45. Jump Game II
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        int start = 0, end = 0;
        int len = nums.length;
        int maxSoFar = 0;

        int step = 0;

        while (end < len - 1) {
            for (int i = start; i <= end; i++) {
                maxSoFar = Math.max(maxSoFar, i + nums[i]);
            }
            start = end + 1;
            end = maxSoFar;

            step++;
        }
        return step;
    }

    // region BFS
    public int jump0(int[] nums) {
        int len = nums.length;
        byte[] visited = new byte[len];

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = 1;

        int level = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int index = queue.pop();

                if (index == len - 1) {
                    return level;
                }

                int step = nums[index];
                for (int k = 1; k <= step; k++) {
                    int e = Math.min(index + k, len - 1);

                    if (visited[e] == 0) {
                        visited[e] = 1;
                        queue.add(e);
                    }
                }

            }
            level++;
        }
        return 0; // never be here
    }
    // endregion
}
