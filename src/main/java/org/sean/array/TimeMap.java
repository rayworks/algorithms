package org.sean.array;

import java.util.*;

// 981. Time Based Key-Value Store
public class TimeMap {
    private static final String EMPTY = "";
    private HashMap<String, SortedMap<Integer, String>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    // [foo, bar 1]
    // [foo, bar 3]
    // [foo, bar2, 4]
    // -> (foo, 1) // (foo, 3) // (foo, 4) // (foo, 5)
    public void set(String key, String value, int timestamp) {
        if (map.containsKey(key)) {
            SortedMap<Integer, String> valMap = map.get(key);
            valMap.put(timestamp, value); // with timestamps increasing strictly
        } else {
            TreeMap<Integer, String> subMap = new TreeMap<>();
            subMap.put(timestamp, value);
            map.put(key, subMap);
        }
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return EMPTY;

        TreeMap<Integer, String> sortedMap = (TreeMap<Integer, String>) map.get(key);
        if (sortedMap.containsKey(timestamp)) {
            return sortedMap.get(timestamp);
        } else {
            Integer lowerKey = sortedMap.lowerKey(timestamp);
            if (lowerKey == null) {
                return EMPTY;
            } else {
                return sortedMap.get(lowerKey);
            }
        }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

/*Note:
All key/value strings are lowercase.
All key/value strings have length in the range [1, 100]
The timestamps for all TimeMap.set operations are strictly increasing.
1 <= timestamp <= 10^7
TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.*/
