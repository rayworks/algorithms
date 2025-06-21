package org.sean.array;

import java.util.HashMap;

public class LRUCacheRaw {
    static class CacheItem {
        int key;
        int value;
        CacheItem next;
        CacheItem prev;

        public CacheItem(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final CacheItem head;
    private final CacheItem tail;

    private final HashMap<Integer, CacheItem> itemHashMap = new HashMap<>();

    public LRUCacheRaw(int capacity) {
        this.capacity = capacity;

        head = new CacheItem(-1, 0);
        tail = new CacheItem(10001, 0);

        // doubly linked list + hashMap
        head.prev = tail;
        head.next = tail;
        tail.next = head;
        tail.prev = head;
    }

    public int get(int key) {
        CacheItem item = itemHashMap.get(key);
        if (item == null) {
            return -1;
        }
        int val = item.value;
        item.prev.next = item.next;
        item.next.prev = item.prev;

        insertAfterHead(item);

        return val;
    }

    private void insertAfterHead(CacheItem item) {
        item.prev = head;
        item.next = head.next;
        head.next.prev = item;
        head.next = item;
    }

    public void put(int key, int value) {
        CacheItem item = itemHashMap.get(key);
        if (item == null) {
            if (itemHashMap.size() == capacity) {
                // remove the last element from the tail
                CacheItem prevTail = tail.prev;
                prevTail.prev.next = tail;
                tail.prev = prevTail.prev;

                itemHashMap.remove(prevTail.key);
            }
            item = new CacheItem(key, value);
            itemHashMap.put(key, item);
        } else {
            item.value = value;
            item.prev.next = item.next;
            item.next.prev = item.prev;
        }

        insertAfterHead(item);
    }
}
