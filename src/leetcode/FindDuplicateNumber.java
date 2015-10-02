/**
 * Problem link: https://leetcode.com/problems/find-the-duplicate-number
 *
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */

public class FindDuplicateNumber {
    public static void main(String[] args) {
        FindDuplicateNumber solution = new FindDuplicateNumber();
        int[] nums = new int[]{1,1};

        assert solution.findDuplicate(nums) == 1;
    }

    public int findDuplicate(int[] nums) {
        int length = nums.length;
        int result = 0;
        for(int i = 0 ; i < length ; ++ i) {
            int cur = nums[i];
            cur = cur > 0 ? cur : -cur;
            if(nums[cur - 1] < 0) {
                result = cur;
                break;
            } else {
                nums[cur - 1] = -nums[cur - 1];
            }
        }

        for(int i = 0 ; i < length ; ++ i) {
            int cur = nums[i];
            if(cur < 0) {
                nums[i] = -cur;
            }
        }

        return result;
    }
}
