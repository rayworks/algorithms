package org.sean.sorting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/***
 * 451. Sort Characters By Frequency
 */
public class FrequencySort {
    public String frequencySort(String s) {
        if (s == null) {
            return "";
        }

        int length = s.length();
        if (length <= 1) {
            return s;
        }

        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) { //5@a, 7@b
                int length2 = o2.length();
                int length1 = o1.length();
                if(length2 > length1) {
                    return 1;
                } else if(length2 < length1) {
                    return -1;
                }
                return o2.compareTo(o1);
            }
        });

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        for (Character key : map.keySet()) {
            queue.add(map.get(key) + "," + key);
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            String str = queue.poll();
            String[] strs = str.split(",");
            int n = Integer.parseInt(strs[0]);
            for (int j = 0; j < n; j++) {
                builder.append(strs[1]);
            }
        }
        return builder.toString();
    }
}
