package org.sean.array;

import java.util.HashMap;
import java.util.Map;

public class PermutationChecker {
    private Map<Character, Integer> filter(String str) {
        int len = str.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char key = str.charAt(i);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        return map;
    }

    public boolean isPermutation(String src, String target) {
        if (src == null || target == null)
            return false;

        if (src.length() != target.length())
            return false;

        return filter(src).equals(filter(target));
    }
}
