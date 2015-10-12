/**
 * Problem link: https://leetcode.com/problems/majority-element-ii
 * Problem link: https://leetcode.com/problems/majority-element
 *
 * This solution based on "MJRTY-A Fast Majority Vote Algorithm". Here is a blog about it: http://blog.csdn.net/joylnwang/article/details/7081575
 */

import java.util.*;

public class MajorityElement {
    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();

        assert solution.kMajorityElement(new int[]{1,1,1,1,2}, 3).get(0) == 1;
    }


    List<Integer> kMajorityElement(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int maxCandidate = k - 1;

        int[] candidate = new int[maxCandidate];
        int[] counter = new int[maxCandidate];

        Arrays.fill(candidate, 0);
        Arrays.fill(counter, 0);

        for(int num : nums) {
            boolean found = false;
            for(int i = 0 ; i < maxCandidate ; ++ i) {
                if(counter[i] == 0 || num == candidate[i]) {
                    ++ counter[i];
                    candidate[i] = num;
                    found = true;
                    break;
                }
            }

            if(!found) {
                for(int i = 0 ; i < maxCandidate ; ++ i) {
                    -- counter[i];
                }
            }
        }

        Arrays.fill(counter, 0);

        for(int num : nums) {
            for(int i = 0 ; i < maxCandidate ; ++ i) {
                if(num == candidate[i]) {
                    ++ counter[i];
                    break;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        int target = nums.length / k;
        for(int i = 0 ; i < maxCandidate ; ++ i) {
            if(counter[i] > target) {
                result.add(candidate[i]);
            }
        }

        return result;
    }

}
