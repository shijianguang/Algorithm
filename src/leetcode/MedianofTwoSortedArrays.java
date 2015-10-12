/**
 * Problem link: https://leetcode.com/problems/median-of-two-sorted-arrays
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */

public class MedianofTwoSortedArrays {
    public static void main(String[] args) {
        MedianofTwoSortedArrays solution = new MedianofTwoSortedArrays();

        System.out.println(solution.findMedianSortedArrays(new int[]{0, 1, 2, 3, 4, 5, 6}, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        assert Double.compare(solution.findMedianSortedArrays(new int[]{0, 1, 2, 3, 4, 5, 6}, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), 4) == 0;

        System.out.println();
        assert Double.compare(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{1, 3, 4}), 3) == 0;

        System.out.println();
        assert Double.compare(solution.findMedianSortedArrays(new int[]{1, 1}, new int[]{1, 2}), 1) == 0;

        System.out.println();
        assert Double.compare(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{1, 1}), 1) == 0;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] shorter = nums1.length <= nums2.length ? nums1 : nums2;
        int[] longer = nums1.length > nums2.length ? nums1 : nums2;

        int total = shorter.length + longer.length;
        if((total & 0x1) == 1) {
            return findKth(shorter, 0, longer, 0, (total >> 1) + 1);
        } else {
            return (findKth(shorter, 0, longer, 0, total >> 1) + findKth(shorter, 0, longer, 0, (total >> 1) + 1)) / 2;
        }
    }

    double findKth(int[] shorter, int sStart, int[] longer, int lStart, int k) {
        int sLength = shorter.length - sStart;
        int lLength = longer.length - lStart;

        if(sLength > lLength) {
            return findKth(longer, lStart, shorter, sStart, k);
        }

        if(0 == sLength) {
            return longer[k - 1];
        }

        if(k == 1) {
            return Math.min(shorter[sStart], longer[lStart]);
        }

        int sOff = Math.min(k >> 1, sLength);
        int lOff = k - sOff;

        int sIndex = sStart + sOff;
        int lIndex = lStart + lOff;

        int sValue = shorter[sIndex - 1];
        int lValue = longer[lIndex - 1];

        if(sValue < lValue) {
            return findKth(shorter, sIndex, longer, lStart, k - sOff);
        } else if(sValue > lValue) {
            return findKth(shorter, sStart, longer, lIndex, k - lOff);
        } else {
            return sValue;
        }
    }
}
