/**
 * Problem link: https://leetcode.com/problems/find-peak-element
 *
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 *
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *
 * You may imagine that num[-1] = num[n] = -∞.
 *
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 *
 * click to show spoilers.
 *
 * Note:
 * Your solution should be in logarithmic complexity.
 */

#include <iostream>
#include <vector>
#include <assert.h>

using namespace std;

class Solution {
    public:
        int findPeakElement(vector<int>& nums) {
            int size = nums.size();
            int l = 0, r = size - 1;
            while(l <= r) {
                if(l == r) {
                    return l;
                }
                int m = (l + r) >> 1;
                if(nums[m] < nums[m + 1]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }

            return l;
        }
};

int main(int argc, char** argv) {
    Solution solution;

    vector<int> test1 = {1, 2, 3, 1};
    assert(solution.findPeakElement(test1) == 2);
    cout << "finish test1" << endl;

    vector<int> test2 = {1, 2, 3, 4};
    assert(solution.findPeakElement(test2) == 3);
    cout << "finish test2" << endl;

    vector<int> test3 = {4, 3, 2, 1};
    assert(solution.findPeakElement(test3) == 0);
    cout << "finish test3" << endl;

    vector<int> test4 = {1, 2, 3, 4, 3, 2, 1, 2, 3, 4};
    assert(solution.findPeakElement(test4) == 3);
    cout << "finish test4" << endl;
}
