/**
 * Problem link: https://leetcode.com/problems/two-sum
 *
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 *
 */

import java.util.*;

public class TwoSum {
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();

        int[] result = null;

        result = solution.twoSum(new int[] {2, 7, 11, 15}, 9);
        assert result[0] == 1 && result[1] == 2;

        result = solution.twoSum(new int[] {1, 1}, 2);
        assert result[0] == 1 && result[1] == 2;

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for(int i = 0 ; i < nums.length ; ++ i) {
            map.put(nums[i], i);
        }

        int smallIndex = 0;
        int bigIndex = 0;
        int length = nums.length - 1;
        for( ; smallIndex < length ; ++ smallIndex) {
            Integer value = map.get(Integer.valueOf(target - nums[smallIndex]));
            if(value != null && value > smallIndex) {
                bigIndex = value;
                break;
            }
        }

        return new int[]{smallIndex + 1, bigIndex + 1};
    }
}
