package org.sean.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 216. Combination Sum III
 */
public class CombinationSum3 {
    List<Set<List<Integer>>> caches;

    // k numbers that add up to a number n
    public List<List<Integer>> combinationSum3(int k, int n) {
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

    public static void main(String[] args) {
        CombinationSum3 sum3 = new CombinationSum3();
        List<List<Integer>> lists = sum3.combinationSum3(3, 7);
        System.out.println(Arrays.toString(lists.toArray()));
    }
}
