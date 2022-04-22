package org.sean.backtracking;

import java.util.*;

/** * 40. Combination Sum II */
public class CombinationSum2 {
    List<Set<List<Integer>>> caches = new ArrayList<>();

    // The original valid element table
    private int[] table;

    private int[] tmp;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target == 0)
            return Collections.emptyList();

        int len = candidates.length;

        table = new int[target + 1];
        for (int i = 0; i < len; i++) {
            int elem = candidates[i];
            if (elem <= target) {
                table[elem] += 1;
            }
        }

        tmp = new int[target + 1];

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

                    // check for a group of limited elements
                    boolean result = limitReached(matchedOut);
                    if (result) continue;

                    // !!n*lg(n)
                    Collections.sort(matchedOut);

                    dpOut.add(matchedOut);
                }
            }
        }
    }

    private boolean limitReached(List<Integer> matchedOut) {
        Arrays.fill(tmp, 0);

        for (Integer i : matchedOut) {
            tmp[i] += 1;
        }

        int last = tmp.length - 1;
        for (int p = 0; p <= last; p++) {
            if (tmp[p] > table[p]) {
                return true;
            }
        }

        return false;
    }
}
