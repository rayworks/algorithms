package org.sean.array;

import java.util.*;

/***
 * 347. Top K Frequent Elements
 */
public class FrequentElemFinder {
    class Elem {
        int value;
        int times;

        public Elem(int value, int times) {
            this.value = value;
            this.times = times;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Elem) {
                return this.value == ((Elem) obj).value;
            }
            return false;
        }
    }

    private PriorityQueue<Elem> queue = new PriorityQueue<>(11, new Comparator<Elem>() {
        @Override
        public int compare(Elem o1, Elem o2) {
            if (o2.times > o1.times) return 1;
            else if (o2.times < o1.times) return -1;
            else return 0;
        }
    });

    private HashMap<Integer, Integer> map = new HashMap<>();

    private List<Integer> result = new LinkedList<Integer>();


    // Input: nums = [1,1,1,2,2,3], k = 2
    // Output: [1,2]
    public List<Integer> topKFrequent(int[] nums, int k) {
        clear();

        for (int key : nums) {
            // Elem elem = new Elem(key, 0);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        for (Integer i : map.keySet()) {
            queue.add(new Elem(i, map.get(i)));
        }


        int cnt = 0;
        while (!queue.isEmpty()) {
            Elem elem = queue.poll();

            if (cnt < k) {
                result.add(elem.value);
            }

            ++cnt;
        }

        return result;
    }

    private void clear() {
        map.clear();
        result.clear();
        queue.clear();
    }
}
