package org.sean.backtracking;

import java.util.*;

/***
 * 39. Combination Sum
 */
public class CombinationSum {
    List<Set<List<Integer>>> caches = new ArrayList<>(); // dp[]

    // Solution 1 :
    public List<List<Integer>> combinationSum0(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target == 0)
            return Collections.emptyList();

        caches = new ArrayList<>(target + 1);
        caches.add(Collections.emptySet());

        int length = candidates.length;
        for (int i = 1; i <= target; i++) {
            Set<List<Integer>> dp = new HashSet<>();

            for (int j = 0; j < length; j++) {
                int candidate = candidates[j];

                if (i == candidate) {
                    dp.add(new ArrayList<>(Arrays.asList(i)));
                } else if (i > candidate) { // >
                    reduceSet(dp, candidate, i - candidate);
                }
            }

            caches.add(dp);
        }

        Set<List<Integer>> result = caches.get(target);
        return new LinkedList<>(result);
    }

    private void reduceSet(Set<List<Integer>> dpOut, int candidate, int rest) {
        if (rest > 0) { // skip <= 0
            Set<List<Integer>> sets = caches.get(rest);
            if (sets != null) {
                for (List<Integer> set : sets) {
                    List<Integer> matchedOut = new ArrayList<>(set);
                    matchedOut.add(candidate);

                    // !!n*lg(n)
                    Collections.sort(matchedOut);

                    dpOut.add(matchedOut);
                }
            }
        }
    }


    // Solution 2 : DFS
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        search(candidates, target, 0, new LinkedList<>());
        return out;
    }

    private List<List<Integer>> out = new ArrayList<>();

    private void search(int[] candidates, int target, int pos, LinkedList<Integer> path) {

        if (target < 0)
            return;

        if (target == 0) {
            out.add(new ArrayList<>(path));
            return;
        }

        for (int i = pos; i < candidates.length; i++) {
            path.add(candidates[i]);
            search(candidates, target - candidates[i], i, path);
            path.removeLast();
        }
    }
}
