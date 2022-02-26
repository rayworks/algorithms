package org.sean.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * 77. Combinations
 */
public class Combinations {
    private List<List<Integer>> outLists = new ArrayList<>();

    private void comb(int n, int k, int i, LinkedList<Integer> list) {
        if (list.size() >= k) {
            outLists.add(new ArrayList<>(list));
            return;
        }

        // prune
        if (list.size() + n - i + 1 < k) return;

        for (int j = i; j <= n; j++) {
            list.add(j);
            comb(n, k, j + 1, list);
            list.removeLast();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        comb(n, k, 1, new LinkedList<>());
        return outLists;
    }
}
