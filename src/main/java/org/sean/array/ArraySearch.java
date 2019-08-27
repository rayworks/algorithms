package org.sean.array;

import java.util.LinkedList;

/** * 33. Search in Rotated Sorted Array */
public class ArraySearch {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int len = nums.length;
        int turnoverIndex = 0;

        for (int i = 0; i < len; i++) {
            if(i < len - 1 && nums[i] > nums[i + 1]) {
                turnoverIndex = i + 1;
                break;
            }
        }

        // concat the same rotated string
        int[] dstArray = new int[len * 2];
        System.arraycopy(nums, 0, dstArray, 0, len);
        System.arraycopy(nums, 0, dstArray, len, len);

        int begin = 0;
        if (turnoverIndex > 0) {
            begin = turnoverIndex;
        }
        int end = turnoverIndex + len - 1;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;

            int elem = dstArray[mid];
            if(elem == target) {
                // {4,5,6,7,0,1,2 + 4,5,6,7,0,1,2}
                return mid >= len ? mid - len : mid;
            } else  if(elem > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return  -1;
    }
}
