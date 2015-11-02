/**
 * Problem link: https://leetcode.com/problems/distinct-subsequences
 *
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 *
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 *
 * Return 3.
 */

public class DistinctSubsequences {
    public static void main(String[] args) {
        DistinctSubsequences solution = new DistinctSubsequences();

        assert solution.numDistinct("rabbbit", "rabbit") == 3;
    }

    public int numDistinct(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        int[][] dp = new int[sLen + 1][tLen + 1];
        dp[0][0] = 1;
        for(int i = 1 ; i <= tLen ; ++ i) {
            dp[0][i] = 0;
        }

        for(int i = 1 ; i <= sLen ; ++ i) {
            dp[i][0] = 1;
        }

        for(int i = 1 ; i <= sLen ; ++ i) {
            for(int j = 1 ; j <= tLen ; ++ j) {
                dp[i][j] = dp[i - 1][j] + (s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] : 0);
            }
        }

        return dp[sLen][tLen];
    }
}
