/**
 * Problem link: https://leetcode.com/problems/minimum-size-subarray-sum
 *
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 */

public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        MinimumSizeSubarraySum solution = new MinimumSizeSubarraySum();

        assert solution.minSubArrayLen(7, new int[]{2,3,1,2,4,3}) == 2;
        assert solution.minSubArrayLen(1, new int[]{1}) == 1;
        assert solution.minSubArrayLen(100, new int[]{}) == 0;
        assert solution.minSubArrayLen(100, new int[]{1, 2, 3, 4, 5}) == 0;
    }

    public int minSubArrayLen(int s, int[] nums) {
        int result = -1;
        int preIndex = 0;
        int behindIndex = 0;
        int length = nums.length;
        int sum = 0;
        for( ; preIndex < length ; ) {
            while(preIndex < length) {
                sum += nums[preIndex];
                if(sum >= s) {
                    ++ preIndex;
                    break;
                } else {
                    ++ preIndex;
                }
            }

            if(sum < s) {
                break;
            }

            while(behindIndex < preIndex) {
                sum -= nums[behindIndex];
                if(sum < s) {
                    ++ behindIndex;
                    break;
                } else {
                    ++ behindIndex;
                }
            }

            int subLength = preIndex - behindIndex + 1;
            if(result == -1 || subLength < result) {
                result = subLength;
            }
        }

        return result == -1 ? 0 : result;
    }
}
