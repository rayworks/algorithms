package org.sean.array;

import java.util.*;

// 120. Triangle
public class Triangle {
    private int minSum = Integer.MAX_VALUE;

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        // dfs(triangle, 0, 0, "", 0);
        int row = triangle.size();
        for (int i = row - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i)
                        .set(
                                j,
                                triangle.get(i).get(j)
                                        + Math.min(
                                                triangle.get(i + 1).get(j),
                                                triangle.get(i + 1).get(j + 1)));
            }
        }

        System.out.println(String.format(">>> minSum is %d", triangle.get(0).get(0)));

        List<Integer> integers = new ArrayList<Integer>(5);
        Collections.fill(integers, 1);

        return 0;
    }

    // brute force : time out
    private void dfs(List<List<Integer>> triangle, int row, int col, String path, int sum) {
        if (row == triangle.size()) {
            if (minSum > sum) {
                minSum = sum;
            }

            System.out.println(path);
            return;
        }

        path += " -> ";

        path += triangle.get(row).get(col);
        sum += triangle.get(row).get(col);

        dfs(triangle, row + 1, col, path, sum);
        dfs(triangle, row + 1, col + 1, path, sum);
    }

    public static <T> List<T> asList(T... items) {
        List<T> list = new ArrayList<T>();
        for (T item : items) {
            list.add(item);
        }

        return list;
    }

    public static void main(String[] args) {
        Triangle solution = new Triangle();
        List<List<Integer>> triangle =
                asList(asList(2), asList(3, 4), asList(6, 5, 7), asList(4, 1, 8, 3));

        solution.minimumTotal(triangle);
    }
}
