/**
 * Problem link: https://leetcode.com/problems/search-for-a-range
 *
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */

public class SearchForARange {
    public static void main(String[] args) {
        SearchForARange solution = new SearchForARange();

        int[] result = solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        System.out.println(result[0] + " " + result[1]);

        result = solution.searchRange(new int[]{8, 8, 8, 8, 8, 8}, 8);
        System.out.println(result[0] + " " + result[1]);

        result = solution.searchRange(new int[]{8, 8, 8, 8, 8, 8}, 7);
        System.out.println(result[0] + " " + result[1]);

        result = solution.searchRange(new int[]{8, 8, 8, 8, 8, 8}, 9);
        System.out.println(result[0] + " " + result[1]);
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];

        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = (left + right) >> 1;
            if(nums[mid] == target) {
                if(mid > 0 && nums[mid] == nums[mid - 1]) {
                    right = mid - 1;
                } else {
                    right = mid;
                    break;
                }
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        result[0] = (right >= 0 && right < nums.length && nums[right] == target) ? right : -1;

        left = 0;
        right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) >> 1;
            if(nums[mid] == target) {
                if(mid < nums.length - 1 && nums[mid] == nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    left = mid;
                    break;
                }
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        result[1] = (left >= 0 && left < nums.length && nums[left] == target) ? left : -1;
        return result;
    }
}
