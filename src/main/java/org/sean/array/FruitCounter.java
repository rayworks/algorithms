package org.sean.array;

import java.util.*;

// 904 Fruit into baskets
public class FruitCounter {
    public int totalFruit1(int[] fruits) {
        // Sliding Window
        // Ref : https://leetcode.com/problems/fruit-into-baskets/discuss/170740/JavaC%2B%2BPython-Sliding-Window-for-K-Elements
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int max = 0;
        int pre = 0;
        for (int i = 0; i < fruits.length; i++) {
            countMap.put(fruits[i], countMap.getOrDefault(fruits[i], 0) + 1);
            while (countMap.size() > 2) {
                countMap.put(fruits[pre], countMap.get(fruits[pre]) - 1);
                if (countMap.get(fruits[pre]) == 0)
                    countMap.remove(fruits[pre]);

                pre++;
            }
            max = Math.max(max, i - pre + 1);
        }
        return max;
    }

    public int totalFruit(int[] fruits) {
        // Double pointers
        if (fruits == null || fruits.length == 0) return 0;
        int pre = 0, post = 0;

        int max = 0;
        int length = fruits.length;
        while (pre < length) {

            int start = pre;
            while (post < length && fruits[post] == fruits[pre]) {
                post++;
            }
            int end = start;
            if (post < length) {
                end = post;
                post++;

                while (post < length) {
                    if (fruits[post] != fruits[pre] && fruits[post] != fruits[end]) {
                        break;
                    }
                    ++post;
                }
            }
            max = Math.max(max, post - pre);

            if (post < length) {
                pre = post - 1;
                while (pre > 0 && fruits[pre - 1] == fruits[post - 1])
                    pre--;
            } else {
                break;
            }
        }
        return max;
    }
}
