package org.sean.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/***
 * 78. Subsets
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        lookup(nums, 0, new LinkedList<>());

        for (List<Integer> lt: allSets){
            System.out.println(Arrays.toString(lt.stream().mapToInt(value -> value).toArray()));
        }
        return allSets;
    }

    List<List<Integer>> allSets = new ArrayList<>();

    void lookup(int[] nums, int pos, LinkedList<Integer> current) {
        allSets.add(new ArrayList<>(current));

        int len = nums.length;
        if (pos >= len) {
            return;
        }

        for (int i = pos; i < len; i++) {
            current.add(nums[i]);
            lookup(nums, i + 1, current);
            current.removeLast();
        }
    }
}
