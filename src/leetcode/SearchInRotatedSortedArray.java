/**
 * Problem link: https://leetcode.com/problems/search-in-rotated-sorted-array
 *
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 */

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();

        assert(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 7) == 3);
        assert(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 5) == 1);
        assert(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 1) == 5);
        assert(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 9) == -1);
        assert(solution.search(new int[]{1, 3}, 3) == 1);
        assert(solution.search(new int[]{4,5,6,7,8,1,2,3}, 8) == 4);
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = (left + right) >> 1;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                if(nums[mid] < nums[left] && nums[right] < target) {
                    right = mid - 1 ;
                } else {
                    left = mid + 1;
                }
            } else {
                if(nums[mid] > nums[right] && nums[left] > target) {
                    left = mid + 1 ;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
