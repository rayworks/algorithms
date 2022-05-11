package org.sean.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 216. Combination Sum III
 */
public class CombinationSum3 {
    List<Set<List<Integer>>> caches;

    // k numbers that add up to a number n
    public List<List<Integer>> combinationSum30(int k, int n) {
        int[] candidates = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int maxSum = 0;
        for (int i : candidates) {
            maxSum += i;
        }
        if (n > maxSum || n < 1) {
            return Collections.emptyList();
        }

        caches = new ArrayList<>(n + 1);
        caches.add(Collections.emptySet()); // 0

        int length = candidates.length;
        for (int i = 1; i <= n; i++) {
            Set<List<Integer>> listResult = new HashSet<>();
            for (int j = 0; j < length; j++) {
                int w = candidates[j];
                if (i == w) {
                    listResult.add(Arrays.asList(w));
                } else if (i > w) {
                    int diff = i - w;
                    reduceSet(listResult, w, diff);
                } else {
                    break;
                }
            }
            caches.add(listResult);
        }
        Set<List<Integer>> result = caches.get(n);
        return result.stream().filter(integers -> integers.size() == k).collect(Collectors.toList());
    }

    private void reduceSet(Set<List<Integer>> dpOut, int candidate, int rest) {
        if (rest > 0) { // skip <= 0
            Set<List<Integer>> sets = caches.get(rest);
            if (sets != null) {
                for (List<Integer> set : sets) {
                    // without duplicate elem
                    Set<Integer> existedSet = new HashSet<>(set);
                    if (existedSet.contains(candidate)) {
                        continue;
                    }

                    List<Integer> matchedOut = new ArrayList<>(set);
                    matchedOut.add(candidate);

                    // !!n*lg(n)
                    Collections.sort(matchedOut);

                    dpOut.add(matchedOut);
                }
            }
        }
    }

    // region DFS
    private int[] nums;
    boolean[] used;
    List<List<Integer>> out = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        nums = new int[9];
        used = new boolean[nums.length + 1]; // [0-9]
        for (int i = 0; i < 9; i++) {
            nums[i] = i + 1;
        }

        int sum = Arrays.stream(nums).sum();
        if (n > sum || n < 1)
            return Collections.emptyList();

        search(nums, 0, n, k, new ArrayList<>());

        return out;
    }


    private void search(int[] nums, int pos, int targetSum, int k, List<Integer> curr) {
        if (0 == targetSum && curr.size() == k) {
            out.add(new ArrayList<>(curr));
            return;
        }
        if (targetSum < 0)
            return;

        if (pos >= nums.length || curr.size() > k)
            return;

        for (int i = pos; i < nums.length; i++) {
            int elem = nums[i];
            if (used[elem])
                continue;

            curr.add(elem);
            used[elem] = true;

            search(nums, i + 1, targetSum - elem, k, curr);

            curr.remove(curr.size() - 1);
            used[elem] = false;
        }
    }
    // endregion
}
