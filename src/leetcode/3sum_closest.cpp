/**
 * Problem link: https://leetcode.com/problems/3sum-closest
 *
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 *     For example, given array S = {-1 2 1 -4}, and target = 1.
 *
 *     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <assert.h>

using namespace std;

class Solution {
    public:
        int threeSumClosest(vector<int>& nums, int target) {
            sort(nums.begin(), nums.end());

            int size = nums.size();
            int prev = 0;
            int diff = INT_MAX;
            int result = 0;
            for(int i = 0 ; i < size - 2 ; ++ i) {
                int first = nums[i];
                if(i != 0) {
                    if(prev == first) {
                        continue;
                    }
                }

                int left = i + 1;
                int right = size - 1;
                while(left < right) {
                    int tmp_sum = first + nums[left] + nums[right];
                    int tmp = abs(tmp_sum - target);
                    if(tmp == 0) {
                        return tmp_sum;
                    }
                    if(tmp < diff) {
                        result = tmp_sum;
                        diff = tmp;
                    }
                    if(tmp_sum < target) {
                        ++ left;
                        while(left < right && nums[left] == nums[left - 1]) {
                            ++ left;
                        }

                    } else {
                        -- right;
                        while(left < right && nums[right] == nums[right + 1]) {
                            -- right;
                        }

                    }
                }
            }

            return result;
        }
};

int main(int argc, char ** argv) {
    Solution solution;

    vector<int> test = {-1, 2, 1, -4};
    assert(solution.threeSumClosest(test, 1) == 2);
}
