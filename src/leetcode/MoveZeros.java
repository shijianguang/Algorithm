/**
 * Problem link: https://leetcode.com/problems/move-zeroes
 *
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 */

public class MoveZeros {
    public static void main(String[] args) {
        MoveZeros solution = new MoveZeros();

        int[] nums = new int[]{0, 1, 0, 3, 12};

        solution.moveZeroes(nums);

        assert nums[0] == 1;
        assert nums[1] == 3;
        assert nums[2] == 12;
        assert nums[3] == 0;
        assert nums[4] == 0;
    }

    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int zeroNums = 0;
        int behindIndex = 0;
        int aheadIndex = 0;
        int start = 0;
        for( ; start < length ; ++ start, ++ behindIndex, ++ aheadIndex) {
            int cur = nums[start];
            if (cur == 0) {
                aheadIndex ++;
                zeroNums ++;
                break;
            }
        }

        if (aheadIndex >= length) {
            return;
        }

        for( ; aheadIndex < length ; ++ aheadIndex) {
            int cur = nums[aheadIndex];
            if(cur == 0) {
                continue;
            } else {
                nums[behindIndex ++] = cur;
            }
        }

        for( ; behindIndex < length ; ++ behindIndex) {
            nums[behindIndex] = 0;
        }

        return;
    }
}
