package org.sean.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 118. Pascal's Triangle
public class PascalTriangle {
    public static void main(String[] args) {
        PascalTriangle solution = new PascalTriangle();
        System.out.println(Arrays.toString(solution.generate(5).toArray()));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new LinkedList<>();
        if (numRows == 0) return lists;

        List<Integer> firstList = new ArrayList<>();
        firstList.add(1);
        lists.add(firstList);
        if (numRows == 1) {
            return lists;
        }

        List<Integer> secondList = new ArrayList<>();
        secondList.add(1);
        secondList.add(1);
        lists.add(secondList);

        if (numRows == 2) {
            return lists;
        }

        for (int i = 2; i < numRows; i++) {
            ArrayList<Integer> list = new ArrayList<>(i + 1);
            for (int k = 0; k < (i + 1); k++) {
                list.add(1);
            }

            List<Integer> preList = lists.get(i - 1);
            for (int j = 1; j < i; j++) {
                list.set(j, preList.get(j - 1) + preList.get(j));
            }

            lists.add(list);
        }

        return lists;
    }
}
