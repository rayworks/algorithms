package org.sean.recursive;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class MinPathFinderTest {

    private MinPathFinder pathFinder;

    @Before
    public void setup() {
        pathFinder = new MinPathFinder();
    }

    @Test
    public void minPathSum() {

        int[][] src = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        Assert.assertEquals(7, pathFinder.minPathSum(src));

        // Not pass for this test case!!
        int[][] dat = {
                {1, 4, 8, 6, 2, 2, 1, 7},
                {4, 7, 3, 1, 4, 5, 5, 1},
                {8, 8, 2, 1, 1, 8, 0, 1},
                {8, 9, 2, 9, 8, 0, 8, 9},
                {5, 7, 5, 7, 1, 8, 5, 5},
                {7, 0, 9, 4, 5, 6, 5, 6},
                {4, 9, 9, 7, 9, 1, 9, 0}
        };
        Assert.assertEquals(47, pathFinder.minPathSum(dat));
    }

    @Test
    public void testLargeData() {
        String str =
                "[7,1,3,5,8,9,9,2,1,9,0,8,3,1,6,6,9,5],\n" +
                        "[9,5,9,4,0,4,8,8,9,5,7,3,6,6,6,9,1,6],\n" +
                        "[8,2,9,1,3,1,9,7,2,5,3,1,2,4,8,2,8,8],\n" +
                        "[6,7,9,8,4,8,3,0,4,0,9,6,6,0,0,5,1,4],\n" +
                        "[7,1,3,1,8,8,3,1,2,1,5,0,2,1,9,1,1,4],\n" +
                        "[9,5,4,3,5,6,1,3,6,4,9,7,0,8,0,3,9,9],\n" +
                        "[1,4,2,5,8,7,7,0,0,7,1,2,1,2,7,7,7,4],\n" +
                        "[3,9,7,9,5,8,9,5,6,9,8,8,0,1,4,2,8,2],\n" +
                        "[1,5,2,2,2,5,6,3,9,3,1,7,9,6,8,6,8,3],\n" +
                        "[5,7,8,3,8,8,3,9,9,8,1,9,2,5,4,7,7,7],\n" +
                        "[2,3,2,4,8,5,1,7,2,9,5,2,4,2,9,2,8,7],\n" +
                        "[0,1,6,1,1,0,0,6,5,4,3,4,3,7,9,6,1,9]";
        int beg = 0;
        int end = 0;

        int lastEnd = end;
        List<String> digits = new LinkedList<>();

        while (beg >= 0) {
            beg = str.indexOf("[", lastEnd);
            if (beg >= 0) {
                end = str.indexOf("]", beg);
                lastEnd = end;

                digits.add(str.substring(beg + 1, end));
            }
        }
        System.out.println(digits);

        int rowCnt = digits.size();
        int colCnt = digits.get(0).split(",").length;
        int[][] src = new int[rowCnt][colCnt];

        for (int i = 0; i < digits.size(); i++) {
            String[] array = digits.get(i).split(",");

            for (int j = 0; j < colCnt; j++) {
                src[i][j] = Integer.parseInt(array[j]);
            }
        }

        int len = pathFinder.minPathSum(src);

        Assert.assertEquals(85, len);
    }
}