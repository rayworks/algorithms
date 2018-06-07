package org.sean.array;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sean on 6/26/17.
 */
public class DistanceFinder {
    // Each given array will have at least 1 number. There will be at least two non-empty arrays
    // The total number of the integers in all the m arrays will be in the range of [2, 10000].
    // The integers in the m arrays will be in the range of [-10000, 10000].
    public int maxDistance(List<List<Integer>> arrays) {
        if (arrays.size() == 2) {
            List<Integer> first = arrays.get(0);
            List<Integer> sec = arrays.get(1);
            int v1 = Math.abs(sec.get(sec.size() - 1) - first.get(0));
            int v2 = Math.abs(sec.get(0) - first.get(first.size() - 1));

            return Math.max(v1, v2);
        }

        int[] range = new int[20001];

        for (List<Integer> lt : arrays) {
            for (Integer v : lt) {
                range[v + 10000] = 1;
            }
        }

        int min = Integer.MAX_VALUE;
        int minSec = Integer.MAX_VALUE;
        for (int i = 0; i <= 20000; i++) {
            if (range[i] == 1) {
                if (min == Integer.MAX_VALUE) {
                    min = i - 10000;
                } else {
                    minSec = i - 10000;
                    break;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int maxSec = Integer.MIN_VALUE;
        for (int i = 20000; i >= 0; i--) {
            if (range[i] == 1) {
                if (max == Integer.MIN_VALUE) {
                    max = i - 10000;
                } else {
                    maxSec = i - 10000;
                    break;
                }
            }
        }


        HashSet<Integer> minSet = new HashSet<>();
        HashSet<Integer> minSecSet = new HashSet<>();
        HashSet<Integer> maxSet = new HashSet<>();
        HashSet<Integer> maxSecSet = new HashSet<>();

        for (int j = 0; j < arrays.size(); j++) {
            List<Integer> sub = arrays.get(j);
            for (Integer elem : sub) {
                if (elem == min) {
                    minSet.add(j);
                } else if (elem == minSec) {
                    minSecSet.add(j);
                } else if (elem == max) {
                    maxSet.add(j);
                } else if (elem == maxSec) {
                    maxSecSet.add(j);
                }
            }
        }

        if (!setEqual(minSet, maxSet)) {
            return Math.abs(max - min);
        } else {
            return Math.max(Math.abs(maxSec - min), Math.abs(max - minSec));
        }
    }

    private boolean setEqual(HashSet<Integer> s1, HashSet<Integer> s2) {
        if (s1.size() == s2.size() && s1.size() == 1) { // if max, min in one list
            for (Integer i : s1) {
                if (!s2.contains(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
