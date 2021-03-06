/**
 * Problem link: https://leetcode.com/problems/house-robber-ii
 *
 * Note: This is an extension of House Robber.
 *
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

public class HouseRobberII {
    public static void main(String[] args) {
    }

    public int rob(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length));
    }

    public int rob(int[] nums, int start, int end) {
        int size = end - start;
        if(size == 1) {
            return nums[start];
        }
        if(size == 2) {
            return Math.max(nums[start], nums[start + 1]);
        }

        int a = nums[start];
        int b = Math.max(nums[start], nums[start + 1]);

        for(int i = start + 2 ; i < end; ++ i) {
            int tmp = b;
            b = Math.max(a + nums[i], b);
            a = tmp;
        }

        return b;
    }

}
