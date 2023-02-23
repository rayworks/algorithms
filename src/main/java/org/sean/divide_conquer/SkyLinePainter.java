package org.sean.divide_conquer;

import java.util.*;

/***
 * 218. The Skyline Problem
 */
public class SkyLinePainter {

    // region Divide and Conquer
    private LinkedList<List<Integer>> merge(LinkedList<List<Integer>> left, LinkedList<List<Integer>> right) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        int hL = 0, hR = 0;
        // Check the height, add appropriately two shadowed buildings
        //
        // hL and hR each track the building height on left and right respectively
        // Now when a shadowed building comes in, our h will guard and prevent the building being added.
        //
        while (!left.isEmpty() && !right.isEmpty()) {
            int x, h;
            if (left.peek().get(0) < right.peek().get(0)) {
                x = left.peek().get(0);
                hL = left.poll().get(1);
            } else if (right.peek().get(0) < left.peek().get(0)) {
                x = right.peek().get(0);
                hR = right.poll().get(1);
            } else {
                x = left.peek().get(0);
                hL = left.poll().get(1);
                hR = right.poll().get(1);
            }

            h = Math.max(hL, hR);
            if (res.isEmpty() || h != res.getLast().get(1))
                res.add(Arrays.asList(x, h));
        }
        res.addAll(left);
        res.addAll(right);

        return res;
    }

    private List<List<Integer>> getSkylineHelper(int[][] buildings, int left, int right) {
        ArrayList<List<Integer>> lists = new ArrayList<>();

        if (left == right) {
            lists.add(Arrays.asList(buildings[left][0], buildings[left][2]));
            lists.add(Arrays.asList(buildings[left][1], 0));
            return lists;
        }

        int mid = left + (right - left) / 2;
        List<List<Integer>> leftLists = getSkylineHelper(buildings, left, mid);
        List<List<Integer>> rightLists = getSkylineHelper(buildings, mid + 1, right);

        return merge(new LinkedList<>(leftLists), new LinkedList<>(rightLists));
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {

        if (buildings.length == 1) {
            ArrayList<List<Integer>> lists = new ArrayList<>();
            lists.add(Arrays.asList(buildings[0][0], buildings[0][2]));
            lists.add(Arrays.asList(buildings[0][1], 0));
            return lists;
        }


        return getSkylineHelper(buildings, 0, buildings.length - 1);
    }
    // endregion

    // region sweep line
    public List<List<Integer>> getSkyline1(int[][] buildings) {
        List<int[]> points = new ArrayList<>();
        for (int[] building : buildings) {
            points.add(new int[]{building[0], -building[2]}); // b[i] entering point
            points.add(new int[]{building[1], building[2]});  // b[i] leaving point
        }

        // sort it by start x else reversed height
        points.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // (height, cnt)
        // using TreeMap to optimize its time performance for removing an element.
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(Collections.reverseOrder());

        // add a virtual start point
        treeMap.put(0, 1);

        int prevH = 0;
        List<List<Integer>> res = new ArrayList<>();

        for (int[] point : points) {
            if (point[1] < 0) // entering
                treeMap.put(-point[1], treeMap.getOrDefault(-point[1], 0) + 1);
            else if (treeMap.get(point[1]) == 1)
                treeMap.remove(point[1]);
            else treeMap.put(point[1], treeMap.get(point[1]) - 1);

            // if height changes from prev one , then it's a key point
            int currH = treeMap.firstKey();
            if (currH != prevH) {
                res.add(Arrays.asList(point[0], currH));
                prevH = currH;
            }
        }
        return res;
    }
    // endregion
}
