package org.sean.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 1146. Snapshot Array
 */
public class SnapshotArray {
    private int snapCnt = 0;

    // [,[snap_id, val], ...]
    List<List<int[]>> cache;
    private Map<Integer, Integer> pendingChanges;

    // after snap, the value of index should be persisted
    // others are the pending ones
    public SnapshotArray(int length) {
        cache = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            cache.add(new ArrayList<>());
        }
        pendingChanges = new HashMap<>();
    }

    public void set(int index, int val) {
        pendingChanges.put(index, val);
    }

    public int snap() {
        snapCnt++;

        int snapKey = snapCnt - 1;
        for (Map.Entry<Integer, Integer> entry : pendingChanges.entrySet()) {
            int key = entry.getKey();
            cache.get(key).add(new int[]{snapKey, entry.getValue()});
        }
        pendingChanges.clear();

        return snapKey;
    }

    public int get(int index, int snap_id) {
        List<int[]> list = cache.get(index);
        int len = list.size();
        if (len == 0)
            return 0;

        // (snap, val)
        // (1,3), (4,4), (7,6)...
        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int[] pair = list.get(mid);
            int id = pair[0];

            if (id == snap_id)
                return pair[1];
            else if (id < snap_id) {
                if (mid + 1 < len && list.get(mid + 1)[0] > snap_id) {
                    return pair[1];
                }
                left = mid + 1;
            } else {
                if (mid >= 1 && list.get(mid - 1)[0] < snap_id) {
                    return list.get(mid - 1)[1];
                }
                right = mid - 1;
            }
        }
        if (right < 0)
            return 0;
        if (left >= len) {
            return list.get(len - 1)[1];
        }

        return list.get(left)[1];
    }
}
