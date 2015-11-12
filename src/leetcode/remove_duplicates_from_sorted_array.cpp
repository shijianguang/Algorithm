/**
 * Problem link: https://leetcode.com/problems/remove-duplicates-from-sorted-array
 *
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 *
 * For example,
 * Given input array nums = [1,1,2],
 *
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 */

#include <iostream>
#include <vector>
#include <assert.h>

using namespace std;

class Solution {
    public:
        int removeDuplicates(vector<int>& nums) {
            int size = nums.size();

            if(size <= 1) {
                return size;
            }

            int start = 0;
            int end = 1;
            for( ; end < size ; ++ end) {
                int start_num = nums[start];
                int end_num = nums[end];
                if(end_num != start_num) {
                    ++start;
                    nums[start] = end_num;
                }
            }

            return start + 1;
        }
};

int main(int argc, char** argv) {
    Solution solution;

    vector<int> test1 = {1, 1, 2};
    assert(solution.removeDuplicates(test1) == 2);

    vector<int> test2 = {1, 2, 3};
    assert(solution.removeDuplicates(test2) == 3);
}
