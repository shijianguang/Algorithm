/**
 * Problem link: https://leetcode.com/problems/merge-sorted-array
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 */

public class MergeSortedArray {
    public static void main(String[] args) {

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int numsEnd = m + n - 1;
        int nums1P = m - 1;
        int nums2P = n - 1;

        while(nums1P >= 0 && nums2P >= 0) {
            int num1 = nums1[nums1P];
            int num2 = nums2[nums2P];

            if(num1 > num2) {
                nums1[numsEnd] = num1;
                -- nums1P;
                -- numsEnd;
            } else if(num1 < num2) {
                nums1[numsEnd] = num2;
                -- nums2P;
                -- numsEnd;
            } else {
                nums1[numsEnd] = num1;
                -- nums1P;
                -- numsEnd;
                nums1[numsEnd] = num2;
                -- nums2P;
                -- numsEnd;
            }
        }

        while(nums2P >= 0) {
            nums1[numsEnd] = nums2[nums2P];
            -- nums2P;
            -- numsEnd;
        }

    }
}
