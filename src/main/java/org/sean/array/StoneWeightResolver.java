package org.sean.array;

import java.util.Comparator;
import java.util.PriorityQueue;

/** * 1046. Last Stone Weight */
public class StoneWeightResolver {
    private PriorityQueue<Integer> priorityQueue;

    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) return 0;
        if (stones.length == 1) {
            return stones[0];
        }

        int size = stones.length;
        priorityQueue =
                new PriorityQueue<>(
                        size,
                        new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                return o2.compareTo(o1);
                            }
                        });

        for (int i : stones) {
            priorityQueue.add(i);
        }

        while (!priorityQueue.isEmpty()) {
            Integer elem = priorityQueue.poll();
            if (elem != null) {
                Integer next = priorityQueue.poll();
                if (next != null) {
                    if (!elem.equals(next)) {
                        priorityQueue.add(elem - next);
                    }
                } else {
                    return elem;
                }
            }
        }
        return 0;
    }
}
