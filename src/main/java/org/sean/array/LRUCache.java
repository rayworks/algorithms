package org.sean.array;

import java.util.LinkedHashMap;
import java.util.Map;

/** * 146. LRU Cache */
public class LRUCache {
    private final LinkedHashMap<Integer, Integer> map;
    private final int size;

    public LRUCache(int capacity) {
        size = capacity;

        // enable accessOrder
        map =
                new LinkedHashMap<Integer, Integer>(size, 0.75f, true) {
                    @Override
                    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                        return size() > size;
                    }
                };
    }

    public int get(int key) {
        Integer val = map.get(key);
        return val == null ? -1 : val;
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}
