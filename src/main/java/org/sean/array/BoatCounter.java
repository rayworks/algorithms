package org.sean.array;

import java.util.Arrays;

/***
 * 881. Boats to Save People
 */
public class BoatCounter {
    public int numRescueBoats(int[] people, int limit) {
        // 2 pointers
        Arrays.sort(people);

        int len = people.length;
        int left = 0, right = len - 1;
        int cnt = 0;

        while (left < right) {
            int l = people[left];
            int r = people[right];

            if (r >= limit) {
                right--;
                cnt++;
            } else {
                if (l + r <= limit) {
                    right--;
                    left++;
                    cnt++;
                } else { // >
                    right--;
                    cnt++;
                }
            }
        }
        if (left == right)
            cnt++;

        return cnt;
    }
}
