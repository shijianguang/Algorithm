/**
 * Problem link: https://leetcode.com/problems/implement-strstr
 *
 * Implement strStr().
 *
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Update (2014-11-02):
 * The signature of the function had been updated to return the index instead of the pointer. If you still see your function signature returns a char * or String, please click the reload button  to reset your code definition.
 */
#include <iostream>
#include <string>
#include <assert.h>

using namespace std;

class Solution {
    public:
        int strStr(string haystack, string needle) {
            return kmp_search(haystack, needle);
        }

    private:
        int kmp_search(string& s, string& p) {
            if(p.length() == 0) {
                return 0;
            }
            int* next = get_next(p);
            int i = 0;
            int j = 0;
            int s_len = s.length();
            int p_len = p.length();
            while(i < s_len && j < p_len) {
                if(j == -1 || s[i] == p[j]) {
                    ++ j;
                    ++ i;
                } else {
                    j = next[j];
                }
            }

            delete next;
            if(j == p_len) {
                return i - j;
            } else {
                return -1;
            }
        }

        int* get_next(string& p) {
            int j = 0;
            int k = -1;
            int length = p.length();
            int* next = new int[length];
            next[0] = -1;
            while(j < length - 1) {
                if(k == -1 || p[k] == p[j]) {
                    ++ k;
                    ++ j;
                    if(p[k] != p[j]) {
                        next[j] = k;
                    } else {
                        next[j] = next[k];
                    }
                } else {
                    k = next[k];
                }
            }
            return next;
        }
};

int main(int argc, char** argv) {
    Solution solution;

    cout << solution.strStr("abcd", "bd") << endl;
    assert(solution.strStr("mississippi", "issipi") == -1);
}
