package org.sean.array;

import java.util.*;

/** * 49. Group Anagrams */
public class AnagramSorter {
    private int[] array = new int[26];

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return Collections.emptyList();

        HashMap<String, List<String>> map = new HashMap<>();

        String s = strs[0];
        List<String> elems = new LinkedList<>();
        elems.add(s);

        if (strs.length == 1) {
            return Arrays.asList(elems);
        }

        String key = extractCountInfo(s);
        map.put(key, elems);

        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            String k = extractCountInfo(str);
            if (map.containsKey(k)) {
                map.get(k).add(str);
            } else {
                List<String> strValues = new LinkedList<>();
                strValues.add(str);
                map.put(k, strValues);
            }
        }

        return new LinkedList<>(map.values());
    }

    // Retrieves the character count info e.g '1a3b1c7z'
    private String extractCountInfo(String s) {
        Arrays.fill(array, 0);

        for (int m = 0; m < s.length(); m++) {
            array[s.charAt(m) - 'a'] += 1;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                builder.append(array[i]).append((char) ('a' + i));
            }
        }
        return builder.toString();
    }
}
