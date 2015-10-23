/**
 * Problem link: https://leetcode.com/problems/regular-expression-matching
 *
 * Implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */
#include <iostream>
#include <string>
#include <assert.h>

using namespace std;

class Solution {
    public:
        bool isMatch(string s, string p) {
            return isMatchRecursive(s, 0, p, 0);
        }
    private:
        bool isMatchRecursive(string &s, int si, string& p, int pi) {
            if(p[pi] == '\0') {
                return s[si] == '\0';
            }

            if(p[pi + 1] != '*') {
                return (s[si] == p[pi] || (p[pi] == '.' && s[si] != '\0')) && isMatchRecursive(s, si + 1, p, pi + 1);
            }

            while(p[pi] == s[si] || (p[pi] == '.' && s[si] != '\0')) {
                if(isMatchRecursive(s, si, p, pi + 2)) {
                    return true;
                }

                ++ si;
            }
            return isMatchRecursive(s, si, p, pi + 2);
        }
};

int main(int argc, char** argv) {

    Solution solution;
    assert(!solution.isMatch("aa", "a"));
    assert(solution.isMatch("aa", "a*"));
    assert(solution.isMatch("abc", ".*"));
    assert(solution.isMatch("abc", "abcc*"));
    assert(solution.isMatch("aab", "c*a*b"));
}
