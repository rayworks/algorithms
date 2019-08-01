package org.sean.array;

import java.util.*;

// 973. K Closest Points to Origin
public class KClosestPoints {
    // Input: points = [[1,3],[-2,2]], K = 1
    // Output: [[-2,2]]

    //    1 <= K <= points.length <= 10000
    //    -10000 < points[i][0] < 10000
    //    -10000 < points[i][1] < 10000
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length;
        if (K == len) {
            return points;
        }

        // contains the same dist points?
        // [key, {pos1, pos2...}]
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        // {key, point[]}
        TreeMap<Integer, int[]> treeMap = new TreeMap<>();
        for (int i = 0; i < len; i++) {
            int key = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            treeMap.put(key, points[i]);

            if (map.containsKey(key)) {
                map.get(key).add(i);
            } else {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);

                map.put(key, list);
            }
        }

        Set<Integer> set = treeMap.keySet();
        Iterator<Integer> iterator = set.iterator();
        int[][] result = new int[K][2];
        int i = 0;
        while (iterator.hasNext()) {
            Integer key = iterator.next();

            // check the pts with same key
            if (map.containsKey(key)) {
                List<Integer> integers = map.get(key);
                for (Integer index : integers) {
                    result[i++] = points[index];
                    if (i >= K) {
                        break;
                    }
                }
            } else {
                result[i++] = treeMap.get(key);
            }

            if (i >= K) {
                break;
            }
        }

        return result;
    }
}
