/**
 * Problem link: https://leetcode.com/problems/single-number-iii
 *
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 *
 * For example:
 *
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 *
 * Note:
 *      1. The order of the result is not important. So in the above example, [5, 3] is also correct.
 *      2. Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 *
 */

public class StringNumberIII {

    public static void main(String[] args) {
        int[] test = {1, 2, 1, 3, 2, 5};
        StringNumberIII solution = new StringNumberIII();

        int[] result = solution.singleNumber(test);
        for(int i = 0 ; i < result.length ; ++ i) {
            System.out.print(result[i] + " ");
        }

        assert (result[0] == 3 && result[1] == 5)
            || (result[1] == 3 && result[0] == 5);
    }

    public int[] singleNumber(int[] nums) {
        if(nums == null || nums.length <= 2) {
            return nums;
        }
        int[] result = new int[2];
        int xor = 0;
        for(int i = 0 ; i < nums.length ; ++ i) {
            xor ^= nums[i];
        }

        int mask = xor & (~(xor - 1));
        int oneValue = 0;
        for(int i = 0 ; i < nums.length ; ++ i) {
            if((nums[i] & mask) > 0) {
                oneValue ^= nums[i];
            }
        }

        result[0] = oneValue;
        result[1] = xor ^ oneValue;
        return result;
    }
}
