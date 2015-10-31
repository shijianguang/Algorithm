/**
 * Problem link: https://leetcode.com/problems/trapping-rain-water
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 * For example, 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 */

#include <iostream>
#include <vector>
#include <assert.h>

using namespace std;

class Solution {
    public:
        int trap(vector<int>& height) {
            return algN(height);
        }

    private:
        int alg2N(vector<int>& height) {
            int size = height.size();
            vector<int> right_max(size);
            int max_height = 0;
            for(int i = size - 1 ; i >= 0 ; -- i) {
                right_max[i] = max_height;
                int tmp = height[i];
                max_height = tmp > max_height ? tmp : max_height;
            }

            max_height = 0;
            int water_area = 0;
            for(int i = 0 ; i < size ; ++ i) {
                int tmp = height[i];
                int right_max_height = right_max[i];
                int min_height = max_height < right_max_height ? max_height : right_max_height;

                if(min_height > tmp) {
                    water_area += (min_height - tmp);
                }
                max_height = max_height < tmp ? tmp : max_height;
            }

            return water_area;
        }
        int algN(vector<int>& height) {
            int left = 0;
            int right = height.size() - 1;

            int total_area = 0;
            int bar_area = 0;
            int max_height = 0;
            while(left < right) {
                int left_value = height[left];
                int right_value = height[right];
                if(left_value < right_value) {
                    if(left_value > max_height) {
                        total_area += ((left_value - max_height) * (right - left));
                        max_height = left_value;
                    }
                    bar_area += left_value;
                    ++ left;
                } else {
                    if(right_value > max_height) {
                        total_area += ((right_value - max_height) * (right - left));
                        max_height = right_value;
                    }
                    bar_area += right_value;
                    -- right;
                }
            }

            return total_area - bar_area;
        }
};

int main(int argc, char **argv) {
    Solution solution;
    vector<int> test = {0,1,0,2,1,0,1,3,2,1,2,1};

    cout << solution.trap(test) << endl;
    assert(solution.trap(test) == 6);
}
