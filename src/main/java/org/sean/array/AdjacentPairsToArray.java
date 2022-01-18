package org.sean.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***
 * 1743. Restore the Array From Adjacent Pairs
 */
public class AdjacentPairsToArray {
    public int[] restoreArray(int[][] adjacentPairs) {
        int length = adjacentPairs.length;
        if(length == 1) {
            return adjacentPairs[0];
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            int p = pair[0];
            int v = pair[1];

            fillMap(map, p, v);
            fillMap(map, v, p);
        }

        // N-1 relations keep the duplicate references of middle nodes, excluding the header and the tail nodes
        // e.g.
        // Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
        // Output: [-2,4,1,-3]
        // ====================
        // Constructed map :
        // <4, [-2, 1]>
        // <1, [4, -3]>
        // <-2, [4]>
        // <-3, [1]>

        int startNum = 0;
        for (Integer k : map.keySet()) {
            ArrayList<Integer> array = map.get(k);
            if(array.size()  == 1) {
                startNum = k;
                break;
            }
        }
        ArrayList<Integer> out = new ArrayList<>();
        out.add(startNum);

        int lastKey = startNum;
        int prev = map.get(startNum).get(0);
        out.add(prev);

        int outLen = length + 1;
        while (out.size() < outLen) {
            ArrayList<Integer> list = map.get(prev);
            if(list.size() > 1) {
                if(list.get(0) != lastKey) {
                    out.add(list.get(0));
                } else {
                    out.add(list.get(1));
                }
                lastKey = prev;
                prev = out.get(out.size() - 1);
            } else {
                out.add(list.get(0));
                break;
            }
        }

        return out.stream().mapToInt(value -> value).toArray();
    }

    private void fillMap(Map<Integer, ArrayList<Integer>> map, int p, int v) {
        if(map.containsKey(p)) {
            map.get(p).add(v);
        } else {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(v);
            map.put(p, arr);
        }
    }
}
