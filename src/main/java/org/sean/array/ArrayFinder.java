package org.sean.array;

import java.util.*;

/***
 * 2007. Find Original Array From Doubled Array
 */
public class ArrayFinder {
    // O(N)
    public int[] findOriginalArray(int[] changed) {
        int[] emptyArray = new int[0];
        int len = changed.length;
        if (len % 2 != 0) {
            return emptyArray;
        }

        int max = Arrays.stream(changed).max().getAsInt();
        int[] freq = new int[max + 1];
        int[] out = new int[len / 2];

        for (int e : changed) {
            freq[e] += 1;
        }

        int j = 0;
        for (int i = 0; i <= max; i++) {
            while (freq[i] > 0) {
                if (2 * i <= max && freq[i + i] > 0) {
                    freq[i] -= 1;
                    freq[i + i] -= 1;

                    if (freq[i] < 0 || freq[i + i] < 0)
                        return emptyArray;

                    out[j++] = i;
                } else {
                    return emptyArray;
                }
            }
        }
        return out;
    }

    // O(N*lgN)
    public int[] findOriginalArray0(int[] changed) {
        int[] emptyArray = new int[0];
        if (changed.length % 2 != 0) {
            return emptyArray;
        }

        Arrays.sort(changed);

        int len = changed.length;

        // {k, [v]s}
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            List<Integer> positions = map.getOrDefault(changed[i], new ArrayList<>());
            positions.add(i);

            map.put(changed[i], positions);
        }

        List<Integer> out = new ArrayList<>();
        int mid = len / 2;

        int cnt = 0;
        Set<Integer> visitedPos = new HashSet<>();

        for (int i = len - 1; i >= 0; i--) {
            if (visitedPos.contains(i))
                continue;

            int elem = changed[i];
            visitedPos.add(i);

            List<Integer> list = map.get(elem);
            list.remove(list.size() - 1);
            if (list.size() == 0) {
                map.remove(elem);
            }

            if (elem % 2 == 0 && map.get(elem / 2) != null) {
                int n = elem / 2;
                if (map.containsKey(n)) {
                    List<Integer> posList = map.get(n);

                    visitedPos.add(posList.get(posList.size() - 1));
                    posList.remove(posList.size() - 1);

                    if (posList.size() == 0)
                        map.remove(n);

                    out.add(n);

                    if (out.size() >= mid) {
                        break;
                    }
                } else {
                    //System.out.println("not found in map : " + n);
                    return emptyArray;
                }
            } else {
                //System.out.println("not found in map : " + n);
                return emptyArray;
            }
        }

        int[] array = out.stream().mapToInt(v -> v).toArray();
        return array.length == len / 2 ? array : emptyArray;
    }
}
