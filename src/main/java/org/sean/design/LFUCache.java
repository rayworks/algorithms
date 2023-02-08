package org.sean.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/***
 * 460. LFU (Least Frequently Used) Cache
 */
public class LFUCache {

    private Map<Integer, Integer> kvMap = new HashMap<>();
    private Map<Integer, Integer> keyToFreq = new HashMap<>();
    private Map<Integer, LinkedHashSet<Integer>> freqToKey = new HashMap<>();

    private int minFreq;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    private void update(int key, int frequency, int val) {
        kvMap.put(key, val);
        keyToFreq.put(key, frequency);

        freqToKey.putIfAbsent(key, new LinkedHashSet<>());
        freqToKey.get(frequency).add(key);
    }

    public int get(int key) {
        if (kvMap.get(key) == null)
            return -1;

        int freq = keyToFreq.get(key);
        Set<Integer> keys = freqToKey.get(freq);
        keys.remove(key);
        if (minFreq == freq && keys.isEmpty()) {
            minFreq++;
        }

        int val = kvMap.get(key);
        update(key, freq + 1, val);

        return val;
    }

    public void put(int key, int value) {
        if (capacity < 0) return;
        if (kvMap.get(key) != null) {
            kvMap.put(key, value);
            keyToFreq.put(key, keyToFreq.get(key));

            get(key);
            return;
        }

        if (capacity == kvMap.size()) {
            Set<Integer> keys = freqToKey.get(minFreq);
            int k = keys.iterator().next();
            keys.remove(k);
            kvMap.remove(k);
        }
        minFreq = 1;
        update(key, 1, value);
    }

}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 *
 * For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key
 * would be invalidated.
 *
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the
 * smallest use counter is the least frequently used key.
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter
 * for a key in the cache is incremented either a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 */