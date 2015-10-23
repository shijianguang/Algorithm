/**
 * Problem link: https://leetcode.com/problems/3sum
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 *     For example, given array S = {-1 0 1 2 -1 -4},
 *
 *     A solution set is:
 *     (-1, 0, 1)
 *     (-1, -1, 2)
 */

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
    public:
        vector<vector<int>> threeSum(vector<int>& nums) {
            vector<vector<int>> result;
            sort(nums.begin(), nums.end());
            int size = nums.size();
            int prev = 0;
            for(int i = 0 ; i < size - 2 ; ++ i) {
                if(i != 0) {
                    if(nums[i] == prev) {
                        continue;
                    }
                }
                int target = -nums[i];

                int left = i + 1, right = size - 1;

                while(left < right) {
                    int sum = nums[left] + nums[right];

                    if(sum < target) {
                        ++ left;
                        while(left < right && nums[left] == nums[left - 1]) {
                            ++ left;
                        }
                    } else if(sum > target) {
                        -- right;
                        while(left < right && nums[right] == nums[right + 1]) {
                            -- right;
                        }
                    } else {
                        vector<int> tmp(3);
                        tmp[0] = nums[i];
                        tmp[1] = nums[left];
                        tmp[2] = nums[right];
                        result.push_back(tmp);
                        ++ left;
                        -- right;
                        while(left < right && nums[left] == nums[left - 1]) {
                            ++ left;
                        }
                        while(left < right && nums[right] == nums[right + 1]) {
                            -- right;
                        }
                    }
                }

                prev = nums[i];
            }

            return result;
        }
};

int main(int argc, char ** argv) {
    Solution solution;

    vector<int> test =  {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};

    vector<vector<int>> result = solution.threeSum(test);

    for(auto vec_ele : result) {
        for(auto ele : vec_ele) {
            cout << ele << " ";
        }

        cout << endl;
    }
}
