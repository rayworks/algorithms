package org.sean.sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// 791. Custom Sort String
public class CustomSort {
    public String customSortString(String order, String str) {
        if (order == null || order.isEmpty() || str == null || str.isEmpty()) return str;

        // Index mapping
        int[] positions = new int['z' - 'a' + 1];
        Arrays.fill(positions, -1);

        Map<Integer, Character> posCharMap = new HashMap<>();
        int orderLen = order.length();
        for (int i = 0; i < orderLen; i++) {
            char ch = order.charAt(i);
            positions[ch - 'a'] = i;

            posCharMap.put(i, ch);
        }

        // <Pos, count>
        TreeMap<Integer, Integer> map = new TreeMap<>();
        StringBuilder sorted = new StringBuilder();
        StringBuilder rest = new StringBuilder();
        int len = str.length();
        for (int j = 0; j < len; j++) {
            char c = str.charAt(j);
            int position = positions[c - 'a'];
            if (position < 0) {
                rest.append(c);
            } else {
                if (map.containsKey(position)) map.put(position, map.get(position) + 1);
                else map.put(position, 1);
            }
        }

        for (int pos : map.keySet()) {
            int cnt = map.get(pos);
            char ch = posCharMap.get(pos);
            for (int i = 0; i < cnt; i++) {
                sorted.append(ch);
            }
        }
        return sorted + rest.toString();
    }
}
