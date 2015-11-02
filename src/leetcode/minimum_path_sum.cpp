/**
 * Problem link: https://leetcode.com/problems/minimum-path-sum
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 */
#include <iostream>
#include <vector>
#include <assert.h>

using namespace std;

class Solution {
    public:
        int minPathSum(vector<vector<int>>& grid) {
            int row = grid.size();
            if(row == 0) {
                return 0;
            }
            int col = grid[0].size();

            int* dp = new int[col];
            int sum = 0;
            for(int j = 0 ; j < col ; ++ j) {
                sum += grid[0][j];
                dp[j] = sum;
            }

            for(int i = 1 ; i < row ; ++ i) {
                dp[0] = dp[0] + grid[i][0];
                for(int j = 1 ; j < col ; ++ j) {
                    dp[j] = std::min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }

            int result = dp[col - 1];
            delete[] dp;
            return result;
        }
};

int main(int argc, char **argv) {
    vector<vector<int>> test = {{1, 2}, {1, 1}};

    Solution solution;

    assert(solution.minPathSum(test) == 3);
}
