/**
 * Problem link: https://leetcode.com/problems/house-robber
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

public class HouseRobber {
    public static void main(String[] args) {
    }

    public int rob(int[] nums) {
        return rob2(nums);
    }

    public int rob1(int[] nums) {
        int size = nums.length;
        if(size == 0) {
            return 0;
        }
        if(size == 1) {
            return nums[0];
        }
        if(size == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[][] dp = new int[size][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        dp[1][0] = nums[0];
        dp[1][1] = nums[1];
        for(int i = 2 ; i < size ; ++ i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][0], Math.max(dp[i - 2][0], dp[i - 2][1]) + nums[i]);
        }

        return Math.max(dp[size - 1][0], dp[size - 1][1]);
    }

    public int rob2(int[] nums) {
        int size = nums.length;
        if(size == 0) {
            return 0;
        }
        if(size == 1) {
            return nums[0];
        }
        if(size == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int a = nums[0];
        int b = Math.max(nums[0], nums[1]);

        for(int i = 2 ; i < size; ++ i) {
            int tmp = b;
            b = Math.max(a + nums[i], b);
            a = tmp;
        }

        return b;
    }
}
