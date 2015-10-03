/**
 * Problem link: https://leetcode.com/problems/perfect-squares
 *
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */

import java.util.*;

public class PerfectSquares {
    public static void main(String[] args) {
        PerfectSquares solution = new PerfectSquares();

        assert solution.numSquares(12) == 3;
        assert solution.numSquares(13) == 2;
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int square;
        for(int i = 1 ; (square = i * i) <= n ; ++ i) {
            dp[square] = 1;
        }

        if(dp[n] == 1) {
            return dp[n];
        }

        for(int i = 1 ; i <= n ; ++ i) {
            for(int j = 1 ; (square = i + j * j) <= n ; ++ j) {
                dp[square] = Math.min(dp[i] + 1, dp[square]);
            }
        }

        return dp[n];
    }
}
