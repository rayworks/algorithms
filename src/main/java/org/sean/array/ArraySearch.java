package org.sean.array;

/**
 * 33. Search in Rotated Sorted Array
 */
public class ArraySearch {
    public int searchExt(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // check two ends
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;

            // eg:
            //4567012
            //5670124
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > nums[left]) { // if it's already sorted in the left half
                if (nums[mid] > target && nums[left] < target) { // check the target position in sorted range
                    right = mid - 1;
                    left++;
                } else {
                    left = mid + 1;
                }
            } else {
                // else right half is sorted
                if (nums[mid] < target && nums[right] > target) {
                    left = mid + 1;
                    right--;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    // region Time O(logN)
    private int doBinSearch(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int curr = nums[mid];
            if (curr == target) {
                return mid;
            } else if (curr > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    private int searchTurningPos(int[] nums, int l, int r) {
        if (l == r) {
            return -1;
        }
        if (l == r - 1) {
            return nums[l] > nums[r] ? r : -1;
        }
        int mid = l + (r - l) / 2;
        int leftResult = searchTurningPos(nums, l, mid);
        int rightResult = searchTurningPos(nums, mid + 1, r);

        if (leftResult < 0 && rightResult < 0) {
            // compare the max val of the left side with the min of right as they're already sorted SubArrays
            if (mid + 1 <= r && nums[mid] > nums[mid + 1]) {
                return mid + 1;
            }
        }

        if (leftResult >= 0) return leftResult;
        if (rightResult >= 0) return rightResult;

        return -1;
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int len = nums.length;
        int pos = searchTurningPos(nums, 0, len - 1);
        //System.out.println(">>> turning pos : " + pos);
        if (pos >= 0) {
            int res = doBinSearch(nums, 0, pos - 1, target);
            if (res >= 0) return res;
            else return doBinSearch(nums, pos, len - 1, target);
        } else {
            return doBinSearch(nums, 0, len - 1, target);
        }
    }
    // endregion

    public int search0(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int len = nums.length;
        int turnoverIndex = 0;

        for (int i = 0; i < len; i++) {
            if (i < len - 1 && nums[i] > nums[i + 1]) {
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
            if (elem == target) {
                // {4,5,6,7,0,1,2 + 4,5,6,7,0,1,2}
                return mid >= len ? mid - len : mid;
            } else if (elem > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return -1;
    }
}
