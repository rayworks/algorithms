package org.sean.array;

import java.util.Map;
import java.util.TreeMap;

/***
 * 2070. Most Beautiful Item for Each Query
 */
public class MaxBeautyCounter {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        // get rid  of the duplicate ones, keep the entry with large value
        TreeMap<Integer, Integer> map = new TreeMap<>(Integer::compare);
        for (int[] p : items) {
            if (map.containsKey(p[0])) {
                if (map.get(p[0]) < p[1]) {
                    map.put(p[0], p[1]);
                }
            } else {
                map.put(p[0], p[1]);
            }
        }

        int size = map.size();
        int[][] nItems = new int[size][2];

        int index = 0;
        for (int i : map.keySet()) {
            nItems[index++] = new int[]{i, map.get(i)};
        }

        int maxSoFar = 0;
        for (int[] pair : nItems) {
            if (maxSoFar < pair[1]) {
                maxSoFar = pair[1];
            } else {
                pair[1] = maxSoFar;
                map.put(pair[0], pair[1]);
            }
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            if (q < nItems[0][0]) {
                result[i] = 0;
            } else {
                Map.Entry<Integer, Integer> entry = map.floorEntry(queries[i]);
                result[i]  = entry.getValue();
            }
        }
        return result;
    }
}
