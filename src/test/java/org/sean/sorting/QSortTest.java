package org.sean.sorting;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QSortTest {

    private QSort qSort;

    @Before
    public void setUp() throws Exception {
        qSort = new QSort();
    }

    @Test
    public void sort() {
        int[] array = {5, 1, 1, 2, 0, 0};
        qSort.sort(array);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 5}, array);

        array = new int[]{3, 6, 1, 7, 9, 0, 5};
        qSort.sort(array);
        assertArrayEquals(new int[]{0, 1, 3, 5, 6, 7, 9}, array);

    }
}