/**
 * Problem link: https://leetcode.com/problems/scramble-string
 *
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 *
 * Below is one possible representation of s1 = "great":
 *
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 *
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 *
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 *
 * We say that "rgeat" is a scrambled string of "great".
 *
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 *
 *      rgtae
 *     /    \
 *    rg    tae
 *   / \    /  \
 *  r   g  ta   e
 *         / \
 *        t   a
 *
 * We say that "rgtae" is a scrambled string of "great".
 *
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */

public class ScrambleString {
    public static void main(String[] args) {
        ScrambleString solution = new ScrambleString();

        assert solution.isScramble("rgtae", "great");
        assert solution.isScramble("rgeat", "great");
    }

    public boolean isScramble(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();

        if(s1Len != s2Len) {
            return false;
        }

        boolean[][][] dp = new boolean[s1Len][s2Len][s1Len + 1];
        for(int i = 0 ; i < s1Len ; ++ i) {
            for(int j = 0 ; j < s2Len ; ++ j) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for(int len = 2 ; len <= s1Len ; ++ len) {
            for(int i = 0 ; i < s1Len - len + 1 ; ++ i) {
                for(int j = 0 ; j < s2Len - len + 1; ++ j) {
                    for(int k = 1 ; k < len ; ++ k) {
                        dp[i][j][len] |= ((dp[i][j][k] && dp[i + k][j + k][len - k]) || (dp[i][j + len - k][k] && dp[i + k][j][len - k]));
                    }
                }
            }
        }

        return dp[0][0][s1Len];
    }
}
