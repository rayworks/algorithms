package org.sean.array;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sean on 2/24/17.
 * <p>
 * 448. Find All Numbers Disappeared in an Array
 */
public class ElementFinder {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums.length <= 1)
            return Collections.EMPTY_LIST;

        LinkedList<Integer> result = new LinkedList<Integer>();
        int cnt = nums.length;

        // used to clear the most left bit if assigned before
        int mask = ~(1 << 31);

        // 4, 3, 2, 7, 8, 2, 3, 1
        for (int i = 0; i < cnt; i++) {
            int pos = (nums[i] & mask) - 1;
            nums[pos] = (1 << 31) | nums[pos];// mark the value
        }

        for (int k = 1; k <= cnt; k++) {
            int v = nums[k - 1];
            byte s = (byte) (v >> 31);
            if ((s & 0x01) == 0) {
                result.add(k);
            }
        }

        return result;
    }
}
