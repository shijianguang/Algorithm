/**
 * Problem link: https://leetcode.com/problems/remove-element
 *
 * Given an array and a value, remove all instances of that value in place and return the new length.
 *
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */

public class RemoveElement {
    public static void main(String[] args) {
        RemoveElement solution = new RemoveElement();

        assert solution.removeElement(new int[]{1, 2, 3, 4}, 5) == 4;
        assert solution.removeElement(new int[]{1}, 1) == 0;
        assert solution.removeElement(new int[]{1, 2, 3, 4, 5}, 4) == 4;
    }

    public int removeElement(int[] nums, int val) {
        int start = -1;
        int end = 0;
        for( ; end < nums.length ; ++ end) {
            if(nums[end] != val) {
                ++ start;
                nums[start] = nums[end];
            }
        }

        return start + 1;
    }
}
