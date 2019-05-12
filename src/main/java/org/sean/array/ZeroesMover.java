package org.sean.array;

/** * 283 Move Zeroes */
public class ZeroesMover {
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public void moveZeroes(int[] nums) {
        if (nums != null || nums.length > 0) {
            int zeroCnt = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    int p = i - 1;
                    while (p >= 0) {
                        if (nums[p] != 0) {
                            break;
                        }

                        --p;
                    }

                    if ((p + 1) >= 0 && (p + 1) != i) {
                        swap(nums, p + 1, i);
                    }
                }
            }
        }
    }
}
