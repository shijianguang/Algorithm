/**
 * Problem link: https://leetcode.com/problems/4sum
 *
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 * The solution set must not contain duplicate quadruplets.
 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 *
 * A solution set is:
 *    (-1,  0, 0, 1)
 *    (-2, -1, 1, 2)
 *    (-2,  0, 0, 2)
 */

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
    public:
        vector<vector<int>> fourSum(vector<int>& nums, int target) {
            vector<vector<int>> result;
            sort(nums.begin(), nums.end());
            int size = nums.size();
            int prev = 0;
            int prev1 = 0;
            for(int j = 0 ; j < size - 3 ; ++ j) {
                if(j != 0) {
                    if(nums[j] == prev1) {
                        continue;
                    }
                }
                if(nums[j] + nums[j + 1] + nums[j + 2] + nums[j + 3] > target) {
                    prev1 = nums[j];
                    break;
                }
                if(nums[j] + nums[size - 1] + nums[size - 2] + nums[size - 3] < target) {
                    prev1 = nums[j];
                    continue;
                }
                for(int i = j + 1 ; i < size - 2 ; ++ i) {
                    if(i != j + 1) {
                        if(nums[i] == prev) {
                            continue;
                        }
                    }
                    int sub_target = target - nums[j] - nums[i];

                    int left = i + 1, right = size - 1;

                    while(left < right) {
                        int sum = nums[left] + nums[right];

                        if(sum < sub_target) {
                            ++ left;
                            while(left < right && nums[left] == nums[left - 1]) {
                                ++ left;
                            }
                        } else if(sum > sub_target) {
                            -- right;
                            while(left < right && nums[right] == nums[right + 1]) {
                                -- right;
                            }
                        } else {
                            vector<int> tmp(4);
                            tmp[0] = nums[j];
                            tmp[1] = nums[i];
                            tmp[2] = nums[left];
                            tmp[3] = nums[right];
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
                prev1 = nums[j];
            }

            return result;
        }
};

int main(int argc, char ** argv) {
    Solution solution;

    vector<int> test =  {1, 0, -1, 0, -2, 2};

    vector<vector<int>> result = solution.fourSum(test, 0);

    for(auto vec_ele : result) {
        for(auto ele : vec_ele) {
            cout << ele << " ";
        }

        cout << endl;
    }
}
